package com.ned.admitone.domain.ticket;


import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket implements TicketInterface {

    @Getter
    @Setter
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name="event_id", nullable = false)
    private Event event;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private Status status;

    public Ticket(){
    }

    public Ticket(Event event, User user) {
        this.event = event;
        this.user = user;
    }
}
