package com.ned.admitone.domain.history;

import com.ned.admitone.domain.ticket.Status;
import com.ned.admitone.domain.ticket.Ticket;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderHistory {
    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Getter
    private Long eventId;
    @Setter
    @Getter
    private String evenName;

    @Setter
    @Getter
    private Long userId;
    @Setter
    @Getter
    private String userName;

    @Setter
    @Getter
    private Long ticketId;
    @Setter
    @Getter
    private Status ticketStatus;

    public OrderHistory(Ticket ticket) {
        this.eventId = ticket.getEvent().getId();
        this.evenName = ticket.getEvent().getName();
        this.userId = ticket.getUser().getId();
        this.userName = ticket.getUser().getUsername();
        this.ticketId = ticket.getId();
        this.ticketStatus = ticket.getStatus();
    }

}
