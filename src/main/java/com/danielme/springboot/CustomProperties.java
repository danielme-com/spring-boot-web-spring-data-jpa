package com.danielme.springboot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("custom")
public class CustomProperties {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
