import java.util.Scanner;

import javax.swing.JOptionPane;

import java.util.ArrayList;

public class world{
    static double current_time, time_increment, max_time;
    static ArrayList<sphere> balls = new ArrayList<sphere>();
    static ArrayList<surface> lines = new ArrayList<surface>();
    static ArrayList<round_block> circles = new ArrayList<round_block>();
    static ArrayList<point[]> postions = new ArrayList<point[]>();
    static ArrayList<point> initpos = new ArrayList<point>();
    static ArrayList<vector> initvelocities = new ArrayList<vector>();
    static vector g = new vector(0, -9.8);        //gravity;
    Scanner sc;

    public world()
    {
        current_time = 0;
        time_increment = 0.01;
        max_time = 100;
        sc = new Scanner(System.in);
    }
    
    public void setGravity(double g)
    {
        world.g.y = -g;
    }

    public void displayStats()
    {
        System.out.println("current time : " + current_time);
        System.out.println("g : " + g.y);
        System.out.println("time_increment : " + time_increment);
        System.out.println("max_time : " + max_time);
    }

    public void setIncTime(double seconds){
        if(seconds < 0)
        {
            System.out.println("!!!increment time cannot be negative!!!");          //change to exception
            System.out.println("setting increment_time to " + time_increment);
        }
        else
            time_increment = seconds;
    }

    public void setMaxTime(double seconds)
    {
        max_time = seconds;
    }

    public boolean worldEnd()
    {
        if(current_time < max_time)
            return false;
        else
            return true;
    }
    public void incTime()
    {
        current_time += time_increment;
    }   

    //use the following functions to insert objects into the world.
    public static void addSphere(point center, vector velocity, double radius, double mass)
    {
        //add new sphere to the set of objects.
        sphere s1 = new sphere();
        try{
            s1 = new sphere(center,radius,mass);  //add exceptions in case the sphere is kept in an illegal place, i.e. it is intersecting with another object on creation itself
        }catch(IllegalArgumentException i){
            JOptionPane.showMessageDialog(null, i.getMessage(),"invalid values!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        s1.setVelocity(velocity);
        initvelocities.add(s1.velocity);
        initpos.add(s1.c.center);
        balls.add(s1);
    }
    public static void addSurface(double a, double b, double c)
    {
        surface s = new surface(0,1,0);
        try{
        s = new surface(a,b,c);
        }catch(IllegalArgumentException i){
            JOptionPane.showMessageDialog(null, i.getMessage(),"invalid values!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        lines.add(s);
    }
    public static void addSurface(point p1, point p2)
    {
        surface s = new surface(new point(0,0), new point(500,0));
        try{
        s = new surface(p1,p2);
        }catch(IllegalArgumentException i){
            JOptionPane.showMessageDialog(null, i.getMessage(),"invalid values!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        lines.add(s);
    }
    public static void addRoundBlock(point center, double radius)
    {
        round_block r = new round_block();
        try{
            r = new round_block(center,radius);
        }catch(IllegalArgumentException i){
            JOptionPane.showMessageDialog(null, i.getMessage(),"invalid values!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        circles.add(r);
    }
    void resetWorld()
    {
        current_time = 0;
        for(int i = 0; i < balls.size(); i++)
        {
            balls.get(i).setPos(new point(20,100));
            balls.get(i).setVelocity(new vector(0, 0));
            System.out.println("initial values =>" + balls.get(i).position.y);
        }
        postions.clear();
    }

    public void run()       //render the simulation
    {       
        int k = 0;
        while(!worldEnd())
        {   System.out.println("\ntime : " + current_time + "\t"); 
            point[] tmp = new point[balls.size()];
            for(int i = 0; i < balls.size(); i++)
            {
                for(int j = 0; j < lines.size(); j++){
                    lines.get(j).interactWith(balls.get(i));
                }
                for(int j = 0; j < circles.size(); j++){
                    circles.get(j).interactWith(balls.get(i));
                }
                for(int j = 0; j < balls.size(); j++){
                    if(j == i)
                    {
                        continue;
                    }
                    balls.get(i).checkCollisionAndSetVelocity(balls.get(j));
                }
                balls.get(i).updateVelocity(time_increment);
                System.out.println("current velocity of ball " + i + " = " + balls.get(i).velocity.x + ", " + balls.get(i).velocity.y);
                tmp[i] = new point(balls.get(i).updatePosition(time_increment));
                //System.out.println("simulating, x = " + tmp[i].x + " y = ");
            }
            this.incTime();
            if(k%10 == 0)
                postions.add(tmp);
            k++;
        }
    }
}