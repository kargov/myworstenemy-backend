package com.ned.admitone.domain.ticket.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ned.admitone.domain.ticket.Status;
import com.ned.admitone.domain.ticket.TicketInterface;

public class TicketMixIns {
    private TicketMixIns() {
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    public abstract static class TicketMMixIn implements TicketInterface {
        @Override
        @JsonProperty
        public abstract Long getId();

        @Override
        @JsonProperty
        public abstract Status getStatus();
    }
}
