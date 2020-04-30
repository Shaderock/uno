package md.uno.game.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController
{
    @GetMapping(value = "/login")
    public String loginRedirect(@CookieValue(name = "JSESSION", required = false) String jsession,
                                @RequestParam(name = "login", required = true) String login)
    {
        return "redirect:/game";
    }
}
