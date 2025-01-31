package com.maybank.labs.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maybank.labs.starter.cache.StarterCacheManager;
import com.maybank.labs.starter.entities.Config;

@Service
public class ConfigService {

	@Autowired
	StarterCacheManager starterCacheManager;

	public Config getConfig(final String configEntry, final String configShortCode) {
		return starterCacheManager.getConfig(configEntry, configShortCode);
	}
}
