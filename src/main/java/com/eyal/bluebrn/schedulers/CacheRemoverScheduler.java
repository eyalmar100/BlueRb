package com.eyal.bluebrn.schedulers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.eyal.bluebrn.services.cache.CacheManager;

@Component
public class CacheRemoverScheduler {

	@Autowired
	CacheManager cacheManager;

	@Scheduled(fixedDelay = 18000000)
	public void removeCache() {
		Long currentTime = System.currentTimeMillis();
		List<Object> ids = cacheManager.getMap().entrySet().stream()
				.filter(entry -> entry.getValue().getActiveTill() < currentTime).map(entry -> entry.getKey())
				.collect(Collectors.toList());
		ids.forEach(id ->{
			this.cacheManager.deleteCache(id);
			System.out.println(" delete id "+id);
		});
	}
}
