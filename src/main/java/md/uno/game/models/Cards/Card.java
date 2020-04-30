package md.uno.game.models.Cards;

public abstract class Card
{
    private final Colors color;

    public Card(Colors color)
    {
        this.color = color;
    }

    public Colors getColor()
    {
        return color;
    }
}
