package md.uno.game.models.cards;

public abstract class Card
{
    private final CardColor cardColor;

    private int random;

    public int getRandom()
    {
        return random;
    }

    private static int setRandom()
    {
        return (int) Math.round((Math.random() * 9));
    }

    public void random()
    {
        random = setRandom();
    }

    public Card(CardColor cardColor)
    {
        this.cardColor = cardColor;
    }

    public CardColor getCardColor()
    {
        return cardColor;
    }
}
