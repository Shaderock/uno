package md.uno.game.models.cards.special.concrete;

import md.uno.game.models.ITable;
import md.uno.game.models.cards.Card;
import md.uno.game.models.cards.CardColor;
import md.uno.game.models.cards.special.SpecialCard;

public class PlusTwoSpecialCard extends SpecialCard
{
    public PlusTwoSpecialCard(CardColor cardColor)
    {
        super(cardColor);
    }

    public PlusTwoSpecialCard(Card card)
    {
        super(card);
    }

    @Override
    public void execute(ITable iTable)
    {
        iTable.nextPlayerTakeCard();
        iTable.nextPlayerTakeCard();
        executeSuper(iTable);
    }
}
