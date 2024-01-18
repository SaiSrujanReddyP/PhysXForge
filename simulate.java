public class simulate{
    public static void main(String[] args)
    {
        world world = new world();

        new mainframe(world);

         /* 
        surface s1 = new surface(new point(0,20), new point(500,20));
        //round_block r = new round_block(new point(5,5),5);
        sphere sp1 = new sphere(new point(20,30),10);
        //sphere sp2 = new sphere(new point(10,20),30);
        //sp1.setVelocity(new vector(10,10));
        //r.interactWith(sp1);
        
        //sp1.updatePosition(0.1);
        s1.interactWith(sp1);
        System.out.println("new velocity of sp1 = " + sp1.velocity.x + "," + sp1.velocity.y);
        //System.out.println("new velocity of sp1 = " + sp1.velocity.x + "," + sp1.velocity.y);
        //sp1.updatePosition(0.1);
        //r.interactWith(sp1);
        //System.out.println("new velocity of sp1 = " + sp1.velocity.x + "," + sp1.velocity.y);
        //sp2.setVelocity(new vector(-5,5));
        //sp1.checkCollisionAndSetVelocity(sp2);
        //System.out.println("new velocity of sp1 = " + sp1.velocity.x + "," + sp1.velocity.y + " sp2 = " + sp2.velocity.x + "," + sp2.velocity.y);
        //s1.interactWith(sp1);
        
        System.out.println("a = " + s1.l1.a + " b = " + s1.l1.b + " c = " + s1.l1.c);
        //*/
        
    }
}