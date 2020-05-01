package md.uno.game.controllers;

import md.uno.game.Memory;
import md.uno.game.models.Player;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController
{
    @GetMapping(value = "/login")
    public String loginRedirect(@CookieValue(name = "JSESSIONID", required = false) String jsession,
                                @RequestParam(name = "login") String login)
    {
        Memory memory = Memory.getInstance();
        Player player = memory.findPlayerByJsession(jsession);
        if (player != null)
        {
            return "redirect:/game";
        }

        Player newPlayer = new Player(login, jsession);

        if (memory.getTable().addPlayer(newPlayer))
        {
            return "redirect:/game";
        }

        return "redirect:/";
    }
}
