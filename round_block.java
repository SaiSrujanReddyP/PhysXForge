public class round_block extends immovableObj{
    circle c;
    public round_block()
    {
        super();
        c = new circle();
    }
    public round_block(point center, double radius){
        super();
        c = new circle(center,radius);
    }
    public void interactWith(sphere obj1)
    {
        if(obj1.c.intersects(this.c))
        {
            //JOptionPane.showMessageDialog(null, "there is a collisiion!!","round block collision", JOptionPane.);
            double angle = 0;
            angle = Math.atan2(obj1.c.center.y-this.c.center.y,obj1.c.center.x-this.c.center.x);
            vector newVel1 = obj1.velocity.rotateBy(angle);
            newVel1.x = -1 * newVel1.x;
            obj1.velocity = newVel1.rotateBy(-angle);
            obj1.velocity.x = -obj1.velocity.x;
        }
        else{
            //System.out.println("+=+no collide");
        }
    }
}
