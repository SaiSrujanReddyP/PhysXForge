/*
 * line be like ax + by + c = 0
 * -a/b = m;
 */
public class line extends shapes
{
    point start,end;
    double a,b,c;
    boolean finiteLine;
    line(point q1, point q2)
    {   //throw exception if q1 and q2 are the same point.
        if (q1.equals(q2)) {
            throw new IllegalArgumentException("The start and end points of a line cannot be the same.");
        }
        start = q1;
        end = q2;
        //finiteLine = true;
        b = start.x - end.x;
        a = end.y - start.y;
        c = -a * start.x - b * start.y;
        if(a == 0){
            start.y = -c/b;
            end.y = -c/b;
            start.x = 0;
            end.x = 500;
        }
        else{
            start.y = 0;
            end.y = 500;
            start.x = -1*(b*start.y + c)/a;
            end.x = -1*(b*end.y + c)/a;
            System.out.println("points are " + start.x + "," + start.y + " and " + end.x + "," + end.y);
        }
    }
    line(double a, double b, double c)
    {
        // if a, b are set to 0 throw an exception
        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("Coefficients 'a' and 'b' cannot both be zero.");
        }

        this.a = a;
        this.b = b;
        this.c = c;
        finiteLine = false;
    }
    public double lineLenght()
    {
        return start.distanceTo(end);
    }
    public double getAngle()  //returns angle between line and x-axis
    {
        return Math.atan2(a,b);
    }
    boolean intersects(circle c)
    {
        if(distanceToCircle(c) <= 0)
            return true;
        else return false;
    }
    double distanceToCircle(circle c)
    {
        System.out.println(Math.abs((this.a * c.center.x) + (this.b * c.center.y) + this.c) / Math.sqrt(a * a + b * b) - c.radius);
        return Math.abs((this.a * c.center.x) + (this.b * c.center.y) + this.c) / Math.sqrt(a * a + b * b) - c.radius;
    }
    point IntersectionPoint(line l1)
    {
        point tmp = new point(0,0);         
        //include exception for division by 0 here
        tmp.x = (this.b * l1.c - l1.b * this.c) / (this.a * l1.b - l1.a * this.b);
        tmp.y = (l1.a * this.c - this.a - l1.c) / (this.a * l1.b - l1.a * this.b);
        return tmp;
    }
}
