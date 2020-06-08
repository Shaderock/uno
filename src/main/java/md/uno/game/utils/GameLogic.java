package md.uno.game.utils;

import md.uno.game.models.cards.Card;
import md.uno.game.models.cards.CardColor;
import md.uno.game.models.cards.NumberCard;

public class GameLogic
{
    public static boolean isPutable(Card stackTop, CardColor topCardColor, Card newCard)
    {
        if (newCard == null)
            return false;

        if (newCard.getCardColor().equals(CardColor.black))
        {
            return true;
        }

        if (stackTop.getCardColor().equals(CardColor.black))
        {
            return topCardColor.equals(newCard.getCardColor());
        }

        if (stackTop.getCardColor().equals(newCard.getCardColor()))
        {
            return true;
        }

        if (stackTop.getClass().equals(newCard.getClass()))
        {
            if (stackTop instanceof NumberCard)
            {
                return ((NumberCard) stackTop).getNumber().equals(((NumberCard) newCard).getNumber());
            }

            return true;
        }

        return false;
    }
}
