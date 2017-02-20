package com.ned.admitone.domain.api;

import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.User;

import java.util.List;

public interface ResponseInterface {

    enum DETAILS {
        INCORRECT_USER_NAME,
        INCORRECT_EVENT_ID,
        INCORRECT_EVENT_COMBINATION,
        UNKNOWN_EVENT,
        UNKNOWN_USER,
        NEGATIVE_NUMBER_OF_TICKETS,
        EXCEEDED_NUMBER_OF_TICKETS,
    }

    User getUser();
    Event getEvent();
    boolean isSuccess();
    void setValid(boolean valid);
    List<DETAILS> getMessages();
    void addMessage(DETAILS message);
    List<Ticket> getTickets();
}
