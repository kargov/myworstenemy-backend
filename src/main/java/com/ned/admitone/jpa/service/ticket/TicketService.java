package com.ned.admitone.jpa.service.ticket;

import com.ned.admitone.domain.admin.ticketoverview.response.TicketsOverviewResponse;
import com.ned.admitone.domain.admin.ticketoverview.response.TicketsOverviewResponseInterface;
import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.event.EventInterface;
import com.ned.admitone.domain.ticket.Status;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.User;
import com.ned.admitone.domain.user.UserInterface;
import com.ned.admitone.jpa.repository.ticket.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService implements TicketServiceInterface {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> makeCancellation(List<Ticket> tickets) {
        for(Ticket ticket : tickets) {
            ticket.setStatus(Status.CANCELLED);
        }
        return saveOrUpdate(tickets);
    }

    @Override
    public List<Ticket> getTickets(EventInterface event, UserInterface user, int numberOfTickets) {
        return ticketRepository.getTickets(event, user, new PageRequest(0, numberOfTickets)).getContent();
    }

    @Override
    public List<Ticket> generateNewTickets(Event event, User user,int numberOfTickets) {
        List<Ticket> tickets = new ArrayList<>();
        for(int i = 0 ; i<numberOfTickets; i++) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setUser(user);
            ticket.setStatus(Status.PURCHASED);
            tickets.add(ticket);
        }
        return saveOrUpdate(tickets);
    }

    @Override
    public List<Ticket> makeExchange(List<Ticket> tickets, Event target) {
        tickets.forEach(ticket -> ticket.setEvent(target));
        for(Ticket ticket : tickets) {
            ticket.setEvent(target);
            ticket.setStatus(Status.EXCHANGED);
        }
        return saveOrUpdate(tickets);
    }

    @Override
    public TicketsOverviewResponseInterface getTicketOverview(Long startEventId, Long endEventId) {
        TicketsOverviewResponse response = new TicketsOverviewResponse();
        response.setResults(ticketRepository.getTicketsOverview(isMissing(startEventId)?1L:startEventId,isMissing(endEventId)?Integer.MAX_VALUE:endEventId));
        response.setSuccess(true);
        return response;
    }

    private boolean isMissing(Long start) {
        return null==start;
    }

    private List<Ticket> saveOrUpdate(List<Ticket> tickets) {
        return ticketRepository.save(tickets);
    }
}
