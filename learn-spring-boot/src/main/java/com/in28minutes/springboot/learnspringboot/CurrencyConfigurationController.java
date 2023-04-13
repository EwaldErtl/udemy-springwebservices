package com.in28minutes.springboot.learnspringboot;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-configuration")
public class CurrencyConfigurationController {

	@Autowired
	private CurrencyServiceConfiguration 	currencyServiceConfiguration;

	@GetMapping("/config")
	public CurrencyServiceConfiguration retrieveConfiguration() {
		return currencyServiceConfiguration;
	}
	
}
