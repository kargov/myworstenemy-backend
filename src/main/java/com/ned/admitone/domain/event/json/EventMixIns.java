package com.ned.admitone.domain.event.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ned.admitone.domain.event.EventInterface;

public class EventMixIns {

    private EventMixIns() {
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    public abstract static class EventMixIn implements EventInterface {
        @Override
        @JsonProperty
        public abstract Long getId();

        @Override
        @JsonProperty
        public abstract String getName();
    }
}
