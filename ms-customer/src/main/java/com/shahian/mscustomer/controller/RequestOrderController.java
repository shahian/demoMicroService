package com.shahian.mscustomer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class RequestOrderController {
    private final RestTemplate restTemplate;
    @Autowired
    public RequestOrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @GetMapping(value = "/v1/requestOrderByCustomerId")
    public ResponseEntity<?> findByCustomerId(@RequestParam Long customerId) {
//        String url ="http://ms-order/api/v1/orderByCustomerId?customerId={customerId}";
//        Map<String, String> params = new HashMap<>();
//        params.put("customerId", "123");
//       String response=restTemplate.getForObject(url,String.class,params);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://ms-order/api/v1/orderByCustomerId?customerId={customerId}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {},
                customerId);
        if (response == null) {
            throw new NullPointerException("response is  empty");
        }
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }





}
