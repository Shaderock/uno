package md.uno.game.controllers;

import md.uno.game.Memory;
import md.uno.game.models.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController
{
    @GetMapping(value = "/game")
    public String gamePage(@CookieValue(name = "JSESSIONID", required = false) String jsession,
                           ModelMap modelMap)
    {
        if (jsession == null)
        {
            return "redirect:/";
        }

        Memory memory = Memory.getInstance();
        Player player = memory.findPlayerByJsession(jsession);
        if (player == null)
        {
            return "redirect:/";
        }

        modelMap.addAttribute("login", player.getLogin());

        return "game";
    }
}
