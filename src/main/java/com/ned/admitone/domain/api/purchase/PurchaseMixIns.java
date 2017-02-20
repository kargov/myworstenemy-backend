package com.ned.admitone.domain.api.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ned.admitone.domain.api.ResponseInterface;
import com.ned.admitone.domain.api.purchase.request.PurchaseRequestInterface;
import com.ned.admitone.domain.api.purchase.response.PurchaseResponseInterface;
import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.User;

import java.util.List;

public class PurchaseMixIns {

    private PurchaseMixIns() {
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    public abstract static class PurchaseRequestMixIn implements PurchaseRequestInterface {
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
    public abstract static class PurchaseResponseMixIn implements PurchaseResponseInterface {
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
        public abstract List<Ticket> getTickets();

        @Override
        @JsonProperty
        public abstract List<ResponseInterface.DETAILS> getMessages();
    }
}
