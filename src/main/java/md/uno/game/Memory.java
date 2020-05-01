package md.uno.game;

import md.uno.game.models.Player;
import md.uno.game.models.Table;

public class Memory
{
    private static final Memory instance = new Memory();

    private Memory()
    {
    }

    public static Memory getInstance()
    {
        return instance;
    }

    private Table table = new Table(2);

    public Table getTable()
    {
        return table;
    }

    public Player findPlayerByJsession(String jsession)
    {
        for (Player player : table.getPlayers())
        {
            if (player.getJsession().equals(jsession))
            {
                return player;
            }
        }

        return null;
    }

    public Player findPlayerByLogin(String login)
    {
        for (Player player : table.getPlayers())
        {
            if (player.getLogin().equals(login))
            {
                return player;
            }
        }

        return null;
    }
}
