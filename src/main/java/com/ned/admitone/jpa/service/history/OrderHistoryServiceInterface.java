package com.ned.admitone.jpa.service.history;

import com.ned.admitone.domain.history.OrderHistory;
import com.ned.admitone.domain.ticket.Ticket;

import java.util.List;

public interface OrderHistoryServiceInterface {
    List<OrderHistory> makeAudit(List<Ticket> tickets);
}
