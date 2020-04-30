package md.uno.game.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController
{
    @GetMapping(value = "/")
    public String welcomePage(@CookieValue(name = "JSESSION", required = false) String jsession)
    {
        return "main";
    }
}
