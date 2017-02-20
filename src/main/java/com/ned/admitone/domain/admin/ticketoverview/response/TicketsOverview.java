package com.ned.admitone.domain.admin.ticketoverview.response;

import lombok.Getter;
import lombok.Setter;

public class TicketsOverview implements TicketsOverviewResultInterface {
    @Getter
    @Setter
    private Long eventId;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private Long totalNumberOfTickets;

    public TicketsOverview(Long eventId, String username, Long totalNumberOfTickets) {
        this.eventId = eventId;
        this.username = username;
        this.totalNumberOfTickets = totalNumberOfTickets;
    }
}
