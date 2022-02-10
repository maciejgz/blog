package pl.mg.blog.keycloak;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class TestController {

    @RolesAllowed("user")
    @GetMapping("/user")
    public String getUser() {
        return "user";
    }

    @RolesAllowed("admin")
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

}
