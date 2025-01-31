package com.maybank.labs.starter.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.maybank.labs.starter.entities.Config;
import com.maybank.labs.starter.repository.ConfigRepository;

@Service
public class StarterCacheManager {

	private ConfigRepository configRepository;


	public StarterCacheManager(ConfigRepository configRepository) {
		super();
		this.configRepository = configRepository;
	}

	@Cacheable("starterConfigCache")
	public Config getConfig(final String configEntry, final String configShortCode) {
		return configRepository.getConfig(configEntry, configShortCode);
	}

}
