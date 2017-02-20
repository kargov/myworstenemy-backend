package com.ned.admitone.service.ticket;

import com.ned.admitone.domain.admin.ticketoverview.response.TicketsOverview;
import com.ned.admitone.domain.admin.ticketoverview.response.TicketsOverviewResponseInterface;
import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.ticket.Status;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.Role;
import com.ned.admitone.domain.user.User;
import com.ned.admitone.jpa.repository.ticket.TicketRepository;
import com.ned.admitone.jpa.service.ticket.TicketService;
import com.ned.admitone.utills.TestObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceTest {

    @Test
    public void testMakeCancellation() {
        //Given
        Event event = TestObjectFactory.getEvent(1L,"Awesome event");
        User user = TestObjectFactory.getUser(1L,"Ned","secret", Role.ROLE_CUSTOMER);
        List<Ticket> tickets = TestObjectFactory.getTickets(event,user,100, Status.PURCHASED);

        TicketRepository repository = Mockito.mock(TicketRepository.class);
        Mockito.when(repository.save(tickets)).thenReturn(tickets.stream().map(ticket -> {ticket.setStatus(Status.CANCELLED); return ticket;}).collect(Collectors.toList()));

        //When
        TicketService service = new TicketService(repository);
        List<Ticket> canceledTickets = service.makeCancellation(tickets);

        //Then
        Assert.assertEquals(tickets.size(),canceledTickets.size());
        canceledTickets.forEach(ticket -> Assert.assertEquals(Status.CANCELLED,ticket.getStatus()));
        Mockito.verify(repository).save(canceledTickets);
    }

    @Test
    public void testGetTickets() {
        //Given
        Event event = TestObjectFactory.getEvent(1L,"Awesome event");
        User user = TestObjectFactory.getUser(1L,"Ned","secret", Role.ROLE_CUSTOMER);
        List<Ticket> tickets = TestObjectFactory.getTickets(event,user,100, Status.PURCHASED);

        TicketRepository repository = Mockito.mock(TicketRepository.class);
        Mockito.when(repository.getTickets(event,user,new PageRequest(0, 100))).thenReturn(new PageImpl<>(tickets,new PageRequest(0, 100),100));

        /// /When
        TicketService service = new TicketService(repository);
        List<Ticket> retrievedTickets = service.getTickets(event,user,100);

        //Then
        Assert.assertNotNull(retrievedTickets);
        Mockito.verify(repository).getTickets(event,user,new PageRequest(0, 100));
    }

    @Test
    public void testExchangeTickets() {
        //Given
        Event source = TestObjectFactory.getEvent(1L,"Awesome event");
        Event target = TestObjectFactory.getEvent(1L,"Awesome event");
        User user = TestObjectFactory.getUser(1L,"Ned","secret", Role.ROLE_CUSTOMER);
        List<Ticket> tickets = TestObjectFactory.getTickets(source,user,100, Status.PURCHASED);

        TicketRepository repository = Mockito.mock(TicketRepository.class);
        Mockito.when(repository.save(tickets)).thenReturn(tickets);

        //When
        TicketService service = new TicketService(repository);
        List<Ticket> exchangedTickets = service.makeExchange(tickets,target);

        //Then
        Assert.assertNotNull(exchangedTickets);
        exchangedTickets.forEach(ticket -> Assert.assertEquals(Status.EXCHANGED,ticket.getStatus()));
        Mockito.verify(repository).save(tickets);
    }

    @Test
    public void testGetTicketOverview() {
        //Given
        Long startEventId = 1L;
        Long endEventId = 19L;

        List<TicketsOverview> overview = new ArrayList<>();
        overview.add(new TicketsOverview(1L,"Ned",100L));
        overview.add(new TicketsOverview(2L,"Ned",10L));

        TicketRepository repository = Mockito.mock(TicketRepository.class);
        Mockito.when(repository.getTicketsOverview(startEventId,endEventId)).thenReturn(overview);

        //When
        TicketService service = new TicketService(repository);
        TicketsOverviewResponseInterface overviewResponse = service.getTicketOverview(startEventId,endEventId);

        //Then
        Assert.assertNotNull(overviewResponse);
        Assert.assertEquals(2, overviewResponse.getResults().size());
        Mockito.verify(repository).getTicketsOverview(startEventId,endEventId);
    }

}
