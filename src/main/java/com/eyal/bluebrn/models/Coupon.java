package com.eyal.bluebrn.models;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coupon {
	private String id;
	private BigDecimal discount;
}
