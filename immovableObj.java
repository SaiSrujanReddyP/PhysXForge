public abstract class immovableObj{
    public enum type{
        RoundBlock,
        Surface
    }
    public type objectType;
    immovableObj()
    {
        //write whatever
    }
    public abstract void interactWith(sphere obj1);
}
