package com.maybank.labs.starter.cache;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class StarterCacheServices {

	@Resource(name = "cacheManager")
	CacheManager cacheManager;

	public void evictCache(final String cacheName) {
		cacheManager.getCache(cacheName).clear();
	}

	public void evictAllCaches() {
		cacheManager.getCacheNames().stream().forEach(cacheName -> evictCache(cacheName));
	}

	public String getCacheDetails(final String cacheName) {
		final Cache cache = cacheManager.getCache(cacheName);
		final Object nativeCache = cache.getNativeCache();
		long size = -1;
		long memsize = -1;
		long locHpSize = -1;
		long hitTime = -1;
		if (nativeCache instanceof net.sf.ehcache.Ehcache) {
			final net.sf.ehcache.Ehcache ehCache = (net.sf.ehcache.Ehcache) nativeCache;
			size = ehCache.getStatistics().getSize();
			memsize = ehCache.getStatistics().getLocalDiskSizeInBytes();
			locHpSize = ehCache.getStatistics().getLocalHeapSizeInBytes();
			hitTime = ehCache.getStatistics().cacheHitCount();
		}
		return fs("Name: " + cacheName) + fs("::: NumElems = " + size) + fs("::: DiskSize(B) = " + memsize)
				+ fs("::: Local Heap Size(B) = " + locHpSize) + fs("::: Hit Time = " + hitTime);
	}

	private String fs(final String text) {
		return String.format("%-" + 30 + "." + 30 + "s", text);
	}

	public List<String> getAllCacheDetails() {
		final List<String> retList = new ArrayList<>();
		cacheManager.getCacheNames().stream().forEach(cacheName -> {
			final Cache cache = cacheManager.getCache(cacheName);
			if (cache != null)
				retList.add(getCacheDetails(cacheName));
		});
		return retList;
	}

}
