package com.ned.admitone.jpa.service.ticket;

import com.ned.admitone.domain.admin.ticketoverview.response.TicketsOverviewResponseInterface;
import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.event.EventInterface;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.User;
import com.ned.admitone.domain.user.UserInterface;

import java.util.List;

public interface TicketServiceInterface {
    List<Ticket> makeCancellation(List<Ticket> tickets);
    List<Ticket> getTickets(EventInterface event, UserInterface user, int numberOfTickets);
    List<Ticket> generateNewTickets(Event event, User user,int numberOfTickets);
    List<Ticket> makeExchange(List<Ticket> tickets, Event target);
    TicketsOverviewResponseInterface getTicketOverview(Long startEventId, Long endEventId);
}
