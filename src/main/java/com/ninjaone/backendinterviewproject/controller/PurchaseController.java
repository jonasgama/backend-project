package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.PurchaseDTO;
import com.ninjaone.backendinterviewproject.usecase.PurchaseUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseUseCase useCase;

    public PurchaseController(PurchaseUseCase useCase){
        this.useCase = useCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void post(@RequestBody PurchaseDTO purchase) throws Exception {
          useCase.confirmPurchase(purchase);
    }
}