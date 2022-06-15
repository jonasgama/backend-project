package com.ninjaone.order.infra.controller;

import com.ninjaone.order.domain.dto.OrderDTO;
import com.ninjaone.order.domain.dto.OrderTransactionDTO;
import com.ninjaone.order.usecase.OrderUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderUseCase useCase;

    public OrderController(OrderUseCase useCase){
        this.useCase = useCase;
    }

    @PostMapping("/devices")
    @ResponseStatus(HttpStatus.CREATED)
    private void post(@RequestBody OrderDTO purchase) throws Exception {
          useCase.includeDevicesAndServices(purchase);
    }

    @GetMapping("/transactions/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    private OrderTransactionDTO getByMonth(
                                    @PathVariable String customerId,
                                    @RequestParam(value="month", required=false, defaultValue = "") Integer month,
                                    @RequestParam(value="year", required=false, defaultValue = "") Integer year) throws Exception {

        if(month == null){
            month = LocalDate.now().getMonthValue();
        }
        if(year == null){
            year = LocalDate.now().getYear();
        }

        return useCase.filterReportsByMonth(customerId, year, month);
    }

}
