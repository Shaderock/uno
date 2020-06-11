package md.uno.game.utils;

import md.uno.game.models.Player;
import md.uno.game.models.Table;
import md.uno.game.models.cards.Card;
import md.uno.game.models.cards.CardColor;
import md.uno.game.models.cards.NumberCard;
import md.uno.game.models.cards.special.concrete.PlusTwoSpecialCard;
import md.uno.game.models.cards.special.concrete.ReverseSpecialCard;
import md.uno.game.models.cards.special.concrete.SkipSpecialCard;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;

public class PageHelper //Helps to construct web-pages
{
    public static void gamePage(ModelMap modelMap, Table table, Player player)
    {
        ArrayList<Player> players = table.getPlayers();
        int playerThatMoves = table.getPlayerThatMoves();
        int playerNumber = players.indexOf(player);
        modelMap.addAttribute("playerHand", getPlayerHand(player,
                playerNumber == playerThatMoves));
        modelMap.addAttribute("playerTurn", playerThatMoves == playerNumber ? "class=\"player_turn\"" : "");

        modelMap.addAttribute("topCardColor", table.getTopCardColor().name());
        modelMap.addAttribute("canDraw", player.canTakeCard ? "class=\"can_draw\"" : "");
        modelMap.addAttribute("canSkip", player.canTakeCard ? "hidden" : "");
        modelMap.addAttribute("canChooseColor", playerThatMoves == playerNumber && table.isChangeColor() ? "" : "hidden");

        ArrayList<String> otherPlayersHands = new ArrayList<>();
        int otherPlayerNumber = playerNumber;
        do
        {
            otherPlayerNumber = table.getNextPlayerNumber(otherPlayerNumber);
            otherPlayersHands.add(getOtherPlayerHand(players.get(otherPlayerNumber)));
        }while (otherPlayerNumber != playerNumber);


        String leftPlayerHand = "";
        String leftPlayerTurn = "";
        String topPlayerHand;
        String topPlayerTurn;
        String rightPlayerHand = "";
        String rightPlayerTurn = "";
        otherPlayerNumber = table.getNextPlayerNumber(otherPlayerNumber);
        if (players.size() == 2)
        {
            topPlayerHand = otherPlayersHands.get(0);
            topPlayerTurn = playerThatMoves == otherPlayerNumber ? "class=\"player_turn\"" : "";
        }
        else {
            leftPlayerHand = otherPlayersHands.get(0);
            leftPlayerTurn = playerThatMoves == otherPlayerNumber ? "class=\"player_turn\"" : "";
            otherPlayerNumber = table.getNextPlayerNumber(otherPlayerNumber);

            topPlayerHand = otherPlayersHands.get(1);
            topPlayerTurn = playerThatMoves == otherPlayerNumber ? "class=\"player_turn\"" : "";

            if (players.size() == 4)
            {
                rightPlayerHand = otherPlayersHands.get(2);
                rightPlayerTurn = playerThatMoves == otherPlayerNumber ? "class=\"player_turn\"" : "";
            }
        }

        modelMap.addAttribute("leftPlayerHand", leftPlayerHand);
        modelMap.addAttribute("leftPlayerTurn", leftPlayerTurn);
        modelMap.addAttribute("topPlayerHand", topPlayerHand);
        modelMap.addAttribute("topPlayerTurn", topPlayerTurn);
        modelMap.addAttribute("rightPlayerHand", rightPlayerHand);
        modelMap.addAttribute("rightPlayerTurn", rightPlayerTurn);

        modelMap.addAttribute("drawPile", getDrawPile(table,
                playerNumber == playerThatMoves && player.canTakeCard).toString());
        modelMap.addAttribute("discardPile", getDiscardPile(table).toString());
    }

