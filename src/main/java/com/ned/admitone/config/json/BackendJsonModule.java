package com.ned.admitone.config.json;


import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ned.admitone.domain.admin.login.LoginMixIns;
import com.ned.admitone.domain.admin.login.response.LoginResponse;
import com.ned.admitone.domain.admin.ticketoverview.TicketsOverviewMixIns;
import com.ned.admitone.domain.admin.ticketoverview.response.TicketsOverview;
import com.ned.admitone.domain.admin.ticketoverview.response.TicketsOverviewResponse;
import com.ned.admitone.domain.api.cancellation.CancellationMixIns;
import com.ned.admitone.domain.api.cancellation.request.CancellationRequest;
import com.ned.admitone.domain.api.cancellation.response.CancellationResponse;
import com.ned.admitone.domain.api.exchange.ExchangeMixIns;
import com.ned.admitone.domain.api.exchange.request.ExchangeRequest;
import com.ned.admitone.domain.api.exchange.response.ExchangeResponse;
import com.ned.admitone.domain.api.purchase.PurchaseMixIns;
import com.ned.admitone.domain.api.purchase.request.PurchaseRequest;
import com.ned.admitone.domain.api.purchase.response.PurchaseResponse;
import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.event.json.EventMixIns;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.ticket.json.TicketMixIns;
import com.ned.admitone.domain.user.User;
import com.ned.admitone.domain.user.json.UserMixIns;

class BackendJsonModule extends SimpleModule {

    private static final long serialVersionUID = 1L;
    private static final Version VERSION = VersionUtil.parseVersion("1.0-SNAPSHOT", "com.ned.admitone", "admitone");

    BackendJsonModule() {
        super(VERSION);

        setMixInAnnotation(PurchaseRequest.class, PurchaseMixIns.PurchaseRequestMixIn.class);
        setMixInAnnotation(PurchaseResponse.class, PurchaseMixIns.PurchaseResponseMixIn.class);

        setMixInAnnotation(CancellationRequest.class, CancellationMixIns.CancellationRequestMix.class);
        setMixInAnnotation(CancellationResponse.class, CancellationMixIns.CancellationResponseMixIn.class);

        setMixInAnnotation(ExchangeRequest.class, ExchangeMixIns.ExchangeRequestMix.class);
        setMixInAnnotation(ExchangeResponse.class, ExchangeMixIns.ExchangeResponseMixIn.class);

        setMixInAnnotation(LoginResponse.class, LoginMixIns.LoginResponseMixIn.class);

        setMixInAnnotation(TicketsOverviewResponse.class, TicketsOverviewMixIns.TicketsOverviewResponseMixIn.class);
        setMixInAnnotation(TicketsOverview.class, TicketsOverviewMixIns.TicketsOverviewResultMixIn.class);

        setMixInAnnotation(Ticket.class, TicketMixIns.TicketMMixIn.class);
        setMixInAnnotation(User.class, UserMixIns.UserMixIn.class);
        setMixInAnnotation(Event.class, EventMixIns.EventMixIn.class);
    }
}
