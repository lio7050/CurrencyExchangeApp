package com.example.service;

import com.example.currencyexchange.BillRequest;
import com.example.exception.ExchangeRateFetchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@Service
public class CurrencyExchangeService {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeService.class);

    private static final String API_URL = "https://open.er-api.com/v6/latest/";

    private static final double EMPLOYEE_DISCOUNT = 0.7;
    private static final double AFFILIATE_DISCOUNT = 0.9;
    private static final double LONG_TERM_CUSTOMER_DISCOUNT = 0.95;
    private static final int DISCOUNT_PER_100 = 5;

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final ObjectProvider<CurrencyExchangeService> selfProvider;

    public CurrencyExchangeService(RestTemplate restTemplate, @Value("${exchange.api.key}") String apiKey, ObjectProvider<CurrencyExchangeService> selfProvider) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.selfProvider = selfProvider;
    }

    public double calculateFinalAmount(BillRequest billRequest) {
        double exchangeRate = selfProvider.getObject().getExchangeRate(billRequest.getOriginalCurrency(), billRequest.getTargetCurrency());
        double discountedAmount = applyDiscounts(billRequest);
        return discountedAmount * exchangeRate;
    }

    @Cacheable("exchangeRates")
    protected double getExchangeRate(String fromCurrency, String toCurrency) {
        String url = API_URL + fromCurrency + "?apikey=" + apiKey;
        logger.info("Fetching exchange rate from: {} for currencyType {}", API_URL, fromCurrency);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        if (response == null || !response.containsKey("rates")) {
            logger.error("Failed to fetch exchange rate");
            throw new ExchangeRateFetchException("Failed to fetch exchange rates");
        }

        Map<String, Double> rates = (Map<String, Double>) response.get("rates");
        return rates != null ? rates.getOrDefault(toCurrency, 1.0) : 1.0;
    }

    protected double applyDiscounts(BillRequest billRequest) {
        double total = billRequest.getTotalAmount();
        logger.info("Amount before discount {} ", total);
        if (!billRequest.isGrocery()) {
            if (billRequest.isEmployee()) {
                total *= EMPLOYEE_DISCOUNT;
            } else if (billRequest.isAffiliate()) {
                total *= AFFILIATE_DISCOUNT;
            } else if (billRequest.getCustomerTenure() > 2) {
                total *= LONG_TERM_CUSTOMER_DISCOUNT;
            }
        }

        total -= (int) (billRequest.getTotalAmount() / 100) * DISCOUNT_PER_100;
        logger.info("Amount After discount {} ", total);
        return total;
    }
}
