package com.eyal.bluebrn.dtos.responses;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidateCouponResponse {
	private BigDecimal discountedPrice;
	private String message;
}
