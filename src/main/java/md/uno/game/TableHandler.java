package md.uno.game;

import md.uno.game.models.Player;

public class TableHandler
{
    private static final Memory memory = Memory.getInstance();

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

    public static boolean organizeNewTables(Player player) throws Exception
    {
        int readyToPlaySize = memory.getReadyToPlay().size();
        int minPlayers = memory.getMinPLayers();
        int maxPlayers = memory.getMaxPLayers();
        if (readyToPlaySize < minPlayers)
        {
            return false;
        }

        while (readyToPlaySize >= minPlayers)
        {
            if (readyToPlaySize > maxPlayers)
            {
                memory.createNewTableFromReadyToPlay(maxPlayers);
                readyToPlaySize -= maxPlayers;
            }
            else
            {
                memory.createNewTableFromReadyToPlay(readyToPlaySize);
                readyToPlaySize = 0;
            }
        }

        return memory.isPlayerInGame(player);
    }
}
