package com.example.controller;

import com.example.currencyexchange.BillRequest;
import com.example.service.CurrencyExchangeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
class CurrencyExchangeController {
    @Autowired
    private CurrencyExchangeService exchangeService;

    @PostMapping("/calculate")
    public double calculatePayableAmount(@RequestBody BillRequest billRequest) {
        return exchangeService.calculateFinalAmount(billRequest);
    }
}