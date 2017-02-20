package com.ned.admitone.jpa.service.api.cancellation;

import com.ned.admitone.domain.api.cancellation.request.CancellationRequestInterface;
import com.ned.admitone.domain.api.cancellation.response.CancellationResponseInterface;

public interface CancellationServiceInterface {
    CancellationResponseInterface makeCancellation(CancellationRequestInterface request);
}
