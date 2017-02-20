package com.ned.admitone.domain.admin.ticketoverview;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ned.admitone.domain.admin.ticketoverview.response.TicketsOverview;
import com.ned.admitone.domain.admin.ticketoverview.response.TicketsOverviewResponseInterface;
import com.ned.admitone.domain.admin.ticketoverview.response.TicketsOverviewResultInterface;

import java.util.List;

public class TicketsOverviewMixIns {
    private TicketsOverviewMixIns() {
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    public abstract static class TicketsOverviewResponseMixIn implements TicketsOverviewResponseInterface {
        @Override
        @JsonProperty
        public abstract List<TicketsOverview> getResults();

        @Override
        @JsonProperty
        public abstract boolean isSuccess();
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    public abstract static class TicketsOverviewResultMixIn implements TicketsOverviewResultInterface {
        @Override
        @JsonProperty
        public abstract Long getEventId();

        @Override
        @JsonProperty
        public abstract String getUsername();

        @Override
        @JsonProperty
        public abstract Long getTotalNumberOfTickets();
    }
}
