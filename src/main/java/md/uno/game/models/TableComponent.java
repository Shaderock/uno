package md.uno.game.models;

public abstract class TableComponent
{
    private Mediator mediator;

    public Mediator getMediator()
    {
        return mediator;
    }

    public void setMediator(Mediator mediator)
    {
        this.mediator = mediator;
    }
}
