package com.ned.admitone.jpa.service.event;


import com.ned.admitone.domain.event.Event;

public interface EventServiceInterface {
    Event getEvent(Long eventId);
}
