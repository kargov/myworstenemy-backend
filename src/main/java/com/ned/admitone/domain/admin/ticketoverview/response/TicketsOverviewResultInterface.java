package com.ned.admitone.domain.admin.ticketoverview.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=TicketsOverview.class)
public interface TicketsOverviewResultInterface {
    Long getEventId();

    String getUsername();

    Long getTotalNumberOfTickets();
}
