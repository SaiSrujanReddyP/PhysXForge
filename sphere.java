
public class sphere extends moveableobject{
    public circle c;
    sphere()
    {
        super();
        c = new circle(new point(0,0),1);
    }
    
    sphere(point p, double radius, double mass)
    {
        super();
        this.c = new circle(new point(p), radius);
        this.density = mass / ((4 / (double)3) * 3.1415 *radius * radius * radius);
    }

    public void setCenter(point p)
    {
        c.center = p;
    }
    public void updateVelocity(double time)         //time is the time interval of the world
    {
        double eta = 1.81e-5;
        vector dragForce = new vector();
        double drag = 6 * 3.1415 * eta * this.c.radius * this.velocity.x;
        dragForce.setVector(drag,0);
        drag = 6 * 3.1415 * eta * this.c.radius * this.velocity.y;
        dragForce.setVector(dragForce.x, drag);
        vector netForce = new vector();
        vector gravity = new vector(world.g.x * mass, world.g.y * mass);

        //added drag Force
        netForce.add(dragForce);
        netForce.add(gravity);
        vector netAccel = new vector();
        netAccel.setVector(netForce.x / mass, netForce.y / mass);
        
        // calculate final velocity
        this.velocity.calcVelocity(this.velocity, netAccel, time);
        
    }
    public point updatePosition(double time)
    {
        vector distanceChange = vector.calcDistance(this.velocity, time);
        this.c.center.push(distanceChange);
        return this.c.center;
    }

    public void checkCollisionAndSetVelocity(sphere s1)
    {
        if(s1.c.intersects(this.c)) //a collision is detected
        {
            //System.out.println("--------------------------------------collision detected");
            //System.out.println("previous velocities => " + this.velocity.x + ", " + this.velocity.y + " and " + s1.velocity.x + " , " + s1.velocity.y);
            double angle = 0;
            angle = Math.atan2(s1.c.center.y-this.c.center.y,s1.c.center.x-this.c.center.x);
            vector newVel1 = this.velocity.rotateBy(angle);
            vector newVel2 = s1.velocity.rotateBy(angle);
            double tmp;
            // get new values for y value of the vectors
            tmp = (this.mass - s1.mass)*(newVel1.x - newVel2.x) / (this.mass + s1.mass) + newVel2.x;
            newVel2.x = 2 * this.mass * (newVel1.x - newVel2.x) / (this.mass + s1.mass) + newVel2.x;
            newVel1.x = tmp;
            s1.velocity = newVel2.rotateBy(-1 * angle);
            this.velocity = newVel1.rotateBy(-1 * angle);
            //System.out.println("new velocities => " + this.velocity.x + ", " + this.velocity.y + " and " + s1.velocity.x + " , " + s1.velocity.y);
        }
        else{
            //System.out.println(";'no collision");
        }
    }
}
