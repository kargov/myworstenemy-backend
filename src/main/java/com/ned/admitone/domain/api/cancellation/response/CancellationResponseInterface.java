package com.ned.admitone.domain.api.cancellation.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ned.admitone.domain.api.ResponseInterface;

@JsonDeserialize(as=CancellationResponse.class)
public interface CancellationResponseInterface extends ResponseInterface {
}
