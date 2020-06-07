package md.uno.game.models;

import md.uno.game.models.Cards.Card;

import java.util.ArrayList;

public class Table implements Mediator
{
    private final ArrayList<Player> players;
    private int playerLimit;

    private MainDeck mainDeck;  //Opened cards
    private Deck deck;          //Closed cards

    public Table()
    {
        this.players = new ArrayList<>();
    }

    public Table(int playerLimit)
    {
        this();
        this.playerLimit = playerLimit;
    }

    public void addPlayer(Player player) throws Exception
    {
        if (players.size() != playerLimit && !players.contains(player))
        {
            players.add(player);
            player.setMediator(this);
            return;
        }
        throw new Exception("Attempt to add more players than possible or dup player");
    }

    public void addPlayers(ArrayList<Player> players) throws Exception
    {
        if (this.players.size() + players.size() > playerLimit)
        {
            throw new Exception("Attempt to add more players than possible");
        }

        for (Player player: players)
        {
            addPlayer(player);
        }
    }

    public void removePlayer(Player player) // TODO: remove safely or not
    {
        players.remove(player);
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
    public void interact(TableComponent tableComponent, ArrayList<Card> cards) // TODO
    {

    }

    public void initiate() // TODO
    {

    }

    public boolean isEmpty()
    {
        return players.isEmpty();
    }
}
