package md.uno.game.models;

import md.uno.game.models.cards.Card;

import java.util.ArrayList;
import java.util.Collections;

public class MainDeck extends Deck
{
    public MainDeck()
    {
        super();
    }

    public MainDeck(ArrayList<Card> cards)
    {
        super(cards);
    }

    public MainDeck(Card card)
    {
        super(card);
    }

    public Card getTop()
    {
        if (cards.size() != 0)
        {
            return cards.get(cards.size() - 1);
        }
        return null;
    }

    public ArrayList<Card> releaseForShuffle()
    {
//        Card topCard = this.stack.get(this.stack.size()-1);
//        this.stack.remove(this.stack.size()-1);
//        ArrayList<Card> releasedCards = (ArrayList<Card>) this.stack.clone();
//        this.stack.clear();
//        return releasedCards;

//        Integer[] array = new Integer[cards.size() - 1];
//        Arrays.setAll(array, p -> p);
//        ArrayList<Integer> orderNumbers = new ArrayList<>(Arrays.asList(array));
//        return releaseSeveral(orderNumbers);

        Collections.reverse(cards);
        return releaseSequence(cards.size()-1);
    }
}
