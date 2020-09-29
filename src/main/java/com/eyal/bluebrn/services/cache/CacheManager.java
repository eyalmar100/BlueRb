package com.eyal.bluebrn.services.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class CacheManager {

	private ConcurrentHashMap<Object, Cache> cacheMap = new ConcurrentHashMap<>();

	public void addCache(Cache cache) {
		cacheMap.put(cache.getId(), cache);
	}

	public void deleteCache(Object id) {
		cacheMap.remove(id);
	}

	public Cache getCache(Object id) {
		return cacheMap.get(id);
	}

	public Map<Object, Cache> getMap() {
		return cacheMap;
	}
}
