package istad.co.gateway.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SecurityController {

    @GetMapping("/hello")
    Map<String, String> hello() {
        return Map.of("message", "Hello from Gateway");
    }

}
