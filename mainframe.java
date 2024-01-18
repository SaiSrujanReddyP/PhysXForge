import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainframe extends JFrame implements ActionListener{
    JButton newObject = new JButton("add new object");
    JButton newLine = new JButton("add new surface");
    JButton newCircle = new JButton("add new round block");
    JButton renderSim = new JButton("render the simulation");
    JButton playSim = new JButton("play the simulation");
    JButton setTime = new JButton("set time values");
    
    JCheckBox defaultSurface = new JCheckBox("set ground by default");
    JCheckBox setbounds = new JCheckBox("set bounds");

    ImageIcon img = new ImageIcon("Untitled.png");

    JPanel p1 = new JPanel();
    JLabel ti = new JLabel("set time increment (seconds):");
    JLabel tm = new JLabel("set simulation time(seconds):");
    JLabel useful = new JLabel();

    world obj;

    mainframe(world obj)
    {

        this.obj = obj;

        newObject.addActionListener(this);
        newLine.addActionListener(this);
        newCircle.addActionListener(this);
        renderSim.addActionListener(this);
        playSim.addActionListener(this);
        setTime.addActionListener(this);
        
        playSim.setEnabled(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6,1,10,20));
        buttonPanel.add(newObject);
        buttonPanel.add(newLine);
        buttonPanel.add(newCircle);
        buttonPanel.add(renderSim);
        buttonPanel.add(playSim);
        buttonPanel.add(setTime);
        buttonPanel.add(defaultSurface);
        buttonPanel.add(setbounds);
        buttonPanel.add(useful);
        //this.add(simulator);


        this.add(buttonPanel);

        this.setTitle("Java Physics simulator");
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(1000,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.pack();
    }

    class getNewObjectDimensions extends JFrame implements ActionListener
    {
        JButton addtoframe = new JButton("add object");
        JButton cancel = new JButton("cancel");
        JTextField xvelocity;
        JTextField yvelocity;
        JTextField mass;
        JTextField radius;
        JTextField ypos;
        JTextField xpos;
        getNewObjectDimensions()
        {
            addtoframe.addActionListener(this);
            cancel.addActionListener(this);

            sphere s = new sphere(); 
            boolean retry = true;
            while(retry){
                retry = false;
                double radius = Math.random() * 20;
                s = new sphere(new point(radius + Math.random() * (500 - radius * 2),radius + Math.random() * (500 - radius * 2)),radius,Math.random() * 1000);
                for(int i = 0; i < world.balls.size(); i++)
                {
                    if(world.balls.get(i).c.intersects(s.c))
                    {    retry = true;
                        break;
                    }
                }
                if(!retry)
                    break;
                else{
                    System.out.println("retry detected");
                }
            }
            xvelocity = new JTextField(" " + Math.random() * 50 * Math.pow(-1, (int)(Math.random() * 2)));
            yvelocity = new JTextField(" " + Math.random() * 50 * Math.pow(-1, (int)(Math.random() * 2)));
            mass = new JTextField(" " + Math.random() * 1000);
            radius = new JTextField(" " + s.c.radius);
            ypos = new JTextField(" " + s.c.center.x);
            xpos = new JTextField(" " + s.c.center.y);

            JLabel xvel = new JLabel("velocity along x axis");
            JLabel yvel = new JLabel("velocity along y axis");
            JLabel m = new JLabel("enter mass");
            JLabel r = new JLabel("enter radius");
            JLabel xp = new JLabel("enter x coordinate of position");
            JLabel yp = new JLabel("enter y coordinate of position");

            this.setLayout(new GridLayout(7,2,10,10));
            this.add(xvel);
            this.add(xvelocity);
            this.add(yvel);
            this.add(yvelocity);
            this.add(m);
            this.add(mass);
            this.add(r);
            this.add(radius);
            this.add(xp);
            this.add(xpos);
            this.add(yp);
            this.add(ypos);
            this.add(addtoframe);
            this.add(cancel);
            this.setTitle("add new sphere");
            this.setVisible(true);
            this.setSize(400,400);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == addtoframe)
            {
                vector vel = new vector();
                point cn = new point();
                double mas = 1, rad = 1;
                try{
                    vel.x = Double.parseDouble(xvelocity.getText());
                    vel.y = Double.parseDouble(yvelocity.getText());
                    cn = new point(Double.parseDouble(xpos.getText()),Double.parseDouble(ypos.getText()));
                    mas = Double.parseDouble(mass.getText());
                    rad = Double.parseDouble(radius.getText());
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Invalid input! Please enter valid numeric values.");
                    this.dispose();
                    return;
                }
                world.addSphere(cn,vel,rad,mas);
                this.dispose();
            }
            if(e.getSource() == cancel)
            {
                this.dispose();
            }
        }
    }
    private class getLineDimensions extends JFrame implements ActionListener
    {
        JButton addtoframe = new JButton("add surface");
        JButton cancel = new JButton("cancel");

        JTextField X1 = new JTextField();
        JTextField X2 = new JTextField();
        JTextField Y1 = new JTextField();
        JTextField Y2 = new JTextField();
        getLineDimensions()
        {
            cancel.addActionListener(this);
            addtoframe.addActionListener(this);
            JLabel X1label = new JLabel("enter x1");
            JLabel X2label = new JLabel("enter x2");
            JLabel Y1label = new JLabel("enter y1");
            JLabel Y2label = new JLabel("enter y2");
            

            this.setLayout(new GridLayout(5,2,10,10));
            this.add(X1label);
            this.add(X1);
            this.add(X2label);
            this.add(X2);
            this.add(Y1label);
            this.add(Y1);
            this.add(Y2label);
            this.add(Y2);

            this.add(addtoframe);
            this.add(cancel);
            this.setTitle("add new surface");
            this.setVisible(true);
            this.setSize(400,400);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == addtoframe)
            {
                double x1 = 0,y1 = 0,x2 = 500,y2 = 0;
                try{
                    x1 = Double.parseDouble(X1.getText());
                    y1 = Double.parseDouble(Y1.getText());
                    x2 = Double.parseDouble(X2.getText());
                    y2 = Double.parseDouble(Y2.getText());
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Invalid input! Please enter valid numeric values.");
                    this.dispose();
                    return;
               }
                world.addSurface(new point(x1,y1), new point(x2,y2));
               
                this.dispose();
            }
            if(e.getSource() == cancel)
            {
                this.dispose();
            }
        }
    }
    
    private class getCircleDimensions extends JFrame implements ActionListener
    {
        JButton addtoframe = new JButton("add roundBlock");
        JButton cancel = new JButton("cancel");

        JTextField radius = new JTextField();
        JTextField xpos = new JTextField();
        JTextField ypos = new JTextField();
        getCircleDimensions()
        {
            cancel.addActionListener(this);
            addtoframe.addActionListener(this);
            JLabel rlabel = new JLabel("enter radius of round block");
            JLabel xlabel = new JLabel("enter x position of round block");
            JLabel ylabel = new JLabel("enter y position of round block");
            

            this.setLayout(new GridLayout(4,2,10,10));
            this.add(rlabel);
            this.add(radius);
            this.add(xlabel);
            this.add(xpos);
            this.add(ylabel);
            this.add(ypos);

            this.add(addtoframe);
            this.add(cancel);
            this.setTitle("add new surface");
            this.setVisible(true);
            this.setSize(400,400);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == addtoframe)
            {
                double x = 0,r = 0,y = 0;
                try{
                    r = Double.parseDouble(radius.getText());
                    x = Double.parseDouble(xpos.getText());
                    y = Double.parseDouble(ypos.getText());
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Invalid input! Please enter valid numeric values.");
                    this.dispose();
                    return;
               }
                world.addRoundBlock(new point(x,y),r);
                this.dispose();
            }
            if(e.getSource() == cancel)
            {
                this.dispose();
            }
        }
    }
    private class setWorldValues extends JFrame implements ActionListener{
        JButton addtoframe = new JButton("set world values");
        JTextField A = new JTextField("0.01");
        JTextField B = new JTextField("10");
        JTextField C = new JTextField("9.8");
        
        setWorldValues()
        {
            addtoframe.addActionListener(this);
            JLabel Alabel = new JLabel("time increment of the world");
            JLabel Blabel = new JLabel("simulation duration");
            JLabel Clabel = new JLabel("gravity of world");
            

            this.setLayout(new GridLayout(4,2,10,10));
            this.add(Alabel);
            this.add(A);
            this.add(Blabel);
            this.add(B);
            this.add(Clabel);
            this.add(C);

            this.add(addtoframe);
            this.setTitle("add new surface");
            this.setVisible(true);
            this.setSize(400,400);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == addtoframe)   
            {
                double inctime = 0.1;
                double maxtime = 10;
                double grav = -9.8;
                try{
                    inctime = Double.parseDouble(A.getText());
                    maxtime = Double.parseDouble(B.getText());
                    grav = Double.parseDouble(C.getText());

                    if(inctime < 0.01)
                        throw new IllegalArgumentException("increment time set too low");
                    else if(inctime > 0.09)
                        JOptionPane.showMessageDialog(null, "Setting increment time too high can lead to simulation problems.","scary values!",JOptionPane.INFORMATION_MESSAGE);
                    if(maxtime < 0)
                        throw new IllegalArgumentException("max time cannot be negative");
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid numeric values.","invalid values!",JOptionPane.ERROR_MESSAGE);
                    this.dispose();
                    return;
               }
               catch(IllegalArgumentException i){
                    JOptionPane.showMessageDialog(null, i.getMessage(),"invalid values!",JOptionPane.ERROR_MESSAGE);
                    this.dispose();
                    return;
               }
                obj.setIncTime(inctime);
                obj.setMaxTime(maxtime*10);
                obj.setGravity(grav);
            }
            this.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == newObject)
        {
            new getNewObjectDimensions();
            if(world.balls.size() > 2)
                newObject.setEnabled(false);
        }
        if(e.getSource() == newLine)
        {
            new getLineDimensions();
        }
        if(e.getSource() == newCircle)
        {
            new getCircleDimensions();
        }
        if(e.getSource() == renderSim)
        {
            JOptionPane.showMessageDialog(null,"rendering simulation ... ", "please wait", JOptionPane.INFORMATION_MESSAGE);

            renderSim.setEnabled(false);
            if(defaultSurface.isSelected())
            {
                world.addSurface(new point(0,10), new point(500,10));
            }
            if(setbounds.isSelected())
            {
                world.addSurface(new point(0,0), new point(0,500));
                world.addSurface(new point(500,0), new point(500,500));
                world.addSurface(new point(0,500), new point(500,500));
                world.addSurface(new point(0,0), new point(500,0));
            }
            ///* 
            obj.run();
            useful.setText("rendering complete");
            newCircle.setEnabled(false);
            newLine.setEnabled(false);
            newObject.setEnabled(false);
            playSim.setEnabled(true);
            setTime.setEnabled(false);
        
        }
        if(e.getSource() == playSim)
        {
            JFrame sim = new JFrame();
            sim.add(new drawpad());
            sim.setVisible(true); 
            sim.pack(); 
            sim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        if(e.getSource() == setTime)
        {
            new setWorldValues();
        }
    }
}
