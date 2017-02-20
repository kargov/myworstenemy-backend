package com.ned.admitone.service.api.purchase;

import com.ned.admitone.domain.api.ResponseInterface;
import com.ned.admitone.domain.api.purchase.request.PurchaseRequest;
import com.ned.admitone.domain.api.purchase.response.PurchaseResponseInterface;
import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.ticket.Status;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.Role;
import com.ned.admitone.domain.user.User;
import com.ned.admitone.jpa.service.api.purchase.PurchaseService;
import com.ned.admitone.jpa.service.event.EventServiceInterface;
import com.ned.admitone.jpa.service.ticket.TicketServiceInterface;
import com.ned.admitone.jpa.service.user.UserServiceInterface;
import com.ned.admitone.utills.TestObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class PurchaseServiceTest {

    @Test
    public void testMakePurchaseOK() {
        //Given
        Event event = TestObjectFactory.getEvent(1L,"Awesome event");
        User user = TestObjectFactory.getUser(1L,"Ned","secret",Role.ROLE_CUSTOMER);
        List<Ticket> tickets = TestObjectFactory.getTickets(event,user,100, Status.PURCHASED);

        EventServiceInterface eventService = Mockito.mock(EventServiceInterface.class);
        Mockito.when(eventService.getEvent(event.getId())).thenReturn(event);

        UserServiceInterface userService = Mockito.mock(UserServiceInterface.class);
        Mockito.when(userService.getUser(user.getUsername())).thenReturn(user);

        TicketServiceInterface ticketService = Mockito.mock(TicketServiceInterface.class);
        Mockito.when(ticketService.generateNewTickets(event,user,100)).thenReturn(tickets);

        PurchaseRequest request = new PurchaseRequest();
        request.setUsername("Ned");
        request.setSourceEventId(1L);
        request.setNumberOfTickets(100);
        //When
        PurchaseService service = new PurchaseService(ticketService,userService,eventService);
        PurchaseResponseInterface response = service.makePurchase(request);
        //Then
        Assert.assertNotNull(response);
        assertTickets(response);
        Assert.assertEquals(event,response.getEvent());
        Assert.assertEquals(user,response.getUser());
        Assert.assertTrue(response.isSuccess());
        Assert.assertNull(response.getMessages());
        Mockito.verify(eventService).getEvent(1L);
        Mockito.verify(userService).getUser("Ned");
        Mockito.verify(ticketService).generateNewTickets(event,user,100);
    }

    @Test
    public void testMakePurchaseIncorrectEventMissingUsername() {
        //Given
        EventServiceInterface eventService = Mockito.mock(EventServiceInterface.class);
        UserServiceInterface userService = Mockito.mock(UserServiceInterface.class);
        TicketServiceInterface ticketService = Mockito.mock(TicketServiceInterface.class);

        PurchaseRequest request = new PurchaseRequest();
        request.setUsername("");
        request.setSourceEventId(-1L);
        request.setNumberOfTickets(-100);
        //When
        PurchaseService service = new PurchaseService(ticketService,userService,eventService);
        PurchaseResponseInterface response = service.makePurchase(request);
        //Then
        Assert.assertNotNull(response);
        Assert.assertNull(response.getTickets());
        Assert.assertNull(response.getEvent());
        Assert.assertNull(response.getUser());
        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(Arrays.asList(ResponseInterface.DETAILS.INCORRECT_EVENT_ID,ResponseInterface.DETAILS.INCORRECT_USER_NAME, ResponseInterface.DETAILS.NEGATIVE_NUMBER_OF_TICKETS),response.getMessages());
    }

    @Test
    public void testMakePurchaseUnknownEventUnknownUser() {
        //Given
        EventServiceInterface eventService = Mockito.mock(EventServiceInterface.class);
        Mockito.when(eventService.getEvent(1L)).thenReturn(null);

        UserServiceInterface userService = Mockito.mock(UserServiceInterface.class);
        Mockito.when(userService.getUser("Ned")).thenReturn(null);

        TicketServiceInterface ticketService = Mockito.mock(TicketServiceInterface.class);

        PurchaseRequest request = new PurchaseRequest();
        request.setUsername("Ned");
        request.setSourceEventId(1L);
        request.setNumberOfTickets(100);
        //When
        PurchaseService service = new PurchaseService(ticketService,userService,eventService);
        PurchaseResponseInterface response = service.makePurchase(request);
        //Then
        Assert.assertNotNull(response);
        Assert.assertNull(response.getTickets());
        Assert.assertNull(response.getEvent());
        Assert.assertNull(response.getUser());
        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(Arrays.asList(ResponseInterface.DETAILS.UNKNOWN_EVENT, ResponseInterface.DETAILS.UNKNOWN_USER),response.getMessages());
        Mockito.verify(eventService).getEvent(1L);
        Mockito.verify(userService).getUser("Ned");
    }

    private void assertTickets(PurchaseResponseInterface response) {
        for(Ticket ticket : response.getTickets()) {
            Assert.assertEquals(Status.PURCHASED,ticket.getStatus());
        }
    }
}
