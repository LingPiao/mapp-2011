package com.mapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlSerializer;

import android.util.Log;
import android.util.Xml;

public class XmlUtils {

	public static void saveFavorites(String appDir) {
		if (!MyFavoriteHolder.getInstance().hasItems()) {
			return;
		}
		XmlSerializer serializer = Xml.newSerializer();
		String xmlFileName = appDir + "/" + Constants.FAVORITES_FILE;
		Log.d(Constants.MODULE_NAME, "Xml file path=" + xmlFileName);
		FileOutputStream out = null;
		try {
			File xml = new File(xmlFileName);
			out = new FileOutputStream(xml);
			serializer.setOutput(out, "UTF-8");
			// <?xml version="1.0″ encoding="UTF-8″ standalone="yes"?>
			serializer.startDocument("UTF-8", true);
			// <favorites>
			serializer.startTag("", "favorites");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (Phone phone : MyFavoriteHolder.getInstance().getFavorites()) {
				serializer.startTag("", "phone");
				serializer.attribute("", "id", phone.getId());
				serializer.attribute("", "watchedDate", sdf.format(phone.getWatchedDate()));
				serializer.attribute("", "pname", phone.getPname());
				serializer.attribute("", "appraise", phone.getAppraise());
				serializer.attribute("", "trend", phone.getTrend());
				serializer.attribute("", "price", phone.getPrice());
				serializer.attribute("", "image", phone.getImage());
				serializer.attribute("", "desc", phone.getDesc());
				serializer.endTag("", "phone");
			}
			serializer.endTag("", "favorites");
			serializer.endDocument();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					Log.e(Constants.MODULE_NAME, e.getMessage());
				}
			}
		}

	}

	public static List<Phone> loadMyfavorites(String xmlFile) {
		List<Phone> phones = new ArrayList<Phone>();
		InputStream inStream = null;
		SAXParserFactory spf = SAXParserFactory.newInstance();

		XMLHandler handler = new XMLHandler();
		try {
			File f = new File(xmlFile);
			if (!f.exists()) {
				return phones;
			}
			inStream = new FileInputStream(f);
			SAXParser saxParser = spf.newSAXParser();
			saxParser.parse(inStream, handler);

		} catch (Exception e) {
			e.printStackTrace();
			Log.e(Constants.MODULE_NAME, e.getMessage());
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					Log.e(Constants.MODULE_NAME, e.getMessage());
				}
			}
		}
		phones = handler.getValues();
		PhoneCache.getInstance().addFavorite(phones);
		return phones;
	}

	public static List<Phone> loadPhones(String url) {
		String xml = HttpUtil.getXmlString(url);
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		XMLHandler handler = new XMLHandler();
		try {
			XMLReader reader = saxParserFactory.newSAXParser().getXMLReader();
			reader.setContentHandler(handler);
			reader.parse(new InputSource(new StringReader(xml)));
		} catch (Exception e) {
			Log.e(Constants.MODULE_NAME, e.getMessage());
		}
		List<Phone> phones = handler.getValues();
		PhoneCache.getInstance().addFavorite(phones);
		return phones;
	}

}
