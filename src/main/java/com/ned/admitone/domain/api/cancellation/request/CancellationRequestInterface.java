package com.ned.admitone.domain.api.cancellation.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ned.admitone.domain.api.RequestInterface;

@JsonDeserialize(as=CancellationRequest.class)
public interface CancellationRequestInterface extends RequestInterface {

}
