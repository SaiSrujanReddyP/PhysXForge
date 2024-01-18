public class vector{
    public double x, y;
    vector()
    {
        x = 0; 
        y = 0;
    }
    vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    public void add(vector v1)
    {
        x += v1.x;
        y += v1.y;
    }

    public void calcVelocity(vector initialVelocity, vector acceleration, double seconds)
    {
        this.x = initialVelocity.x + acceleration.x * seconds;
        this.y = initialVelocity.y + acceleration.y * seconds;
    }
    public static vector calcDistance(vector initialVelocity, vector acceleration, double seconds)
    {
        vector v1 = new vector();
        v1.x = initialVelocity.x * seconds + acceleration.x * seconds * seconds / 2;
        v1.y = initialVelocity.y * seconds + acceleration.y * seconds * seconds / 2;
        return v1;
    }
    public static vector calcDistance(vector initialVelocity, double seconds)
    {
        vector deltaDistance = new vector();
        deltaDistance.x = initialVelocity.x * seconds;
        deltaDistance.y = initialVelocity.y * seconds;
        return deltaDistance;
    }

    public void setVector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    public vector rotateBy(double angle)
    {
        vector tmp = new vector();
        tmp.x = Math.cos(angle) * this.x - Math.sin(angle) * this.y;
        tmp.y = Math.sin(angle) * this.x + Math.cos(angle) * this.y;
        return tmp;
    }
}