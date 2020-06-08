package md.uno.game.models;

public interface ITable
{
    void nextPlayerTakeCard();

    void nextPlayerSkip();

    void switchDirection();

    void askPlayerForNextColor();
}
