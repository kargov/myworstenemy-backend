package com.ned.admitone.controller.api.cancellation;

import com.ned.admitone.domain.api.cancellation.request.CancellationRequestInterface;
import com.ned.admitone.domain.api.cancellation.response.CancellationResponseInterface;
import com.ned.admitone.jpa.service.api.cancellation.CancellationServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class CancellationController {

    private final CancellationServiceInterface service;

    @Autowired
    public CancellationController(CancellationServiceInterface service) {
        this.service = service;
    }

    @CrossOrigin
    @PostMapping(value="api/customers/cancellation/", produces = "application/json; charset=utf-8")
    @ResponseBody
    public CancellationResponseInterface cancellation(@RequestBody CancellationRequestInterface request) {
        log.debug("Request to CancellationController");
        return service.makeCancellation(request);
    }
}