    private static StringBuilder getDiscardPile(Table table)
    {
        StringBuilder discardPile = new StringBuilder();
        discardPile.append(String.format("<div class=\"card top-card rand%d\">\n" +
                "                            <div class=\"bckg\" id=\"%s\"></div>\n" +
                "                        </div>", Math.round((Math.random()*8)+1),
                getCardId(table.getMainDeck().getTop())));
        for (int cardLeft = 0; cardLeft < 6 && cardLeft < table.getMainDeck().getSize()-1; cardLeft++)
        {
            discardPile.append(String.format("\n" +
                    "<div class=\"card pile_shadow rand%d\">\n" +
                    "                            <div class=\"bckg\" id=\"%s\"></div>\n" +
                    "                        </div>", Math.round((Math.random()*8)+1),
                    getCardId(table.getMainDeck().getCard(table.getMainDeck().getSize() - cardLeft - 2))));
        }
        return discardPile;
    }

    private static StringBuilder getDrawPile(Table table, boolean canDrawCard)
    {
        StringBuilder drawPile = new StringBuilder();
        for (int cardLeft = 0; cardLeft < 6 && cardLeft < table.getDeck().getSize()-1; cardLeft++)
        {
            drawPile.append("\n" +
                    "<div class=\"card turned pile_shadow\">\n" +
                    "                            <div class=\"bckg\"></div>\n" +
                    "                        </div>");
        }
        drawPile.append(String.format("\n" +
                "                        <div class=\"card turned top-card\" %s>\n" +
                "                            <div class=\"bckg\"></div>\n" +
                "                        </div>", canDrawCard ? "onclick=\"take()\"": ""));
        return drawPile;
    }

    private static String getOtherPlayerHand(Player player)
    {
        String cardTemplate = "<div class=\"card turned\">\n" +
                "                                <div class=\"bckg\"></div>\n" +
                "                            </div>";
        String start = "<p class=\"player_name\">%s</p>\n" +
                "                    <div class=\"player_hand\">" +
                "                       <div class=\"hand_row\">";
        String end = "\n" +
                "                        </div>\n" +
                "                   </div>";

        StringBuilder result = new StringBuilder(String.format(start, player.getLogin()));

        for (int cardNumber = 0; cardNumber < player.getCardNumber(); cardNumber++)
        {
            if (cardNumber != 0 && cardNumber % 10 == 0)
            {
                result.append("\n" +
                        "                        </div>\n" +
                        "                        <div class=\"hand_row\">");
            }

            result.append(cardTemplate);
        }

        result.append(end);

        return result.toString();
    }

    private static String getPlayerHand(Player player, boolean isPlayerTurn)
    {
        String cardTemplate = "<div class=\"card\" %s>\n" +    // todo release only your turn
                "                                <div class=\"bckg\" id=\"%s\"></div>\n" +
                "                            </div>";
        String start = "<p class=\"player_name\">%s (you)</p>\n" +
                "                    <div class=\"player_hand\">\n" +
                "                       <div class=\"hand_row\">";
        String end = "\n" +
                "                        </div>\n" +
                "                   </div>";

        StringBuilder result = new StringBuilder(String.format(start, player.getLogin()));

        for (int cardNumber = 0; cardNumber < player.getCardNumber(); cardNumber++)
        {
            if (cardNumber != 0 && cardNumber % 9 == 0)
            {
                result.append("\n" +
                        "                        </div>\n" +
                        "                        <div class=\"hand_row\">");
            }
            Card card = player.getCard(cardNumber);

            result.append(String.format(cardTemplate,
                    isPlayerTurn? String.format("onclick=\"release(%d)\"", cardNumber) : "",
                    getCardId(card)));
        }

        result.append(end);

        return result.toString();
    }

    private static String getCardId(Card card)
    {
        if (card.getCardColor().equals(CardColor.black))
        {
            if (card instanceof PlusTwoSpecialCard)
                return "draw4";

            return "wild";
        }

        if (card instanceof NumberCard)
        {
            return card.getCardColor().name().charAt(0) + ((NumberCard) card).getNumber().toString();
        }

        if (card instanceof SkipSpecialCard)
        {
            return card.getCardColor().name().charAt(0) + "skip";
        }

        if (card instanceof ReverseSpecialCard)
        {
            return card.getCardColor().name().charAt(0) + "reverse";
        }

        if (card instanceof PlusTwoSpecialCard)
        {
            return card.getCardColor().name().charAt(0) + "draw2";
        }

        return "";
    }
}
