package com.in28minutes.springboot.learnspringboot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ConfigurationProperties(prefix = "currency-service")
@Component
public class CurrencyServiceConfiguration {

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		log.warn("Setting URL to {}", url);
		this.url = url;
	}

	@Override
	public String toString() {P
		return "CurrencyServiceConfiguration [url=" + url + "]";
	}
	
}
