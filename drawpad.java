import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class drawpad extends JPanel implements ActionListener{
    final int sim_height = 500;
    final int sim_width = 500;
    int k;
    Timer timer;
    Graphics2D g2d;

    drawpad()
    {
        this.setPreferredSize(new Dimension(sim_width,sim_height));
     
        timer = new Timer((int)(world.time_increment*1000),this);
        System.out.println("started simulation");
        timer.start();   
        k = 0;
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        g2d = (Graphics2D)g;
//        g2d.drawOval(250,250,10,10);
        // draw all the required shapes based on the input.
        //draw lines
        g2d.drawString("time : " + k/(float)100, 420, 20);
        int x1 = 0,y1 = 0,x2 = 0,y2 = 0;
        for(int i = 0; i < world.lines.size(); i++)
        {
            y1 = (int)(world.lines.get(i).l1.start.y);;
            y2 = (int)(world.lines.get(i).l1.end.y);
            x1 = (int)(world.lines.get(i).l1.start.x);
            x2 = (int)(world.lines.get(i).l1.end.x);
            /* 
            if(world.lines.get(i).l1.a != 0){
                x1 = (int)(world.lines.get(i).l1.start.x);
                x2 = (int)((world.lines.get(i).l1.b * y2 + world.lines.get(i).l1.c)/world.lines.get(i).l1.a);
            }
            else{
                x1 = (int)(-1 * world.lines.get(i).l1.c / world.lines.get(i).l1.b);
                x2 = x1;
            }*/
            try{
            g2d.drawLine(x1, sim_height - y1, x2, sim_height - y2);
            x1 = 0;
            x2 = sim_width;
            y1 = 0;
            y2 = 0;
            //g2d.drawLine(x1, sim_height - y1, x2, sim_height - y2);
            }
            catch(NullPointerException e)
                {
                    System.out.println("error found in segment 1");
                }
            

        }
        //draw circles
        for(int i = 0; i < world.circles.size(); i++)
        {
            g2d.setPaint(Color.GREEN);
            try{
            g2d.fillOval(
                (int)(world.circles.get(i).c.center.x - (int)world.circles.get(i).c.radius), 
                sim_height - (int)(world.circles.get(i).c.center.y + (int)world.circles.get(i).c.radius), 
                (int)world.circles.get(i).c.radius * 2, 
                (int)world.circles.get(i).c.radius * 2
            );
            }
            catch(NullPointerException e)
                {
                    System.out.println("error found in segment 2");
                }
            g2d.setPaint(Color.BLACK);
        }
        //draw spheres
        for(int j = 0; j < world.balls.size(); j++){
            
                try{
                g2d.drawOval(
                    (int)(world.postions.get(k)[j].x - world.balls.get(j).c.radius),
                    sim_height - (int)(world.postions.get(k)[j].y + (int)world.balls.get(j).c.radius),
                    (int)world.balls.get(j).c.radius * 2, 
                    (int)world.balls.get(j).c.radius * 2
                );
                g2d.drawString("sphere " + j + " : " + (world.postions.get(k)[j].x) + " , " + (world.postions.get(k)[j].y),0,20*(j + 1));
                }
                catch(NullPointerException e)
                {
                    System.out.println(" NP error found in segment 3\n i = " + k + "\nj = " + j);
                }
                catch(IndexOutOfBoundsException e)
                {
                    System.out.println("too many frames rendered");
                }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(k < world.postions.size() - 2)
        {
            k ++;
        }
        else{
            k = 0;
        }
        repaint();
    }
}
