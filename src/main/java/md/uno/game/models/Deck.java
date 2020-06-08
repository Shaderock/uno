package md.uno.game.models;

import md.uno.game.models.cards.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Deck extends TableComponent
{
    protected final ArrayList<Card> cards;

    public Deck()
    {
        this.cards = new ArrayList<>();
    }

    public Deck(Card card)
    {
        this();
        cards.add(card);
    }

    public Deck(ArrayList<Card> cards)
    {
        this();
        this.cards.addAll(cards);
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public void put(Card card)
    {
        cards.add(card);
    }

    public void put(ArrayList<Card> cards)
    {
        this.cards.addAll(cards);
    }

    public Card getCard(int orderNumber)
    {
        try
        {
            return this.cards.get(orderNumber);
        } catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }

    public Card release()
    {
        return this.release(cards.size() - 1);
    }

    public Card release(int orderNumber)
    {
        try
        {
            Card releasedCard = this.cards.get(orderNumber);
            this.cards.remove(orderNumber);
            return releasedCard;

        } catch (IndexOutOfBoundsException e)
        {
            System.out.println("Exception!\nTried to release non exist card from deck.");
            return null;
        }
    }

    public ArrayList<Card> releaseSequence(int sequenceSize)
    {
        Integer[] array = new Integer[sequenceSize];
        Arrays.setAll(array, p -> cards.size() - p - 1);

        return releaseSeveral(new ArrayList<>(Arrays.asList(array)));
    }

    public ArrayList<Card> releaseSeveral(ArrayList<Integer> orderNumbers)
    {
        ArrayList<Card> cards = new ArrayList<>();
        orderNumbers.sort(Collections.reverseOrder());
        for (Integer integer : orderNumbers)
        {
            cards.add(this.cards.get(integer));
            this.cards.remove((int) integer);
        }
        return cards;
    }

    public void shiftCard(Deck deck, int orderNumber)
    {
        iMediator.shiftCard(this, deck, orderNumber);
    }

    public void release(Card card)
    {
        cards.remove(card);
    }

    public int getSize()
    {
        return cards.size();
    }
}
