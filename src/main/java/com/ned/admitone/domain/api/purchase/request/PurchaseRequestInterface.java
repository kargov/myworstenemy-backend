package com.ned.admitone.domain.api.purchase.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ned.admitone.domain.api.RequestInterface;

@JsonDeserialize(as=PurchaseRequest.class)
public interface PurchaseRequestInterface extends RequestInterface {

}
