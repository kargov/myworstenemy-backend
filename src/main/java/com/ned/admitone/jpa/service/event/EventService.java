package com.ned.admitone.jpa.service.event;

import com.ned.admitone.domain.event.Event;
import com.ned.admitone.jpa.repository.show.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService implements EventServiceInterface {

    private final EventRepository repository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.repository = eventRepository;
    }

    @Override
    public Event getEvent(Long eventId) {
        return repository.findOne(eventId);
    }
}
