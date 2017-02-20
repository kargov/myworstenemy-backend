package com.ned.admitone.jpa.service.history;

import com.ned.admitone.domain.history.OrderHistory;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.jpa.repository.history.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderHistoryService implements OrderHistoryServiceInterface {

    private final OrderHistoryRepository repository;

    @Autowired
    public OrderHistoryService(OrderHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrderHistory> makeAudit(List<Ticket> tickets) {
        List<OrderHistory> orders = new ArrayList<>();
        for (Ticket ticket : tickets) {
            orders.add(new OrderHistory(ticket));
        }
        return repository.save(orders);
    }
}
