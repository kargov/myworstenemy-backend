package com.ned.admitone.service.api.exchange;

import com.ned.admitone.domain.api.ResponseInterface;
import com.ned.admitone.domain.api.exchange.request.ExchangeRequest;
import com.ned.admitone.domain.api.exchange.response.ExchangeResponseInterface;
import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.ticket.Status;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.Role;
import com.ned.admitone.domain.user.User;
import com.ned.admitone.jpa.service.api.exchange.ExchangeService;
import com.ned.admitone.jpa.service.event.EventServiceInterface;
import com.ned.admitone.jpa.service.ticket.TicketServiceInterface;
import com.ned.admitone.jpa.service.user.UserServiceInterface;
import com.ned.admitone.utills.TestObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class ExchangeServiceTest {
    @Test
    public void testMakeExchange() {
        //Given
        Event source = TestObjectFactory.getEvent(1L,"Awesome event");
        Event target = TestObjectFactory.getEvent(2L,"Another Awesome event");
        User user = TestObjectFactory.getUser(1L,"Ned","secret", Role.ROLE_CUSTOMER);
        List<Ticket> tickets = TestObjectFactory.getTickets(source,user,100, Status.PURCHASED);

        EventServiceInterface eventService = Mockito.mock(EventServiceInterface.class);
        Mockito.when(eventService.getEvent(source.getId())).thenReturn(source);
        Mockito.when(eventService.getEvent(target.getId())).thenReturn(target);

        UserServiceInterface userService = Mockito.mock(UserServiceInterface.class);
        Mockito.when(userService.getUser(user.getUsername())).thenReturn(user);

        TicketServiceInterface ticketService = Mockito.mock(TicketServiceInterface.class);
        Mockito.when(ticketService.getTickets(source,user,100)).thenReturn(tickets);

        ExchangeRequest request = new ExchangeRequest();
        request.setUsername("Ned");
        request.setSourceEventId(source.getId());
        request.setTargetEventId(target.getId());
        request.setNumberOfTickets(100);
        //When
        ExchangeService service = new ExchangeService(ticketService,userService,eventService);
        ExchangeResponseInterface response = service.makeExchange(request);
        //Then
        Assert.assertNotNull(response);
        assertTickets(response);
        Assert.assertEquals(target,response.getEvent());
        Assert.assertEquals(user,response.getUser());
        Assert.assertTrue(response.isSuccess());
        Assert.assertNull(response.getMessages());
        Mockito.verify(eventService).getEvent(source.getId());
        Mockito.verify(eventService).getEvent(target.getId());
        Mockito.verify(userService).getUser("Ned");
        Mockito.verify(ticketService).getTickets(source,user,100);
    }

    @Test
    public void testMakeExchangeWrongCombination() {
        //Given
        Event source = TestObjectFactory.getEvent(1L,"Awesome event");
        User user = TestObjectFactory.getUser(1L,"Ned","secret", Role.ROLE_CUSTOMER);
        List<Ticket> tickets = TestObjectFactory.getTickets(source,user,100, Status.PURCHASED);

        EventServiceInterface eventService = Mockito.mock(EventServiceInterface.class);
        Mockito.when(eventService.getEvent(source.getId())).thenReturn(source);
        Mockito.when(eventService.getEvent(source.getId())).thenReturn(source);

        UserServiceInterface userService = Mockito.mock(UserServiceInterface.class);
        Mockito.when(userService.getUser(user.getUsername())).thenReturn(user);

        TicketServiceInterface ticketService = Mockito.mock(TicketServiceInterface.class);
        Mockito.when(ticketService.getTickets(source,user,100)).thenReturn(tickets);

        ExchangeRequest request = new ExchangeRequest();
        request.setUsername("Ned");
        request.setSourceEventId(source.getId());
        request.setTargetEventId(source.getId());
        request.setNumberOfTickets(100);
        //When
        ExchangeService service = new ExchangeService(ticketService,userService,eventService);
        ExchangeResponseInterface response = service.makeExchange(request);
        //Then
        Assert.assertNotNull(response);
        Assert.assertNull(response.getTickets());
        Assert.assertNull(response.getEvent());
        Assert.assertNull(response.getUser());
        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(Collections.singletonList(ResponseInterface.DETAILS.INCORRECT_EVENT_COMBINATION),response.getMessages());
    }

    private void assertTickets(ExchangeResponseInterface response) {
        for(Ticket ticket : response.getTickets()) {
            Assert.assertEquals(Status.EXCHANGED,ticket.getStatus());
        }
    }
}
