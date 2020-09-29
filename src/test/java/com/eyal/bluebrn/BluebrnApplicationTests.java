package com.eyal.bluebrn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eyal.bluebrn.dtos.requests.CheckInRequest;
import com.eyal.bluebrn.dtos.requests.TicketAvailabilityRequest;
import com.eyal.bluebrn.dtos.requests.ValidateCouponRequest;
import com.eyal.bluebrn.services.CouponService;
import com.eyal.bluebrn.services.TicketService;
import com.eyal.bluebrn.services.UserService;

@SpringBootTest
class BluebrnApplicationTests {

	@Autowired
	CouponService couponService;

	@Autowired
	UserService userService;

	@Autowired
	TicketService ticketService;

	@Test
	void validCouponCode() {
		assertEquals(couponService
				.validateCoupon(ValidateCouponRequest.builder().couponId("coup1").price(BigDecimal.valueOf(99)).build())
				.getDiscountedPrice().doubleValue(), 89);
	}

	@Test
	void inValidCouponCode() {
		assertEquals(couponService
				.validateCoupon(ValidateCouponRequest.builder().couponId("coupx").price(BigDecimal.valueOf(99)).build())
				.getDiscountedPrice().doubleValue(), 99);
	}

	@Test
	void validTicketAvailability() {
		assertEquals(ticketService.checkTicketAvailability(TicketAvailabilityRequest.builder().ticketId("1").build())
				.isAvailable(), true);
	}

	@Test
	void inValidTicketAvailability() {
		assertEquals(ticketService.checkTicketAvailability(TicketAvailabilityRequest.builder().ticketId("1x").build())
				.isAvailable(), false);
	}

	@Test
	void validCheckIn() {
		assertEquals(
				userService.checkIn(CheckInRequest.builder().baggageId("user2_bag1").destinationId("dest-new").build())
						.isCheckedIn(),
				true);
	}

	@Test
	void inValidCheckIn() {
		assertEquals(
				userService.checkIn(CheckInRequest.builder().baggageId("user2_bagX").destinationId("dest-new").build())
						.isCheckedIn(),
				false);
	}
}
