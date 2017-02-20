package com.ned.admitone.domain.api.purchase.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ned.admitone.domain.api.ResponseInterface;

@JsonDeserialize(as=PurchaseResponse.class)
public interface PurchaseResponseInterface extends ResponseInterface {
}
