package com.ned.admitone.domain.api.exchange.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ned.admitone.domain.api.RequestInterface;

@JsonDeserialize(as=ExchangeRequest.class)
public interface ExchangeRequestInterface extends RequestInterface {
    Long getTargetEventId();
}
