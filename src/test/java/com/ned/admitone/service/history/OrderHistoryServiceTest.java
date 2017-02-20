package com.ned.admitone.service.history;

import com.ned.admitone.domain.event.Event;
import com.ned.admitone.domain.history.OrderHistory;
import com.ned.admitone.domain.ticket.Status;
import com.ned.admitone.domain.ticket.Ticket;
import com.ned.admitone.domain.user.Role;
import com.ned.admitone.domain.user.User;
import com.ned.admitone.jpa.repository.history.OrderHistoryRepository;
import com.ned.admitone.jpa.service.history.OrderHistoryService;
import com.ned.admitone.utills.TestObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.isA;

public class OrderHistoryServiceTest {

    @Test
    public void testMakeAudit() {
        //Given
        Event event = TestObjectFactory.getEvent(1L,"Awesome event");
        User user = TestObjectFactory.getUser(1L,"Ned","secret", Role.ROLE_CUSTOMER);
        List<Ticket> tickets = TestObjectFactory.getTickets(event,user,100, Status.PURCHASED);
        List<OrderHistory> history = TestObjectFactory.getHistory(tickets);
        //When
        OrderHistoryRepository historyRepository = Mockito.mock(OrderHistoryRepository.class);
        Mockito.when(historyRepository.save(history)).thenReturn(history);
        OrderHistoryService service = new OrderHistoryService(historyRepository);
        List<OrderHistory> generatedHistory = service.makeAudit(tickets);
        //Assert
        Assert.assertNotNull(generatedHistory);
        Mockito.verify(historyRepository).save(isA(List.class));
    }
}
