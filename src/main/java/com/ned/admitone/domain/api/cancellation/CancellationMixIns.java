package com.ned.admitone.domain.api.cancellation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ned.admitone.domain.api.ResponseInterface;
import com.ned.admitone.domain.api.cancellation.request.CancellationRequestInterface;
import com.ned.admitone.domain.api.cancellation.response.CancellationResponseInterface;
import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.User;

import java.util.List;

public class CancellationMixIns {
    private CancellationMixIns() {
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    public abstract static class CancellationRequestMix implements CancellationRequestInterface {
        @Override
        @JsonProperty
        public abstract String getUsername();

        @Override
        @JsonProperty
        public abstract Long getSourceEventId();

        @Override
        @JsonProperty
        public abstract int getNumberOfTickets();
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    public abstract static class CancellationResponseMixIn implements CancellationResponseInterface {
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
