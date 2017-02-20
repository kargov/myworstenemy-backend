package com.ned.admitone.domain.admin.ticketoverview.response;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TicketsOverviewResponse implements TicketsOverviewResponseInterface {
    @Getter
    @Setter
    private List<TicketsOverview> results;

    @Getter
    @Setter
    private boolean success;
}
