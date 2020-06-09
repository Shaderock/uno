package md.uno.game.utils;

import md.uno.game.models.Table;

import java.util.ArrayList;

public class TablePool
{
    private static TablePool instance;
    private static int playerLimit;
    private static final int maxTableCount = 2;

    private final ArrayList<Table> availableTables = new ArrayList<>();
    private final ArrayList<Table> inUseTables = new ArrayList<>();

    private TablePool()
    {
        initiate();
    }

    public static TablePool getInstance() throws Exception
    {
        if (instance == null)
        {
            throw new Exception("Don't know playerLimit, thus can't create any table");
        }

        return instance;
    }

    public static TablePool getInstance(int playerLimit) throws Exception
    {
        if (instance != null)
        {
            throw new Exception("TablePool is already initiated");
        }

        TablePool.playerLimit = playerLimit;
        instance = new TablePool();

        return instance;
    }

    private void initiate()
    {
        for (int iteration = 0; iteration < maxTableCount; iteration++)
        {
            availableTables.add(new Table(playerLimit));
        }
    }

    public Table acquireTable()
    {
        if (availableTables.size() == 0)
        {
            return null;
        }
        Table table = availableTables.get(0);
        availableTables.remove(0);
        inUseTables.add(table);
        return table;
    }

    public void releaseTable(Table inUseTable)
    {
        if (inUseTables.remove(inUseTable))
        {
            availableTables.add(inUseTable);
        }
    }
}
