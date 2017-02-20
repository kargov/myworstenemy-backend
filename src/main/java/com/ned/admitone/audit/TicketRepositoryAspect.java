package com.ned.admitone.audit;

import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.jpa.service.history.OrderHistoryService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class TicketRepositoryAspect {

    private final OrderHistoryService service;

    @Autowired
    public TicketRepositoryAspect(OrderHistoryService service) {
        this.service = service;
    }

    @After("execution(* com.ned.admitone.jpa.repository.ticket.TicketRepository.save(..))")
    public void beforeSave(JoinPoint joinPoint){
        Object[] objects = joinPoint.getArgs();
        List<Ticket> tickets = (List<Ticket>) objects[0];
        service.makeAudit(tickets);
    }
}
