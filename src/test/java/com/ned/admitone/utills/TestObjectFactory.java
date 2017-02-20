package com.ned.admitone.utills;

import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.history.OrderHistory;
import com.ned.admitone.domain.ticket.Status;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.Role;
import com.ned.admitone.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class TestObjectFactory {

    public static User getUser(Long id,String username, String password,Role role) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }

    public static Event getEvent(Long eventId, String eventName) {
        Event event = new Event();
        event.setId(eventId);
        event.setName(eventName);
        return event;
    }

    public static List<Ticket> getTickets(Event event, User user, int numberOfTickets, Status status) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setUser(user);
            ticket.setStatus(status);
            tickets.add(ticket);
        }
        return tickets;
    }

    public static List<OrderHistory> getHistory(List<Ticket> tickets) {
        List<OrderHistory> orders = new ArrayList<>();
        for (Ticket ticket : tickets) {
            orders.add(new OrderHistory(ticket));
        }
        return orders;
    }
}
