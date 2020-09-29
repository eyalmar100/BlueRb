package com.eyal.bluebrn.dtos.requests;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidateCouponRequest {
	@NotNull(message="couponId is required")
	private String couponId;

	@NotNull
	@Positive(message="price must ne positive")
	private BigDecimal price;
}
