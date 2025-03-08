package com.example.controller;

import com.example.currencyexchange.BillRequest;
import com.example.service.CurrencyExchangeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
class CurrencyExchangeController {
    private final CurrencyExchangeService exchangeService;

    public CurrencyExchangeController(CurrencyExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @PostMapping("/calculate")
    public double calculatePayableAmount(@RequestBody BillRequest billRequest) {
        return exchangeService.calculateFinalAmount(billRequest);
    }
}
