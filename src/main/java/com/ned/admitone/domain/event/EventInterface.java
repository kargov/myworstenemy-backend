package com.ned.admitone.domain.event;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=Event.class)
public interface EventInterface {
    Long getId();
    String getName();
}
