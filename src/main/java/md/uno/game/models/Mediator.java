package md.uno.game.models;

import md.uno.game.models.Cards.Card;

import java.util.ArrayList;

public interface Mediator
{
    public void interact(Mediator mediator, ArrayList<Card> cards);
}