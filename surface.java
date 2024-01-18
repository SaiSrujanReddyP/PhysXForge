public class surface extends immovableObj{
    line l1;
    public surface(double a, double b, double c)
    {
        super();
        l1 = new line(a,b,c);
    }
    public surface(point p1, point p2)
    {
        super();
        l1 = new line(p1,p2);
    }
    public void interactWith(sphere obj1)
    {
        if(this.l1.intersects(obj1.c))
        {
            //System.out.println("--collision detected");
            //System.out.println("circle is at " + obj1.c.center.x + "," +  obj1.c.center.y);
           // get angle of line with x-axis
           double angle = this.l1.getAngle();
           // then get new components of velocity with respect to the surface
           vector newVel = obj1.velocity.rotateBy(angle);
           // reverse the y velocity after doing that
           newVel.y = -1 * newVel.y;
           // get back the old vector
           obj1.velocity = newVel.rotateBy(-1 * angle);
        }
        else{
            //System.out.println("--no collision");
        }
    }
}
