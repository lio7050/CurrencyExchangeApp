package com.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyExchangeServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrencyExchangeService currencyExchangeService;

    @BeforeEach
    void setUp() {
        // Inject a mock API key
        ReflectionTestUtils.setField(currencyExchangeService, "apiKey", "test-api-key");
    }

    @Test
    void testExchangeRate() {
        // Prepare mock response data
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.85);

        Map<String, Object> response = new HashMap<>();
        response.put("rates", rates);

        // Mock RestTemplate call (ensuring partial URL match)
        when(restTemplate.getForObject(contains("USD"), eq(Map.class))).thenReturn(response);

        // Validate exchange rate retrieval
        double rate = currencyExchangeService.getExchangeRate("USD", "EUR");
        assertEquals(0.85, rate);
    }
}
