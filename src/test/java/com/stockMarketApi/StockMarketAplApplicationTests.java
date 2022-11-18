package com.stockMarketApi;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.stockMarketApi.Model.stock;
import com.stockMarketApi.Service.stockService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StockMarketAplApplicationTests {


    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private TestH2Repository h2Repository;

    @Autowired
    private stockService service;
    @Autowired
    private HttpServletRequest request;


    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/v1.0/market");
    }


    @Test
    public void addstock(){

        stock stok=new stock(1,123F,"facebook",12, LocalDateTime.now());

        String tocken="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEyMyIsImV4cCI6MTY2NzMwMDAwOSwiaWF0IjoxNjY3MjgyMDA5fQ._5DyjhsKwGWyOsMobfsY5VIdYOQ_uOhewgxcYm0pJr9GC2tu2ZBrdAFpmsWkgQQA9g9Akpgb5yPwSb7oP-x3sw";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+tocken);
        HttpEntity<stock> request = new HttpEntity<>(stok, headers);
        stock responce=restTemplate.postForObject(baseUrl+"/stock/add",request, stock.class);
        assertEquals("facebook",responce.getCompanyName());
        assertEquals(1, h2Repository.findAll().size());
    }
    @Test
    public void getstocks() {
        stock stock = new stock(1, 123F, "facebook", 12, LocalDateTime.now());
        String tocken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEyMyIsImV4cCI6MTY2NzMwMDAwOSwiaWF0IjoxNjY3MjgyMDA5fQ._5DyjhsKwGWyOsMobfsY5VIdYOQ_uOhewgxcYm0pJr9GC2tu2ZBrdAFpmsWkgQQA9g9Akpgb5yPwSb7oP-x3sw";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tocken);
        HttpEntity<stock> request = new HttpEntity<>(stock, headers);

        stock responce = restTemplate.postForObject(baseUrl + "/stock/add", request, stock.class);
//        List<stock> responcee = restTemplate.getForObject(baseUrl + "/stock/get", List.class);
        List<stock> responcee= restTemplate.exchange(baseUrl + "/stock/add", HttpMethod.GET, request, List.class).getBody();
        assertEquals(1, (responcee).size());
        assertEquals(1, h2Repository.findAll().size());
    }
    @Test
    public void gett() throws JsonProcessingException {
        stock stok = new stock(1, 123F, "facebook", 12, LocalDateTime.now());
        String tocken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEyMyIsImV4cCI6MTY2NzMwMDAwOSwiaWF0IjoxNjY3MjgyMDA5fQ._5DyjhsKwGWyOsMobfsY5VIdYOQ_uOhewgxcYm0pJr9GC2tu2ZBrdAFpmsWkgQQA9g9Akpgb5yPwSb7oP-x3sw";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tocken);
        HttpEntity<stock> request = new HttpEntity<>(stok, headers);
        stock responce = restTemplate.postForObject(baseUrl + "/stock/add", request, stock.class);
//
        List<stock> responcee= restTemplate.exchange(baseUrl + "/stock/12", HttpMethod.GET, request, List.class).getBody();
        assertEquals(1,(responcee).size());
        assertEquals(1, h2Repository.findAll().size());
    }
    private String createURLWithPort() {
        return "http://localhost:" + port + "/api/v1.0/market/stock";
    }
}
