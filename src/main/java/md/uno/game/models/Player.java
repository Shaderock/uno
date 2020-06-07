package md.uno.game.models;

enum State
{
    notReady,
    ready,
    waiting,
    playing,
    afk
}

// TODO: Разобраться со статусами (скорее всего - удалить)

public class Player extends Deck
{
    private final String login;
    private boolean isPlaying = false;
    private final String jsession;

    public Player(String login, String jsession)
    {
        this.login = login;
        this.jsession = jsession;
    }

    public boolean isPlaying()
    {
        return isPlaying;
    }

    public void setPlaying(boolean playing)
    {
        isPlaying = playing;
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
