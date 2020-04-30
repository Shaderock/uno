package md.uno.game.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController
{
    @GetMapping(value = "/game")
    public String gamePage()
    {
        return "game";
    }
}
