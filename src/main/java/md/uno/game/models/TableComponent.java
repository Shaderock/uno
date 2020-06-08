package md.uno.game.models;

public abstract class TableComponent
{
    protected IMediator iMediator;

    public void setIMediator(IMediator IMediator)
    {
        this.iMediator = IMediator;
    }
}
