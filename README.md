
# Introduction
This is a simulator application, made using java, which helps the user to visualize the movement of spheres under the effect of -
  1. Collisions with other spheres
  2. Gravity(from other spheres as well as the world)
  3. Drag force
  4. Collision with stationary objects like surfaces and stationary spheres

It comes with a user-friendly GUI so that the application can be accessible to larger audience. The physics used in this application is entirely based on real world values. The default unit of distance is meters and for time it is seconds.

## How to run the application
In case you are new to java, you may read the instructions to run the java application.
Since this is an application made in java, please make sure that you have java installed in order to run the application.
  1. Clone the repository into an empty folder.
  2. Navigate to the folder where you have cloned this repository.
  3. Open the command line interface(i.e. terminal, command prompt, powershell).
  4. Compile the program by the command <code>$javac simulate.java</code> in the command line interface.
  5. Run the application using the command <code>$java simulate</code> in the command line interface. (if you compiled the program already, there is no need to recompile).
### some terms I will use within the next sections and its explanation -
1. world - by world, I mean the window in which the simulation runs.
2. render - to make the program do the necessary calculations.
# Running of the application
<center><img width="255" alt="main page" src="images/physics1.png"></center><br>
Here is the screenshot of the main window of the application. It allows 4 basic functions -<pre>
  1. Add a sphere to the world.
  2. Add a surface or stationary object to the world.
  3. Rendering the simulation.(you can run the application after rendering the simulation only. Rendering means to calculate the position of every object at every moment in time.)
  4. Playing the simulation.</pre>

It also allows for a slightly more complex function to "set time values" which is basically to set parameters for the world.

# Description of each button
## add new object
Clicking "add new object" opens the following window - 
<center><img width="291" alt="add new object interface" src="images/physics2.png"></center><br>
You will see some random values already in each text field. These are values randomly generated by the program, in case you want to simulate something random.
The values that are to be given as input into each field are self explanatory. Some rules to follow while entering values include -<pre>
1. Mass cannot be negative
2. Reducing the radius of the sphere too much would cause the sphere to be too small to be visible on the screen.
3. Radius that is too small with a velocity that is too large can cause errors like the object going through surfaces, and improper collisions between objects.
4. (x,y) coordinate of the bottom left of the simulation window is (0,0), for top right of the simulation window is (500,500). Be careful to not place objects outside this range so that they remain visible.
5. Be careful to not place objects such that they are in collision with another object right from the beginning as the simulation may turn out to be improper.
</pre>
Pressing "add object" will add the sphere to the world.

## add new surface
<center><img width="290" alt="add new surface" src="images/physics3.png"></center><br>
You can place a surface in the world that goes through the points x1,y1 and x2,y2.
In case you want to place a ground or set bounds so that the sphere doesn't go outside the bounds of the window, you can directly select the checkboxes from the main window.
Adding the ground using the checkbox means to place a line that passes through 0,20 to 500,20.

## add new round block
<center><img width="288" alt="add new round block" src="images/physics4.png"></center><br>
You can place a stationary sphere in the world using this. <b>You may keep 2 round blocks such that they collide</b>. Doing so will not cause an improper simulation.
Round blocks will be drawn with bright green color to indicate that they are meant to be stationary.
note - Collision of an object with a round block at high velocity may also cause improper simulation.

## set time values
<img width="287" alt="Set time values" src="images/physics6.png"><br>
This window allows to set basic values of the world, a brief description about each - <pre>
1. time increment of the world - On rendering the simulation, after a particular interval of time, the position of each object is recalculated, after checking for collisions with other objects and the forces       acting on each object. This time interval is called "time increment of the world".
2. simulation duration - this is the maximum duration of the simulation. Larger simulation duration means that the memory consumed to store the simulation also will increase, hence do not set it too high.
3. gravity of the world - this is the value of gravity experienced by every object in the world. You can set this to a negative value also, then the spheres would fall upwards.
4. drag coefficent - this is the drag coefficient experience by every object.  Drag force will decrease the velocity of the object based on this drag coefficient.
</pre>
**do not set world values while the simulation is being rendered.** 
This will cause errors in rendering the simulation.
It is recommended to leave the time increment value as it is, without changing it. 

## render the simulation
On clicking this button, the simulation will be rendered. it is possible to add objects while the simulation is being rendered, without errors, as long as you're doing it properly. More number of objects means that the time taken to render the simulation will increase. <br>
<img width="198" alt="information box" src="images/physics7.png"><br>
<br>
You will see the above window as the simulation gets rendered. It is possible to play the simulation before it completes rendering. However, the simulation will only run till where the simulation is rendered. When the rendering is completed, there will be a message like "rendering complete" in the main window, after which you can play the simulation completely.<br>
<img width="252" alt="main window while simulation is being rendered" src="images/physics8.png"><br>

## play the simulation
You can play the simulation after it has been rendered.

<img width="374" alt="Simulation window" src="images/physics9.png"><br>
the simulation window would look like this. At the top right, you will see the position of each object. At top left, you will see the current time of the simulation, in seconds. This may tend to be slightly slower than realtime as it needs to redraw the picture 10 times a second(in case the time interval is set to 0.01). The simulation will loop back to 0 seconds once it reaches the simulation duration.

# Conclusion
This application would be useful to demonstrate how objects interact with gravity, air/water resistance and how insignificant the effect of gravity is, between 2 objects of very large mass.

