package com.ned.admitone.domain.admin.ticketoverview.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(as=TicketsOverviewResponse.class)
public interface TicketsOverviewResponseInterface {
    List<TicketsOverview> getResults();
    boolean isSuccess();
}
