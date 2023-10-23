package ma.enset.billingservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BillingRestControler {

    @Value("${token.accessTokenTimeOut}")
    private Long accesTokenTimeout;

    @Value("${token.refreshTokenTimeout}")
    private Long refreshTokenTimeOut;

    @GetMapping("/config")
    public Map<String, Long> config(){
        return Map.of("accesTokenTimeout",accesTokenTimeout,"refreshTokenTimeOut",refreshTokenTimeOut);
    }
}
