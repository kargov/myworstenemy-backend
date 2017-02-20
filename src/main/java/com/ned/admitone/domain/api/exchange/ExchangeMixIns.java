package com.ned.admitone.domain.api.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ned.admitone.domain.api.ResponseInterface;
import com.ned.admitone.domain.api.exchange.request.ExchangeRequestInterface;
import com.ned.admitone.domain.api.exchange.response.ExchangeResponseInterface;
import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.User;

import java.util.List;

public class ExchangeMixIns {
    private ExchangeMixIns() {
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    public abstract static class ExchangeRequestMix implements ExchangeRequestInterface {
        @Override
        @JsonProperty
        public abstract String getUsername();

        @Override
        @JsonProperty
        public abstract Long getSourceEventId();

        @Override
        @JsonProperty
        public abstract Long getTargetEventId();

        @Override
        @JsonProperty
        public abstract int getNumberOfTickets();
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    public abstract static class ExchangeResponseMixIn implements ExchangeResponseInterface {
        @Override
        @JsonProperty
        public abstract User getUser();

        @Override
        @JsonProperty
        public abstract Event getEvent();

        @Override
        @JsonProperty
        public abstract boolean isSuccess();

        @Override
        @JsonProperty
        public abstract List<ResponseInterface.DETAILS> getMessages();

        @Override
        @JsonProperty
        public abstract List<Ticket> getTickets();
    }
}
