package md.uno.game.models;

import md.uno.game.models.Cards.Card;

import java.util.ArrayList;

public class Table implements Mediator
{
    private final ArrayList<Player> players;
    private int playerLimit;

    private MainDeck mainDeck;
    private Deck deck;

    public Table()
    {
        this.players = new ArrayList<>();
    }

    public Table(int playerLimit)
    {
        this();
        this.playerLimit = playerLimit;
    }

    public boolean addPlayer(Player player)
    {
        if (players.size() != playerLimit && !players.contains(player))
        {
            players.add(player);
            player.setMediator(this);
            return true;
        }
        return false;
    }

    public boolean removePlayer(Player player)
    {
        return players.remove(player);
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public int getPlayerLimit()
    {
        return playerLimit;
    }

    public void setPlayerLimit(int playerLimit)
    {
        this.playerLimit = playerLimit;
    }

    @Override
    public void interact(Mediator mediator, ArrayList<Card> cards)
    {

    }

    public void initiate()
    {

    }
}
