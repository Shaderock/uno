package md.uno.game.models.cards;

public class NumberCard extends Card
{
    private final Integer number;

    public NumberCard(CardColor cardColor, int number)
    {
        super(cardColor);
        this.number = number;
    }

    public Integer getNumber()
    {
        return number;
    }
}
