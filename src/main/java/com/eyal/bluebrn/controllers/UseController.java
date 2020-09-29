package com.eyal.bluebrn.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyal.bluebrn.dtos.requests.CheckInRequest;
import com.eyal.bluebrn.dtos.responses.CheckInResponse;
import com.eyal.bluebrn.models.User;
import com.eyal.bluebrn.services.UserService;
import com.eyal.bluebrn.services.fs.FileSystem;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UseController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/checkin", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CheckInResponse> checkin(@RequestBody @Valid CheckInRequest checkInRequest) {
		return ResponseEntity.ok(this.userService.checkIn(checkInRequest));
	}
	
	
	@PostMapping(value = "/checkintest", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> checkinTest(@RequestBody @Valid CheckInRequest checkInRequest) {
		return ResponseEntity.ok( null);
	}
	
	@PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> addUser(@RequestBody @Valid User user) {
		log.info("some test message");
		return ResponseEntity.ok(this.userService.addUser(user));
	}
}
