package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.core.dto.PurchaseDTO;
import com.ninjaone.backendinterviewproject.shop.transaction.usecase.PurchaseUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseUseCase useCase;

    public PurchaseController(PurchaseUseCase useCase){
        this.useCase = useCase;
    }

    @PostMapping("/devices")
    @ResponseStatus(HttpStatus.CREATED)
    private void post(@RequestBody PurchaseDTO purchase) throws Exception {
          useCase.includeDevicesAndServices(purchase);
    }

    @GetMapping("/reports/{traceId}")
    @ResponseStatus(HttpStatus.OK)
    private List<Object> getByMonth(@PathVariable UUID traceId,
                                    @RequestParam(value="month", required=false, defaultValue = "") Integer month,
                                    @RequestParam(value="year", required=false, defaultValue = "") Integer year) throws Exception {

        if(month == null){

        }
        if(year == null){

        }
        useCase.
    }

}
