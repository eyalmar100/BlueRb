package com.eyal.bluebrn.services;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eyal.bluebrn.dtos.requests.ValidateCouponRequest;
import com.eyal.bluebrn.dtos.responses.ValidateCouponResponse;
import com.eyal.bluebrn.models.Coupons;
import com.eyal.bluebrn.services.cache.Cache;
import com.eyal.bluebrn.services.cache.CacheManager;
import com.eyal.bluebrn.services.fs.FileSystem;
import com.google.gson.Gson;

@Service
public class CouponService {

	@Autowired
	FileSystem fileSystem;

	@Autowired
	CacheManager cacheManager;

	private Coupons coupons;

	@PostConstruct
	public void loadCoupons() {
		coupons = new Gson().fromJson(fileSystem.readFile("/fs/coupons.json"), Coupons.class);
		if (coupons == null) {
			coupons = Coupons.builder().coupons(Arrays.asList()).build();
		}
	}

	public ValidateCouponResponse validateCoupon(ValidateCouponRequest validateCouponRequest) {
		Cache cache = cacheManager.getCache(validateCouponRequest);
		if (cache != null) {
			return (ValidateCouponResponse) cache.getData();
		}
		ValidateCouponResponse response = this.coupons.getCoupons().stream()
				.filter(coupon -> StringUtils.equals(coupon.getId(), validateCouponRequest.getCouponId()))
				.map(coupon -> ValidateCouponResponse.builder().message("Discount Applied!")
						.discountedPrice(validateCouponRequest.getPrice()
								.subtract(validateCouponRequest.getPrice()
										.multiply(coupon.getDiscount().divide(BigDecimal.valueOf(100.0))))
								.round(new MathContext(2)))
						.build())
				.findFirst().orElse(ValidateCouponResponse.builder().message("Invalid Coupon!")
						.discountedPrice(validateCouponRequest.getPrice()).build());
		this.cacheManager.addCache(Cache.builder().id(validateCouponRequest).data(response)
				.activeTill(System.currentTimeMillis() + 18000000).build()); // Active till 5 mins
		return response;
	}
}
