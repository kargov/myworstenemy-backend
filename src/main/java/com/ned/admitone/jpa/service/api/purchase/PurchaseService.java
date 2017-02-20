package com.ned.admitone.jpa.service.api.purchase;

import com.ned.admitone.domain.api.purchase.request.PurchaseRequestInterface;
import com.ned.admitone.domain.api.purchase.response.PurchaseResponse;
import com.ned.admitone.domain.api.purchase.response.PurchaseResponseInterface;
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
public class PurchaseService extends AbstractApiService implements PurchaseServiceInterface {

    @Autowired
    public PurchaseService(TicketServiceInterface ticketService, UserServiceInterface userService, EventServiceInterface eventService) {
        super(ticketService, userService, eventService);
    }

    @Override
    public PurchaseResponseInterface makePurchase(PurchaseRequestInterface request) {
        PurchaseResponse response = new PurchaseResponse();

        checkEventId(request.getSourceEventId(), response);
        checkUsername(request.getUsername(), response);
        checkNumberOfTickets(request.getNumberOfTickets(), response);

        if(response.isValid()) {
            Event event = getEvent(request.getSourceEventId());
            checkEvent(response, event);

            User user = getUser(request.getUsername());
            checkUser(response, user);

            if(response.isValid()) {
                List<Ticket> tickets = ticketService.generateNewTickets(event, user, request.getNumberOfTickets());
                response.setTickets(tickets);
                response.setEvent(event);
                response.setUser(user);
                response.setSuccess(true);
            }
        }
        return response;
    }
}
