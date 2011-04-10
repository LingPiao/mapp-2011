package com.mapp;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
	private List<Phone> values = new ArrayList<Phone>();

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("phone")) {
			Phone p = new Phone();
			p.setId(attributes.getValue("id"));
			p.setPname(attributes.getValue("pname"));
			p.setAppraise(attributes.getValue("appraise"));
			p.setTrend(attributes.getValue("trend"));
			p.setPrice(attributes.getValue("price"));
			p.setImage(attributes.getValue("image"));
			String desc = attributes.getValue("desc");
			if (desc.length() > Constants.MAX_DESCRIPTION_LENGTH) {
				p.setDesc(desc.substring(0, Constants.MAX_DESCRIPTION_LENGTH) + "...");
			} else {
				p.setDesc(desc);
			}
			values.add(p);
		}
	}

	public List<Phone> getValues() {
		return values;
	}
}
