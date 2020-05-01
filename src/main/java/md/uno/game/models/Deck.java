package md.uno.game.models;

import md.uno.game.models.Cards.Card;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;

public class Deck extends TableComponent
{
    private final ArrayList<Card> cards;

    public Deck()
    {
        this.cards = new ArrayList<Card>();
    }

    public Deck(Card card)
    {
        this();
        cards.add(card);
    }

    public Deck(@NotNull ArrayList<Card> cards)
    {
        this.cards = (ArrayList<Card>) cards.clone();
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public void put(Card card)
    {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card);
        this.put(cards);
    }

    public void put(ArrayList<Card> cards)
    {
        for (Card card : cards)
        {
            if (!this.cards.contains(card))
            {
                this.cards.add(card);
            }
        }
    }


    public Card release()
    {
        return this.release(cards.size() - 1);
    }

    public Card release(int number)
    {
        try
        {
            Card releasedCard = this.cards.get(number);
            this.cards.remove(number);
            return releasedCard;

        } catch (IndexOutOfBoundsException e)
        {
            System.out.println("Exception!\nTried to release non exist card from deck.");
            return null;
        }
    }

    public ArrayList<Card> releaseSeveral(ArrayList<Integer> numbers)
    {
        ArrayList<Card> cards = new ArrayList<>();
        numbers.sort(Collections.reverseOrder());
        for (Integer integer : numbers)
        {
            cards.add(this.cards.get(integer));
            this.cards.remove((int) integer);
        }
        return cards;
    }
}
