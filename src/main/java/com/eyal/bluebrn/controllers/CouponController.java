package com.eyal.bluebrn.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyal.bluebrn.dtos.requests.ValidateCouponRequest;
import com.eyal.bluebrn.dtos.responses.ValidateCouponResponse;
import com.eyal.bluebrn.services.CouponService;

@RestController
@RequestMapping("/api/v1/coupons")
public class CouponController {

	@Autowired
	private CouponService couponService;

	@PostMapping(value = "/validate", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ValidateCouponResponse> validateCoupon(
			@RequestBody @Valid ValidateCouponRequest validateCouponRequest) {
		return ResponseEntity.ok(this.couponService.validateCoupon(validateCouponRequest));
	}
}
