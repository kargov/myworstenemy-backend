package com.ned.admitone.jpa.service.api.exchange;

import com.ned.admitone.domain.api.exchange.request.ExchangeRequestInterface;
import com.ned.admitone.domain.api.exchange.response.ExchangeResponseInterface;

public interface ExchangeServiceInterface {
    ExchangeResponseInterface makeExchange(ExchangeRequestInterface request);
}
