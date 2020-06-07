package md.uno.game.controllers;

import md.uno.game.Memory;
import md.uno.game.models.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController
{
    @GetMapping(value = "/")
    public String welcomePage(@CookieValue(name = "JSESSIONID", required = false) String jsession,
                              @RequestParam(name = "authAttempt", required = false) boolean authAttempt,
                              ModelMap modelMap)
    {
        Memory memory = Memory.getInstance();
        Player player = memory.findPlayerByJsession(jsession);
        if (player != null)
        {
            return "redirect:/lobby";
        }

        if (authAttempt)
        {
            modelMap.addAttribute("authAttempt", "<i> (try another login)</i>");
        }
        else
        {
            modelMap.addAttribute("authAttempt", "");
        }

        return "welcome";
    }
}
