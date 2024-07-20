public class circle extends shapes{
    public point center;
    double radius;
    public circle()
    {
        super();
        center = new point(0,0);
    }
    public circle(point p, double radius)
    {   //throw exception if radius is negative
        super();
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }

        center = new point(p);
        this.radius = radius;
    }
    public void setCenter(point p)
    {
        center = p;
    }
    public boolean intersects(circle c1)
    {
        if((this.center.distanceTo(c1.center) <= (c1.radius + this.radius)))
        {
            System.out.println("collision detected");
            return true;
        }
        else return false;
    }
    public double distanceTo(circle c2)
    {
        return this.center.distanceTo(c2.center) - this.radius - c2.radius;
    }
}
