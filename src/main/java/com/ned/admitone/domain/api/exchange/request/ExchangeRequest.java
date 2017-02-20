package com.ned.admitone.domain.api.exchange.request;

import com.ned.admitone.domain.api.AbstractRequest;
import lombok.Getter;
import lombok.Setter;

public class ExchangeRequest extends AbstractRequest implements ExchangeRequestInterface {
    @Getter
    @Setter
    private Long targetEventId;
}
