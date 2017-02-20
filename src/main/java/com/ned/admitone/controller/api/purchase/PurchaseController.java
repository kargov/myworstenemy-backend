package com.ned.admitone.controller.api.purchase;

import com.ned.admitone.domain.api.purchase.request.PurchaseRequestInterface;
import com.ned.admitone.domain.api.purchase.response.PurchaseResponseInterface;
import com.ned.admitone.jpa.service.api.purchase.PurchaseServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class PurchaseController {

    private final PurchaseServiceInterface service;

    @Autowired
    public PurchaseController(PurchaseServiceInterface service) {
        this.service = service;
    }

    @CrossOrigin
    @PostMapping(value="api/customers/purchase/", produces = "application/json; charset=utf-8")
    @ResponseBody
    public PurchaseResponseInterface purchase(@RequestBody PurchaseRequestInterface request) {
        log.debug("Request to PurchaseController");
        return service.makePurchase(request);
    }
}
