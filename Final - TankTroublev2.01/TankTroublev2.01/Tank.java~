import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Tank{

  public String tankName = "";
  private double x;
  private double y;
  private double velocityX;
  private double velocityY;
  public int bulletType;
  private boolean shoot;
  private TankTrouble game;
  private double rotation;
  private double velocity;
  private double addRotation;
  private int w = 700; // width
  private int h = 700; // height
  private int bulletNumber = 0;
  
  
  public int screenFit = 10;  // length of the wall
  private Random rand = new Random();
  public BufferedImage icon;  
  
  public Tank(String name, String color,int bt, TankTrouble tk) 
  {
    tankName += name;
    game = tk;
    shoot=true;
    rotation = 0;
    velocity=0;
    x = game.map.originX + rand.nextInt(7)*100 + 40;
    y = game.map.originY + rand.nextInt(7)*100 + 40;
    bulletType=bt;
    try {
    icon = ImageIO.read(new File(color+"Tank.png"));
     } catch (IOException e) {
       System.out.println("icon not found");
     }
  }
  
  public void updateTank()
  {
    
    //useful info:
    //tank size = 30 x 55
    
   rotation = (rotation+addRotation)%360;
   
   velocityX = (velocity) * Math.sin(Math.toRadians(rotation));
   velocityY = -(velocity) * Math.cos(Math.toRadians(rotation));
   
   x+=velocityX;
   y+=velocityY;
   if(game.physics.tankWall(this)==true)
   {
     x-=velocityX;
     y-=velocityY;
   }
 
  }
  
  public void renderTank(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
    
    g2.rotate(Math.toRadians(rotation),x,y); 
    AffineTransform t = new AffineTransform();
    t.translate(x-31/2, y-47/2); 
    t.scale(1, 1);
    g2.drawImage(icon, t, null);  
    g2.rotate(Math.toRadians(360-rotation),x,y);
  }
  
  public void setVelocity(double a){
  velocity = a;
  }
  
  public double getY()
  {
    return y;
  }
  public double getX()
  {
    return x;
  }
  
  public void setY(double a)
  {
    y = a;
  }
  public void setX(double a)
  {
    x = a;
  }
  public void setRotation(double a)
  {
    rotation = a;
  }
  
  
  public void setShoot(boolean condition)
  {
    shoot = condition;
  }
  
  public boolean hasShoot()
  {
    return shoot;
  }
  
  public void addShoot (int num){
    bulletNumber += num;
    //Thread.sleep(3);
    
    
    if (bulletNumber == 2){
      setShoot (false);
    }
    
  }
  
  public void addRotationDegree(double a)
  {
    addRotation = a;
  }
  public double getRotation()
  {
    return rotation;
  }
  public double getVelocity()
  {
    return velocity;
  }
  
  public Area getArea()
  {
    Area rect1 = new Area(new Rectangle2D.Double(x-31/2, y-45/2,31,45));
    Area rect2 = new Area(new Rectangle2D.Double(x-5/2, y-32/2,5,32));
    rect1.add(rect2);
    return rect1;
  }
  
  public double[] getVelVector()
  {
    return new double[]{velocityX, velocityY};
  }
  
  public double[] getPosVector()
  {
    return new double[]{x, y};
  }
  
}