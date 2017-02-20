package com.ned.admitone.jpa.service.api.cancellation;

import com.ned.admitone.domain.api.cancellation.request.CancellationRequestInterface;
import com.ned.admitone.domain.api.cancellation.response.CancellationResponse;
import com.ned.admitone.domain.api.cancellation.response.CancellationResponseInterface;
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
public class CancellationService extends AbstractApiService implements CancellationServiceInterface {

    @Autowired
    public CancellationService(TicketServiceInterface ticketService, UserServiceInterface userService, EventServiceInterface eventService) {
        super(ticketService, userService, eventService);
    }

    @Override
    public CancellationResponseInterface makeCancellation(CancellationRequestInterface request) {
        CancellationResponse response = new CancellationResponse();

        checkEventId(request.getSourceEventId(), response);
        checkUsername(request.getUsername(), response);
        checkNumberOfTickets(request.getNumberOfTickets(), response);

        if(response.isValid()) {

            Event event = getEvent(request.getSourceEventId());
            checkEvent(response, event);

            User user = getUser(request.getUsername());
            checkUser(response, user);

            List<Ticket> tickets = ticketService.getTickets(event,user,request.getNumberOfTickets());
            checkValidForCancellationOrExchange(request, response, tickets);

            if(response.isValid()) {
                tickets = ticketService.makeCancellation(tickets);
                response.setEvent(event);
                response.setUser(user);
                response.setTickets(tickets);
                response.setSuccess(true);
            }
        }
        return response;
    }
}
