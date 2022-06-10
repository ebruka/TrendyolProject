package com.denemebase.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import com.trendyolbase.utility.*;

public class Configuration {

	private static Configuration instance;
	private Properties configProps = new Properties();
	private static String machine;

	public static Configuration getInstance() {

		if (instance == null)
			createInstance();

		return instance;
	}

	private static synchronized void createInstance() {

		if (instance == null)
			instance = new Configuration();
	}

	private Configuration() {

		InputStream is = null;
		try {
			is = ClassLoader.getSystemResourceAsStream("config.properties");
			configProps.load(is);

			setMachine(configProps.getProperty("server"));
		} catch (Exception e) {
			Log.fail("config.properties dosyası ile alakalı sorun yaşandı." + Arrays.toString(e.getStackTrace()));
			//ExtentTestManager.getTest()
			  //               .log(Status.FAIL, "config.properties dosyası ile alakalı sorun yaşandı.");
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					Log.fail("config.properties dosyası kapatılamadı." , e);
					//ExtentTestManager.getTest()
					  //               .log(Status.FAIL, "config.properties dosyası kapatılamadı.");
				}
		}
	}

	public static String getMachine() {

		return machine;
	}

	public static void setMachine(String machine) {

		Configuration.machine = machine;
	}
}
