package md.uno.game.models;

import md.uno.game.models.cards.Card;
import md.uno.game.models.cards.CardColor;
import md.uno.game.models.cards.NumberCard;
import md.uno.game.models.cards.special.SpecialCard;
import md.uno.game.models.cards.special.concrete.PlusTwoSpecialCard;
import md.uno.game.models.cards.special.concrete.ReverseSpecialCard;
import md.uno.game.models.cards.special.concrete.SkipSpecialCard;
import md.uno.game.models.cards.special.concrete.WildSpecialCard;

import java.util.ArrayList;

public class Table implements IMediator, ITable
{
    private final ArrayList<Player> players;
    private final int playerLimit;

    private final MainDeck mainDeck;  // Opened cards
    private CardColor topCardColor;
    private final Deck deck;          // Closed cards

    private int playerThatMoves = 0;    // Player that should move next
    private boolean direction = true;   // true = forward direction, false = reverse
    private boolean isNextPlayerSkipping = false;
    private boolean changeColor = false;    //if true - before takeEffect() need to give player choose to change color

//    public Table()
//    {
//        this.players = new ArrayList<>();
//    }

    public Table(int playerLimit)
    {
//        this();
        mainDeck = new MainDeck();
        deck = new Deck();
        players = new ArrayList<>();
        this.playerLimit = playerLimit;
    }

    public void addPlayer(Player player) throws Exception
    {
        if (players.size() != playerLimit && !players.contains(player))
        {
            players.add(player);
            player.setIMediator(this);
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

        for (Player player : players)
        {
            addPlayer(player);
        }
    }

    public void removePlayer(Player player)
    {
        while (player.getSize() != 0)
        {
            player.shiftCard(deck, 0);
        }

        if (playerThatMoves > players.indexOf(player))
        {
            playerThatMoves--;
        }

        players.remove(player);
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public MainDeck getMainDeck()
    {
        return mainDeck;
    }

    public Deck getDeck()
    {
        return deck;
    }

    public Card getTopMainDeck()
    {
        return mainDeck.getTop();
    }

//    public int getPlayerLimit()
//    {
//        return playerLimit;
//    }
//
//    public void setPlayerLimit(int playerLimit)
//    {
//        this.playerLimit = playerLimit;
//    }

    @Override
    public void shiftCard(Deck fromDeck, Deck toDeck, int orderNumber)
    {
        Card card = fromDeck.release(orderNumber);
        toDeck.put(card);
    }

    public void initiate()
    {
        initiateDeck();
        giveStartCards();
        putFirstCardToMainDeck();
    }

    private void putFirstCardToMainDeck()
    {
        for(int cardNumber = 0; cardNumber < deck.getSize(); cardNumber++)
        {
            if (! (deck.getCard(0) instanceof SpecialCard))
            {
                deck.shiftCard(mainDeck, cardNumber);
                return;
            }
        }
    }

    private void giveStartCards()
    {
        for (int iteration = 0; iteration < 7; iteration++)
        {
            for (Player player : players)
            {
                deck.shiftCard(player, deck.getSize() - 1);
            }
        }
    }

    private void initiateDeck()
    {
        // one 0 of each color
        for (CardColor cardColor : CardColor.values())
        {
            if (cardColor.equals(CardColor.black))
            {
                continue;
            }

            deck.put(new NumberCard(cardColor, 0));

        }

        // two ([1-9] + specialColors) of each color
        for (int iteration = 0; iteration < 2; iteration++)
        {
            for (CardColor cardColor : CardColor.values())
            {
                if (cardColor.equals(CardColor.black))
                {
                    continue;
                }

                for (int cardNumber = 1; cardNumber < 10; cardNumber++)
                {
                    deck.put(new NumberCard(cardColor, cardNumber));
                }

                //Special color cards
                deck.put(new SkipSpecialCard(cardColor));
                deck.put(new ReverseSpecialCard(cardColor));
                deck.put(new PlusTwoSpecialCard(new SkipSpecialCard(cardColor)));
            }
        }

        // four wild and wild+4 cards
        for (int iteration = 0; iteration < 4; iteration++)
        {
            deck.put(new WildSpecialCard(CardColor.black));
            deck.put(new PlusTwoSpecialCard(new PlusTwoSpecialCard(new SkipSpecialCard(new WildSpecialCard(CardColor.black)))));

        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public int getNextPlayerNumber()
    {
        int number = playerThatMoves;
        number += (direction) ? 1 : -1;
        try
        {
            players.get(number);
        } catch (IndexOutOfBoundsException e)
        {
            number = (direction) ? 0 : players.size() - 1;
        }

        return number;
    }

    public boolean isEmpty()
    {
        return players.isEmpty();
    }


    // ITable implementations

    @Override
    public void nextPlayerTakeCard()
    {
        players.get(getNextPlayerNumber()).shiftCard(deck, deck.getSize() - 1);
    }

    @Override
    public void nextPlayerSkip()
    {
        isNextPlayerSkipping = true;
    }

    @Override
    public void switchDirection()
    {
        direction = !direction;
    }

    @Override
    public void askPlayerForNextColor()
    {
        changeColor = true;
        players.get(playerThatMoves).canTakeCard = false;
    }

    public void nextMove()
    {
        if (changeColor)
        {
            return;
        }

        playerThatMoves = getNextPlayerNumber();
        if (isNextPlayerSkipping)
        {
            playerThatMoves = getNextPlayerNumber();
            isNextPlayerSkipping = false;
        }
    }

    public boolean isPlayerNotMoving(Player player)
    {
        return !player.equals(players.get(playerThatMoves));
    }

    public boolean isChangeColor()
    {
        return changeColor;
    }

    public CardColor getTopCardColor()
    {
        return topCardColor;
    }

    public void setTopCardColor(CardColor topCardColor)
    {
        this.topCardColor = topCardColor;
        changeColor = false;
    }

    public boolean checkForEndGame()
    {
        return players.size() <= 1;
    }
}
