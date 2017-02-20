package com.ned.admitone.controller.admin.ticketsoverview;

import com.ned.admitone.domain.admin.ticketoverview.response.TicketsOverviewResponseInterface;
import com.ned.admitone.jpa.service.ticket.TicketServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class TicketsOverviewController {

    private final TicketServiceInterface service;

    @Autowired
    public TicketsOverviewController(TicketServiceInterface service) {
        this.service = service;
    }

    @CrossOrigin
    @RequestMapping(value="api/admin/tickets/overview/", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public TicketsOverviewResponseInterface cancellation(@RequestParam(value="startEventId",required = false) Long startEventId, @RequestParam(value="endEventId", required = false) Long endEventId) {
        log.debug("Request to TicketOverviewController");
        return service.getTicketOverview(startEventId,endEventId);
    }
}