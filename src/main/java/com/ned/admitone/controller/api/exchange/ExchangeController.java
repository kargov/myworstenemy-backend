package com.ned.admitone.controller.api.exchange;

import com.ned.admitone.domain.api.exchange.request.ExchangeRequestInterface;
import com.ned.admitone.domain.api.exchange.response.ExchangeResponseInterface;
import com.ned.admitone.jpa.service.api.exchange.ExchangeServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class ExchangeController {

    private final ExchangeServiceInterface service;

    @Autowired
    public ExchangeController(ExchangeServiceInterface service) {
        this.service = service;
    }

    @CrossOrigin
    @PostMapping(value="api/customers/exchange/", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ExchangeResponseInterface exchange(@RequestBody ExchangeRequestInterface request) {
        log.debug("Request to ExchangeController");
        return service.makeExchange(request);
    }
}
