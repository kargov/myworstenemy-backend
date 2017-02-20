package com.ned.admitone.jpa.repository.ticket;

import com.ned.admitone.domain.admin.ticketoverview.response.TicketsOverview;
import com.ned.admitone.domain.event.EventInterface;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.UserInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT ticket FROM Ticket ticket WHERE ticket.event= ?1 and ticket.user = ?2 and ticket.status in ('PURCHASED','EXCHANGED')")
    Page<Ticket> getTickets(EventInterface event, UserInterface user, Pageable page);

    @Query("select new com.ned.admitone.domain.admin.ticketoverview.response.TicketsOverview(ticket.event.id, ticket.user.username, count(*)) from Ticket as ticket where ticket.status in ('PURCHASED','EXCHANGED')and ticket.event.id between ?1 and ?2 GROUP BY ticket.event.id, ticket.user.username")
    List<TicketsOverview> getTicketsOverview(Long startEventId, Long endEventId);
}
