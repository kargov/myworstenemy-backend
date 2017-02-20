package com.ned.admitone.domain.api.exchange.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ned.admitone.domain.api.ResponseInterface;

@JsonDeserialize(as=ExchangeResponse.class)
public interface ExchangeResponseInterface extends ResponseInterface {
}
