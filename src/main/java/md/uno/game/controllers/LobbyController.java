package md.uno.game.controllers;

import md.uno.game.utils.Memory;
import md.uno.game.models.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LobbyController
{
    @GetMapping(value = "/lobby")
    public String lobbyPage(@CookieValue(name = "JSESSIONID", required = false) String jsession,
                            ModelMap modelMap) throws Exception
    {
        Memory memory = Memory.getInstance();
        Player player = memory.findPlayerByJsession(jsession);
        if (player == null)
        {
            return "redirect:/";
        }

        if (!memory.isPlayerInLobby(player))
        {
            return "redirect:/startgame";
        }

        modelMap.addAttribute("login", player.getLogin());

        return "lobby";
    }
}
