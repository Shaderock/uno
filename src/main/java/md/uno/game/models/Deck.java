package md.uno.game.models;

import md.uno.game.models.Cards.Card;

import java.util.ArrayList;

public class Deck
{
    private final ArrayList<Card> cards;

    public Deck(ArrayList<Card> cards)
    {
        this.cards = cards;
    }

    public Deck()
    {
        this.cards = new ArrayList<Card>();
    }

}
