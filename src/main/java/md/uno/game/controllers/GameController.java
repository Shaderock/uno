package md.uno.game.controllers;

import md.uno.game.Memory;
import md.uno.game.TableHandler;
import md.uno.game.models.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController
{
    @GetMapping(value = "/startgame")
    public String startGame(@CookieValue(name = "JSESSIONID", required = false) String jsession,
                            ModelMap modelMap) throws Exception
    {
        Memory memory = Memory.getInstance();
        Player player = memory.findPlayerByJsession(jsession);
        if (player == null)
        {
            return "redirect:/";
        }

        if (memory.isPlayerInLobby(player))
        {
            memory.movePlayerToReadyToPlay(player);
        }

        if (memory.isPlayerInGame(player) || TableHandler.organizeNewTables(player))
        {
            return "redirect:/game";
        }

        return "startgame";
    }

    @GetMapping(value = "/game")
    public String gamePage(@CookieValue(name = "JSESSIONID", required = false) String jsession,
                           ModelMap modelMap)
    {
        Memory memory = Memory.getInstance();
        Player player = memory.findPlayerByJsession(jsession);
        if (player == null)
        {
            return "redirect:/";
        }

        if (!memory.isPlayerInGame(player))
        {
            return "redirect:/lobby";
        }

        modelMap.addAttribute("login", player.getLogin());

        return "game";
    }

    @GetMapping(value = "/endgame")
    public String endGame(@CookieValue(name = "JSESSIONID", required = false) String jsession)
    {
        Memory memory = Memory.getInstance();
        Player player = memory.findPlayerByJsession(jsession);
        if (player == null)
        {
            return "redirect:/";
        }

        if (!memory.isPlayerInLobby(player))
        {
            memory.removePlayerToLobby(player);
        }

        return "redirect:/lobby";
    }
}
