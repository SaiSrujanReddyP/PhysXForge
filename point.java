public class point {
    public double x;
    public double y;
    public point()
    {
        x = 0; 
        y = 0;
    }
    public point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    public point(point p)
    {
        this.x = p.x;
        this.y = p.y;
    }
    public double distanceTo(point p1)
    {
        return Math.sqrt((x - p1.x)*(x - p1.x) + (y - p1.y) * (y - p1.y));
    }
    public void push(vector velocity)
    {
        this.x += velocity.x;
        this.y += velocity.y;
    }
}
