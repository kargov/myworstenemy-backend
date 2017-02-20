package com.ned.admitone.jpa.service.api;

import com.ned.admitone.domain.api.RequestInterface;
import com.ned.admitone.domain.api.ResponseInterface;
import com.ned.admitone.domain.api.exchange.response.ExchangeResponse;
import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.User;
import com.ned.admitone.jpa.service.event.EventServiceInterface;
import com.ned.admitone.jpa.service.ticket.TicketServiceInterface;
import com.ned.admitone.jpa.service.user.UserServiceInterface;

import java.util.List;
import java.util.Objects;

public class AbstractApiService {

    private final UserServiceInterface userService;
    protected final TicketServiceInterface ticketService;
    private final EventServiceInterface eventService;

    public AbstractApiService(TicketServiceInterface ticketService, UserServiceInterface userService, EventServiceInterface eventService) {
        this.ticketService = ticketService;
        this.userService = userService;
        this.eventService = eventService;
    }

    protected void checkUsername(String username, ResponseInterface response) {
        if(!validUserName(username)) {
            response.setValid(false);
            response.addMessage(ResponseInterface.DETAILS.INCORRECT_USER_NAME);
        }
    }

    protected void checkEventId(Long eventId, ResponseInterface response) {
        if(!validEventId(eventId)) {
            response.setValid(false);
            response.addMessage(ResponseInterface.DETAILS.INCORRECT_EVENT_ID);
        }
    }

    protected void checkEventCombination(Long sourceEventId, Long targetEventId, ExchangeResponse response) {
        if(!validEventCombination(sourceEventId,targetEventId)) {
            response.setValid(false);
            response.addMessage(ResponseInterface.DETAILS.INCORRECT_EVENT_COMBINATION);
        }
    }

    protected void checkNumberOfTickets(int numberOfTickets, ResponseInterface response) {
        if(!validNumberOfTickets(numberOfTickets)) {
            response.setValid(false);
            response.addMessage(ResponseInterface.DETAILS.NEGATIVE_NUMBER_OF_TICKETS);
        }
    }

    protected void checkEvent(ResponseInterface response, Event event) {
        if(!validEvent(event)) {
            response.setValid(false);
            response.addMessage(ResponseInterface.DETAILS.UNKNOWN_EVENT);
        }
    }

    protected void checkUser(ResponseInterface response, User user) {
        if(!validUser(user)) {
            response.setValid(false);
            response.addMessage(ResponseInterface.DETAILS.UNKNOWN_USER);
        }
    }
    protected void checkValidForCancellationOrExchange(RequestInterface request, ResponseInterface response, List<Ticket> tickets) {
        if(!validCancellationOrExchange(tickets,request.getNumberOfTickets())) {
            response.setValid(false);
            response.addMessage(ResponseInterface.DETAILS.EXCEEDED_NUMBER_OF_TICKETS);
        }
    }

    protected User getUser(String username) {
        return userService.getUser(username);
    }

    protected Event getEvent(Long eventId) {
        return eventService.getEvent(eventId);
    }

    private boolean validUser(User user) {
        return null != user;
    }
    private boolean validEvent(Event event) {
        return null != event;
    }
    private boolean validUserName(String username) {
        return null != username && !username.isEmpty();
    }
    private boolean validEventId(Long event) {return null != event && 0 < event;}
    private boolean validNumberOfTickets(int numberOfTickets) {
        return 0 < numberOfTickets;
    }
    private boolean validCancellationOrExchange(List<Ticket> tickets, int numberOfTicketsToCancell) {return tickets.size() == numberOfTicketsToCancell;}
    private boolean validEventCombination(Long sourceEventId, Long targetEventId) {
        return !Objects.equals(sourceEventId, targetEventId);
    }
}
