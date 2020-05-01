package md.uno.game.controllers;

import md.uno.game.Memory;
import md.uno.game.models.Player;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController
{
    @GetMapping(value = "/")
    public String welcomePage(@CookieValue(name = "JSESSIONID", required = false) String jsession)
    {
        Memory memory = Memory.getInstance();
        Player player = memory.findPlayerByJsession(jsession);
        if (player != null)
        {
            return "redirect:/game";
        }
        return "main";
    }
}
