package com.ned.admitone.domain.api;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractRequest {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private int numberOfTickets;
    @Getter
    @Setter
    private Long sourceEventId;
}
