package com.eyal.bluebrn.services;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eyal.bluebrn.dtos.requests.TicketAvailabilityRequest;
import com.eyal.bluebrn.dtos.responses.TicketAvailabilityResponse;
import com.eyal.bluebrn.models.Tickets;
import com.eyal.bluebrn.services.cache.Cache;
import com.eyal.bluebrn.services.cache.CacheManager;
import com.eyal.bluebrn.services.fs.FileSystem;
import com.google.gson.Gson;

@Service
public class TicketService {

	@Autowired
	FileSystem fileSystem;

	@Autowired
	CacheManager cacheManager;

	private Tickets tickets;

	@PostConstruct
	public void loadTickets() {
		tickets = new Gson().fromJson(fileSystem.readFile("/fs/tickets.json"), Tickets.class);
		if (tickets == null) {
			tickets = Tickets.builder().tickets(Arrays.asList()).build();
		}
	}

	public TicketAvailabilityResponse checkTicketAvailability(TicketAvailabilityRequest ticketAvailabilityRequest) {
		Cache cache = cacheManager.getCache(ticketAvailabilityRequest);
		if (cache != null) {
			return (TicketAvailabilityResponse) cache.getData();
		}
		TicketAvailabilityResponse response = this.tickets.getTickets().stream()
				.filter(ticket -> StringUtils.equals(ticket.getId(), ticketAvailabilityRequest.getTicketId()))
				.map(ticket -> TicketAvailabilityResponse.builder().available(ticket.isAvailable()).build()).findFirst()
				.orElse(TicketAvailabilityResponse.builder().available(false).build());
		this.cacheManager.addCache(Cache.builder().id(ticketAvailabilityRequest).data(response)
				.activeTill(System.currentTimeMillis() + 18000000).build()); // Active till 5 mins
		return response;
	}
}
