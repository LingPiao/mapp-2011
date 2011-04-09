package com.mapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class HttpUtil {

	/**
	 * Prevent initiation
	 */
	private HttpUtil() {

	}

	public static Bitmap getBitMap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		InputStream is = null;
		HttpURLConnection conn = null;
		try {
			myFileUrl = new URL(url);
			conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			Log.e(Constants.MODULE_NAME, "Get BitMap from " + url + " error, caused by:" + e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.e(Constants.MODULE_NAME, "Close input stream error, caused by:" + e.getMessage());
			}
			conn.disconnect();
		}
		return bitmap;
	}

	public static String getXmlString(String url) {
		String outputString = "";
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		try {
			outputString = httpclient.execute(httpget, responseHandler);
		} catch (Exception e) {
			Log.e(Constants.MODULE_NAME, "Get xml from " + url + " error, caused by:" + e.getMessage());
		}
		httpclient.getConnectionManager().shutdown();
		return outputString;
	}
}
