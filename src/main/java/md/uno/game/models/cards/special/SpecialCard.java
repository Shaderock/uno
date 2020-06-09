package md.uno.game.models.cards.special;

import md.uno.game.models.ITable;
import md.uno.game.models.cards.Card;
import md.uno.game.models.cards.CardColor;

public abstract class SpecialCard extends Card implements ISpecialCard
{
    private final Card card;

    public SpecialCard(CardColor cardColor)
    {
        super(cardColor);
        card = null;
    }

    public SpecialCard(Card card)
    {
        super(card.getCardColor());
        this.card = card;
    }

    public Card getCard()
    {
        return card;
    }

    protected void executeSuper(ITable iTable)
    {
        if (card instanceof ISpecialCard)
        {
            ((ISpecialCard) card).execute(iTable);
        }
    }

	public void execute(ITable iTable){

	}
}
