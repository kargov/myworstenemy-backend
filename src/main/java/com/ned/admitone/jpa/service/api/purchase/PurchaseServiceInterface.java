package com.ned.admitone.jpa.service.api.purchase;


import com.ned.admitone.domain.api.purchase.request.PurchaseRequestInterface;
import com.ned.admitone.domain.api.purchase.response.PurchaseResponseInterface;

public interface PurchaseServiceInterface {
    PurchaseResponseInterface makePurchase(PurchaseRequestInterface purchaseRequest);
}
