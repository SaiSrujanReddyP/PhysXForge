public abstract class moveableobject{
    public vector velocity;
    public point position;
    protected double mass;
    protected double density;
    moveableobject()
    {
        density = 1;   //mass is defined in KG
        position = new point(0,0);
        velocity = new vector();
        mass = 1;
    }
    public void setMass(double mass)
    {
        this.mass = mass;
    }
    public void setPos(point v1)
    {
        position = v1;  
    }
    public void setDensity(double density)
    {
        this.density = density;
    }
    public void setVelocity(vector v)
    {
        velocity = v;
    }
  
}