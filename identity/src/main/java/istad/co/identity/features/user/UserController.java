package istad.co.identity.features.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    @GetMapping
    Map<String, String> hello() {
        return Map.of("message", "Hello from Gateway");
    }

}
