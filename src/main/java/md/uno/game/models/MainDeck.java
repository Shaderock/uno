package md.uno.game.models;

import md.uno.game.GameLogic;
import md.uno.game.models.Cards.Card;

import java.util.ArrayList;

public class MainDeck extends Deck
{
    private final ArrayList<Card> stack;

    public MainDeck()
    {
        this.stack = new ArrayList<>();
    }

    public MainDeck(ArrayList<Card> cards)
    {
        this.stack = cards;
    }

    public MainDeck(Card card)
    {
        this();
        this.stack.add(card);
    }

    public ArrayList<Card> getStack()
    {
        return stack;
    }

    public Card getTop()
    {
        return stack.get(stack.size() - 1);
    }

    public void put(Card card)
    {
        if (GameLogic.isPutable(this.getTop(), card))
        {
            stack.add(card);
        }
    }

    public ArrayList<Card> releaseForShuffle()
    {
        Card topCard = this.stack.get(this.stack.size()-1);
        this.stack.remove(this.stack.size()-1);
        ArrayList<Card> releasedCards = (ArrayList<Card>) this.stack.clone();
        this.stack.clear();
        return releasedCards;
    }
}
