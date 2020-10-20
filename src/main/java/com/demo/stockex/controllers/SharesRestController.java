package com.demo.stockex.controllers;

import com.demo.stockex.bootstrap.DataBootstrap;
import com.demo.stockex.domain.Share;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class SharesRestController {

    private final RestTemplateBuilder restTemplateBuilder;

    private String testToken;

    //Getting token
    {
        try {
            testToken = DataBootstrap.getToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Double getSharePrice(Share share) {
        RestTemplate rest = restTemplateBuilder.build();
        Share responseShare = rest.getForObject("https://sandbox.iexapis.com/stable/stock/{symbol}/quote?token={token}",
                Share.class, share.getSymbol(), testToken);
        assert responseShare != null;
        return responseShare.getPrice();
    }

    public Share getShareFromIEX(String symbol) {
        RestTemplate rest = restTemplateBuilder.build();
        return rest.getForObject("https://sandbox.iexapis.com/stable/stock/{symbol}/quote?token={token}",
                Share.class, symbol, testToken);
    }

//    //Gets list of all symbols
//    public List<Share> getAllSymbols() {
//        RestTemplate rest = restTemplateBuilder.build();
//        ResponseEntity<Share[]> shareResponse =
//        rest.getForEntity("https://sandbox.iexapis.com/beta/ref-data/symbols?token={token}", Share[].class, testToken);
//        return Arrays.asList(shareResponse.getBody());
//    }

}
