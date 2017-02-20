package com.ned.admitone.domain.api;

import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractResponse {
    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private Event event;

    @Getter
    @Setter
    protected boolean success;

    @Getter
    @Setter
    protected boolean valid;

    @Getter
    @Setter
    private List<Ticket> tickets;

    @Getter
    private List<ResponseInterface.DETAILS> messages;

    public AbstractResponse() {
        this.valid = true;
    }

    public void addMessage(ResponseInterface.DETAILS message){
        if(null == messages) {
            messages = new ArrayList<>();
        }
        messages.add(message);
    }

}
