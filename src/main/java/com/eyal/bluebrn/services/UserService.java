package com.eyal.bluebrn.services;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eyal.bluebrn.dtos.requests.CheckInRequest;
import com.eyal.bluebrn.dtos.responses.CheckInResponse;
import com.eyal.bluebrn.models.User;
import com.eyal.bluebrn.models.Users;
import com.eyal.bluebrn.services.cache.Cache;
import com.eyal.bluebrn.services.cache.CacheManager;
import com.eyal.bluebrn.services.fs.FileSystem;
import com.google.gson.Gson;

@Service
public class UserService {

	@Autowired
	FileSystem fileSystem;

	@Autowired
	CacheManager cacheManager;

	private Users users;

	@PostConstruct
	public void loadUsers() {
		users = new Gson().fromJson(fileSystem.readFile("/fs/users.json"), Users.class);
		if (users == null) {
			users = Users.builder().users(Arrays.asList()).build();
		}
	}

	public CheckInResponse checkIn(CheckInRequest checkInRequest) {
		Cache cache = cacheManager.getCache(checkInRequest);
		if (cache != null) {
			return (CheckInResponse) cache.getData();
		}
		CheckInResponse response = users.getUsers().stream().filter(user -> {
			return StringUtils.equals(user.getDestinationId(), checkInRequest.getDestinationId())
					&& StringUtils.equals(user.getBaggage().getId(), checkInRequest.getBaggageId());
		}).map(user -> CheckInResponse.builder().checkedIn(true).build()).findFirst()
				.orElse(CheckInResponse.builder().checkedIn(false).build());
		this.cacheManager.addCache(Cache.builder().id(checkInRequest).data(response)
				.activeTill(System.currentTimeMillis() + 180000).build()); // Active till 5 mins
		return response;
	}
	
	public boolean addUser(User user) {
		
		System.out.println(" adding user");
		return true;
	}
}
