package com.ned.admitone.domain.ticket;

import com.ned.admitone.domain.event.Event;

public interface TicketInterface {
    Long getId();
    Event getEvent();
    Status getStatus();
}
