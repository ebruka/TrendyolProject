package com.denemebase.configs;

import com.trendyolbase.utility.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Data;

import java.io.File;

@Data
public class DriverConfig {

    private boolean chromeHeadless;
    private boolean firefoxHeadless;
    private boolean mobileHeadless;

    private DriverConfig() {
    }

    private static DriverConfig instance;

    public static DriverConfig getInstance() {
        if (instance == null) {
            try {
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                return mapper.readValue(new File("src/test/resources/driver_config.yaml"), DriverConfig.class);
            } catch (Exception e) {
                Log.fail("Config Dosyası Okunurken hata alındı!", e);
            }
        }
        return instance;
    }
}
