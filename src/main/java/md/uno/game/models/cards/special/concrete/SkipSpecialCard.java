package md.uno.game.models.cards.special.concrete;

import md.uno.game.models.ITable;
import md.uno.game.models.cards.Card;
import md.uno.game.models.cards.CardColor;
import md.uno.game.models.cards.special.SpecialCard;

public class SkipSpecialCard extends SpecialCard
{
    public SkipSpecialCard(CardColor cardColor)
    {
        super(cardColor);
    }

    public SkipSpecialCard(Card card)
    {
        super(card);
    }

    @Override
    public void execute(ITable iTable)
    {
        iTable.nextPlayerSkip();
        executeSuper(iTable);
    }
}
