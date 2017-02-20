package com.ned.admitone.service.api.cancellation;

import com.ned.admitone.domain.api.ResponseInterface;
import com.ned.admitone.domain.api.cancellation.request.CancellationRequest;
import com.ned.admitone.domain.api.cancellation.response.CancellationResponseInterface;
import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.ticket.Status;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.Role;
import com.ned.admitone.domain.user.User;
import com.ned.admitone.jpa.service.api.cancellation.CancellationService;
import com.ned.admitone.jpa.service.event.EventServiceInterface;
import com.ned.admitone.jpa.service.ticket.TicketServiceInterface;
import com.ned.admitone.jpa.service.user.UserServiceInterface;
import com.ned.admitone.utills.TestObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class CancellationServiceTest {

    @Test
    public void testMakeCancellation() {
        Event event = TestObjectFactory.getEvent(1L,"Awesome event");
        User user = TestObjectFactory.getUser(1L,"Ned","secret", Role.ROLE_CUSTOMER);
        List<Ticket> tickets = TestObjectFactory.getTickets(event,user,100, Status.PURCHASED);

        EventServiceInterface eventService = Mockito.mock(EventServiceInterface.class);
        Mockito.when(eventService.getEvent(event.getId())).thenReturn(event);

        UserServiceInterface userService = Mockito.mock(UserServiceInterface.class);
        Mockito.when(userService.getUser(user.getUsername())).thenReturn(user);

        TicketServiceInterface ticketService = Mockito.mock(TicketServiceInterface.class);
        Mockito.when(ticketService.getTickets(event,user,100)).thenReturn(tickets);

        CancellationRequest request = new CancellationRequest();
        request.setUsername("Ned");
        request.setSourceEventId(1L);
        request.setNumberOfTickets(100);
        //When
        CancellationService service = new CancellationService(ticketService,userService,eventService);
        CancellationResponseInterface response = service.makeCancellation(request);

        Assert.assertNotNull(response);
        assertTickets(response);
        Assert.assertEquals(event,response.getEvent());
        Assert.assertEquals(user,response.getUser());
        Assert.assertTrue(response.isSuccess());
        Assert.assertNull(response.getMessages());
        Mockito.verify(eventService).getEvent(1L);
        Mockito.verify(userService).getUser("Ned");
        Mockito.verify(ticketService).getTickets(event,user,100);
    }

    @Test
    public void testMakeCancellationMoreThanYouHave() {
        Event event = TestObjectFactory.getEvent(1L,"Awesome event");
        User user = TestObjectFactory.getUser(1L,"Ned","secret", Role.ROLE_CUSTOMER);
        List<Ticket> tickets = TestObjectFactory.getTickets(event,user,100, Status.PURCHASED);

        EventServiceInterface eventService = Mockito.mock(EventServiceInterface.class);
        Mockito.when(eventService.getEvent(event.getId())).thenReturn(event);

        UserServiceInterface userService = Mockito.mock(UserServiceInterface.class);
        Mockito.when(userService.getUser(user.getUsername())).thenReturn(user);

        TicketServiceInterface ticketService = Mockito.mock(TicketServiceInterface.class);
        Mockito.when(ticketService.getTickets(event,user,200)).thenReturn(tickets);

        CancellationRequest request = new CancellationRequest();
        request.setUsername("Ned");
        request.setSourceEventId(1L);
        request.setNumberOfTickets(200);
        //When
        CancellationService service = new CancellationService(ticketService,userService,eventService);
        CancellationResponseInterface response = service.makeCancellation(request);

        Assert.assertNotNull(response);
        Assert.assertNull(response.getTickets());
        Assert.assertNull(response.getEvent());
        Assert.assertNull(response.getUser());
        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(Collections.singletonList(ResponseInterface.DETAILS.EXCEEDED_NUMBER_OF_TICKETS),response.getMessages());
        Mockito.verify(eventService).getEvent(1L);
        Mockito.verify(userService).getUser("Ned");
        Mockito.verify(ticketService).getTickets(event,user,200);
    }

    private void assertTickets(CancellationResponseInterface response) {
        for(Ticket ticket : response.getTickets()) {
            Assert.assertEquals(Status.CANCELLED,ticket.getStatus());
        }
    }
}
