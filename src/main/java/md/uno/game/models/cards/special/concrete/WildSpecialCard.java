package md.uno.game.models.cards.special.concrete;

import md.uno.game.models.ITable;
import md.uno.game.models.cards.Card;
import md.uno.game.models.cards.CardColor;
import md.uno.game.models.cards.special.SpecialCard;

public class WildSpecialCard extends SpecialCard
{
    public WildSpecialCard(CardColor cardColor)
    {
        super(cardColor);
    }

    public WildSpecialCard(Card card)
    {
        super(card);
    }

    @Override
    public void execute(ITable iTable)
    {
        iTable.askPlayerForNextColor();
        executeSuper(iTable);
    }
}
