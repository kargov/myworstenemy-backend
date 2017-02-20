package com.ned.admitone.jpa.service.api.exchange;

import com.ned.admitone.domain.api.exchange.request.ExchangeRequestInterface;
import com.ned.admitone.domain.api.exchange.response.ExchangeResponse;
import com.ned.admitone.domain.api.exchange.response.ExchangeResponseInterface;
import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.User;
import com.ned.admitone.jpa.service.api.AbstractApiService;
import com.ned.admitone.jpa.service.event.EventServiceInterface;
import com.ned.admitone.jpa.service.ticket.TicketServiceInterface;
import com.ned.admitone.jpa.service.user.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeService extends AbstractApiService implements ExchangeServiceInterface {

    @Autowired
    public ExchangeService(TicketServiceInterface ticketService, UserServiceInterface userService, EventServiceInterface eventService) {
        super(ticketService, userService, eventService);
    }

    @Override
    public ExchangeResponseInterface makeExchange(ExchangeRequestInterface request) {
        ExchangeResponse response = new ExchangeResponse();

        checkEventId(request.getSourceEventId(), response);
        checkEventId(request.getSourceEventId(), response);
        checkEventCombination(request.getSourceEventId(),request.getTargetEventId(), response);
        checkUsername(request.getUsername(), response);
        checkNumberOfTickets(request.getNumberOfTickets(), response);

        if(response.isValid()) {
            Event source = getEvent(request.getSourceEventId());
            checkEvent(response, source);
            response.setEvent(source);

            Event target = getEvent(request.getTargetEventId());
            checkEvent(response, target);

            User user = getUser(request.getUsername());
            checkUser(response, user);
            response.setUser(user);

            List<Ticket> tickets = ticketService.getTickets(source,user,request.getNumberOfTickets());
            checkValidForCancellationOrExchange(request, response, tickets);
            if(response.isValid()) {
                tickets = ticketService.makeExchange(tickets,target);
                response.setEvent(target);
                response.setTickets(tickets);
                response.setSuccess(true);
            }
        }
        return response;
    }
}
