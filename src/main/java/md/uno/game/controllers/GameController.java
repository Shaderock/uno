package md.uno.game.controllers;

import md.uno.game.models.Player;
import md.uno.game.models.cards.CardColor;
import md.uno.game.utils.Memory;
import md.uno.game.utils.TableHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        if (memory.isPlayerInGame(player))
        {
            return "redirect:/game";
        }

        TableHandler.organizeNewTables(player);

        return "startgame";
    }

    @GetMapping(value = "/game")
    public String gamePage(@CookieValue(name = "JSESSIONID", required = false) String jsession,
                           ModelMap modelMap) throws Exception
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

        return "game";  // TODO: generate page
    }

    @GetMapping(value = "/game/release")
    public String actionRelease(@CookieValue(name = "JSESSIONID", required = false) String jsession,
                                @RequestParam(name = "ordernumber") Integer orderNumber) throws Exception
    {
        Memory memory = Memory.getInstance();
        Player player = memory.findPlayerByJsession(jsession);
        if (player == null)
        {
            return "redirect:/";
        }

        TableHandler.playerReleaseCard(player, orderNumber);
        return "redirect:/game";
    }

    @GetMapping(value = "/game/take")
    public String actionTake(@CookieValue(name = "JSESSIONID", required = false) String jsession) throws Exception
    {
        Memory memory = Memory.getInstance();
        Player player = memory.findPlayerByJsession(jsession);
        if (player == null)
        {
            return "redirect:/";
        }

        TableHandler.playerTakeCard(player);

        return "redirect:/game";
    }

    @GetMapping(value = "/game/color")
    public String actionColor(@CookieValue(name = "JSESSIONID", required = false) String jsession,
                              @RequestParam(name = "color") CardColor cardColor) throws Exception
    {
        Memory memory = Memory.getInstance();
        Player player = memory.findPlayerByJsession(jsession);
        if (player == null)
        {
            return "redirect:/";
        }

        TableHandler.changeTopCardColor(player, cardColor);

        return "redirect:/game";
    }

    @GetMapping(value = "/game/skip")
    public String actionSkip(@CookieValue(name = "JSESSIONID", required = false) String jsession) throws Exception
    {
        Memory memory = Memory.getInstance();
        Player player = memory.findPlayerByJsession(jsession);
        if (player == null)
        {
            return "redirect:/";
        }

        TableHandler.playerSkip(player);

        return "redirect:/game";
    }

    @GetMapping(value = "/endgame")
    public String endGame(@CookieValue(name = "JSESSIONID", required = false) String jsession) throws Exception
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
