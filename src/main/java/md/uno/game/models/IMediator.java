package md.uno.game.models;

import md.uno.game.models.cards.Card;

public interface IMediator
{
    void shiftCard(Deck fromDeck,
                   Deck toDeck,
                   int orderNumber);
}
