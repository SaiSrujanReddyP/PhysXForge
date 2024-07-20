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
           double angle = this.l1.getAngle();
           vector newVel = obj1.velocity.rotateBy(angle);
           vector pos = new vector(obj1.c.center);
           newVel.y = -1 * newVel.y;
           if(this.l1.distanceToCircle(obj1.c) < obj1.c.radius && (Math.abs(obj1.velocity.x) < 1 || Math.abs(obj1.velocity.y) < 1))
           {
                pos.rotateBy(angle);
                if(l1.a * obj1.c.center.x + l1.b * obj1.c.center.y + l1.c > 0)
                {
                    pos.y += this.l1.distanceToCircle(obj1.c);
                }
                else{
                    pos.y -= this.l1.distanceToCircle(obj1.c);
                }
                pos.rotateBy(-1*angle);
            }
            obj1.velocity = newVel.rotateBy(-1 * angle);

            obj1.c.center.set(pos);
        }
        else{
            //System.out.println("--no collision");
        }
    }
}
