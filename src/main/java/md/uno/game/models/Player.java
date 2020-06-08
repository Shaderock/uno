package md.uno.game.models;

public class Player extends Deck
{
    private final String login;
    private final String jsession;

    public boolean canTakeCard = true;

    public Player(String login, String jsession)
    {
        this.login = login;
        this.jsession = jsession;
    }

    public String getLogin()
    {
        return login;
    }

    public String getJsession()
    {
        return jsession;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o.getClass() == Player.class)
        {
            return super.equals(o) ||
                    this.jsession.equals(((Player) o).jsession) ||
                    this.login.equals(((Player) o).login);
        }
        return false;
    }
}
