package com.maybank.labs.starter.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.maybank.labs.starter.bean.StarterRequestBean;


@Configuration
@EnableJpaRepositories(basePackages = {"com.maybank"})
@EnableCaching
@EnableWebMvc
@EnableScheduling
@EnableJpaAuditing
@Import({ PersistenceConfig.class})
@ComponentScan(basePackages = "com.maybank.*")
//public class DisbursementConfiguration extends WebMvcConfigurerAdapter {
public class StarterConfiguration implements WebMvcConfigurer {

//	@Bean
//	public CacheManager cacheManager() {
//		return new ConcurrentMapCacheManager("configuration");
//	}
//
	// THIS IS GENERIC
	@Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public StarterRequestBean disbRequestBean() {
		return new StarterRequestBean();
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		final EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		return cmfb;
	}
	
	@Bean
	@RequestScope
//	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public StarterRequestBean starterRequestBean() {
		return new StarterRequestBean();
	}

//
//	@Override
//	public void addInterceptors(final InterceptorRegistry registry) {
//		registry.addInterceptor(new DisbRequestInterceptor()).addPathPatterns("/**");
//	}
//
//	@Bean
//	@SessionScope
//	public DisbSessionBean sessionScopedBean() {
//		return new DisbSessionBean();
//	}

}
