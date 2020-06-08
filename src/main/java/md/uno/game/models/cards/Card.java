package md.uno.game.models.cards;

public abstract class Card
{
    private final CardColor cardColor;

    public Card(CardColor cardColor)
    {
        this.cardColor = cardColor;
    }

    public CardColor getCardColor()
    {
        return cardColor;
    }
}
