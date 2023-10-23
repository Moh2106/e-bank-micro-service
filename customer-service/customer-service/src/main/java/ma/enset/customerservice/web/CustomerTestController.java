package ma.enset.customerservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class CustomerTestController {
    @Value("${global.params.g1}")
    private String p1;
    @Value("${global.params.g2}")
    private String p2;
    @Value("${customer.params.c1}")
    private String x;
    @Value("${customer.params.c2}")
    private String y;

    @GetMapping("/params")
    public Map<String, String> params(){
        return Map.of("p1",p1, "p2",p2, "c1", x, "c2", y);
    }
}
