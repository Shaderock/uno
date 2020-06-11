package md.uno.game.utils;

import md.uno.game.models.Player;
import md.uno.game.models.Table;
import md.uno.game.models.cards.Card;
import md.uno.game.models.cards.CardColor;
import md.uno.game.models.cards.special.SpecialCard;

public class TableHandler
{
    private static Memory memory;

    static
    {
        try
        {
            memory = Memory.getInstance();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Can fail only if login is already used
    public static boolean addPlayerToLobby(Player newPlayer)
    {
        Player player = memory.findPlayerByLogin(newPlayer.getLogin());
        if (player != null)
        {
            return false;
        }

        memory.addPlayerToLobby(newPlayer);
        return true;
    }

    public static void organizeNewTables(Player player) throws Exception
    {
        int readyToPlaySize = memory.getReadyToPlay().size();
        int minPlayers = memory.getMinPLayers();
        int maxPlayers = memory.getMaxPLayers();
        if (readyToPlaySize < minPlayers)
        {
            return;
        }

        while (readyToPlaySize >= minPlayers)
        {
            if (readyToPlaySize > maxPlayers)
            {
                if (!memory.createNewTableFromReadyToPlay(maxPlayers))
                {
                    return;
                }
                readyToPlaySize -= maxPlayers;
            } else
            {
                if (memory.createNewTableFromReadyToPlay(readyToPlaySize))
                {
                    return;
                }
                readyToPlaySize = 0;
            }
        }
    }

    public static void playerReleaseCard(Player player, Integer orderNumber)
    {
        Table table = memory.findTableByPlayer(player);
        if (table == null)
        {
            return;
        }

        if (table.isPlayerNotMoving(player))
        {
            return;
        }

        Card shiftedCard = player.getCard(orderNumber);

        if (GameLogic.isPutable(table.getTopMainDeck(), table.getTopCardColor(), shiftedCard))
        {
            player.canTakeCard = true;
            if (shiftedCard instanceof SpecialCard)
            {
                ((SpecialCard) shiftedCard).execute(table);
            }
            table.nextMove();
            player.shiftCard(table.getMainDeck(), orderNumber);

            if (checkPlayerWin(player))
            {
                memory.removePlayerToLobby(player);
            }
        }
    }

    private static boolean checkPlayerWin(Player player)
    {
        return player.getSize() == 0;
    }

    public static void changeTopCardColor(Player player, CardColor cardColor)
    {
        if (cardColor == null || cardColor.equals(CardColor.black))
        {
            return;
        }

        Table table = memory.findTableByPlayer(player);
        if (table == null)
        {
            return;
        }

        if (!table.isChangeColor() || table.isPlayerNotMoving(player))
        {
            return;
        }

        table.setTopCardColor(cardColor);
        table.nextMove();
    }

    public static void playerTakeCard(Player player)
    {
        if (!player.canTakeCard)
        {
            return;
        }

        Table table = memory.findTableByPlayer(player);
        if (table == null)
        {
            return;
        }

        if (table.isPlayerNotMoving(player))
        {
            return;
        }

        try
        {
            table.getDeck().shiftCard(player, table.getDeck().getSize() - 1);
        } catch (IndexOutOfBoundsException e)
        {
            for (int i = 0; i < table.getMainDeck().getSize() - 1; i++)
            {
                table.getMainDeck().shiftCard(table.getDeck(), 0);
            }
            table.getDeck().shuffle();
            table.getDeck().shiftCard(player, table.getDeck().getSize() - 1);
        }

        player.canTakeCard = false;
    }

    public static void playerSkip(Player player)
    {
        if (player.canTakeCard)
        {
            return;
        }

        Table table = memory.findTableByPlayer(player);
        if (table == null)
        {
            return;
        }

        if (table.isPlayerNotMoving(player))
        {
            return;
        }

        player.canTakeCard = true;
        table.nextMove();
    }
}
