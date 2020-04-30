package md.uno.game;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController
{
    @GetMapping("/index")
    public String index()
    {
        return "test";
    }
}
