package com.trendyolbase.data;

import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.trendyolbase.data.GetData.*;
import com.trendyolbase.utility.Log;


public class DataFinder {

	private DataFinder() {

		throw new IllegalStateException("Utility class");
	}

	private static Map<String, Properties> fileList = new HashMap<>();

	public static final String getUrl(Url u) {

		return getValue("GetData", u.name());
	}



	public static String getValue(String key) {

		return getValue("GetData", key);
	}

	private static String getValue(String filePath, String key) {

		return getProp(filePath).getProperty(key);
	}

	private static Properties getProp(String filePath) {

		if (!fileList.containsKey(filePath)) {
			fileList.put(filePath, createProp(filePath));
		}
		return fileList.get(filePath);
	}

	private static Properties createProp(String filePath) {

		InputStream is = null;
		Properties prop = new Properties();
		try {
			is = ClassLoader.getSystemResourceAsStream(filePath + ".properties");
			prop.load(is);
		} catch (Exception e) {
			Log.fail(filePath + ".properties" + " dosyası bulunamadı! \n\n" + e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					Log.fail("Dosya kapatılamadı! \n\n" + e);
				}
			}
		}
		return prop;
	}
}
