package gr.hua.dit.noc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class GovLoginController {

    @GetMapping("/gov/login")
    public String showLoginForm() {
        return "login-form";
    }

    @PostMapping("/gov/login")
    public String processLogin(@RequestParam String afm, @RequestParam String phone) {
        String redirectUrl = UriComponentsBuilder.fromUriString("http://localhost:8080/login/gov/callback")
                .queryParam("afm", afm)
                .queryParam("phone", phone)
                .encode()
                .toUriString();

        return "redirect:" + redirectUrl;
    }
}
