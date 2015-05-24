import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.geom.*;


public class Bullet{
 private double x;
 private double y;
 private double velocityX;
 private double velocityY;
 private double velocity;
 private double rotation;
 public boolean isActive;
 private BufferedImage icon;
 private TankTrouble game;
 public Tank tank;
 public Rectangle2D previousWall;
 public long timer;
 public long lifeSpan;
 
 public Bullet(double xCoordinate, double yCoordinate, TankTrouble tanktrouble,Tank tankNew)
 {
   tank = tankNew;
   game = tanktrouble;
   velocity = 4; //zaten velocityX ve velocityY oldugundan bunu pozitif tutalim dedim
   rotation = tank.getRotation();
   timer = System.currentTimeMillis();
   lifeSpan = 5000;
   isActive = true;
   x = xCoordinate-5-(32*Math.sin(Math.toRadians(rotation)));
   y = yCoordinate-5+(32*Math.cos(Math.toRadians(rotation)));
   
   try {
     icon = ImageIO.read(new File("defaultBullet.png"));
   } catch (IOException e) {
     System.out.println("icon not found");
   }
   
 }
 
 public void updateBullet(){
   velocityX = -(velocity) * Math.sin(Math.toRadians(rotation));
   velocityY = (velocity) * Math.cos(Math.toRadians(rotation));
   moveBullet();
 }
 
 public void renderBullet(Graphics g)
 {
   Graphics2D g2 = (Graphics2D) g;
   AffineTransform t = new AffineTransform();
   t.translate(x, y);
   t.scale(1, 1); 
   g2.drawImage(icon, t, null); 
 }
  
 
 public double getY()
  {
    return y;
  }
  public double getX()
  {
    return x;
  }
  
  public Ellipse2D.Double getShape()
  {
    Ellipse2D.Double shape = new Ellipse2D.Double(x,y,5,5);
    return shape;
  }
  public void setRotation(double a)
  {
    rotation = (a+360)%360;
  }
  public double getRotation()
  {
    return rotation;
  }
  public void setVelocity(double a)
  {
    velocity = a;
  }
  public void setVelocities(double[] a)//sets both velocities from the vector
  {
    velocityX = a[0];
    velocityY = a[1];
    velocity = Math.sqrt(a[0]*a[0]+a[1]*a[1]);
  }
  
  public double getVelocity()
  {
    return velocity;
  }
  
  public void moveBullet()//moves the bullet in x and y by velocityX and velocityY
  {
    x+=velocityX;
    y+=velocityY;
  }
  
  public void setX(double newX) {x=newX;}
  public void setY(double newY) {y=newY;}
  
  public double[] getPosVector()
  {
    return new double[]{x, y};
  }
  
  public double[] getVelVector()
  {
    return new double[]{velocityX, velocityY};
  }
  
  public void setIcon(String imgName)
  {
    try {
      icon = ImageIO.read(new File(imgName));
    } catch (IOException e) {
      System.out.println("icon not found");
    }
  }
  
  public TankTrouble getGame()
  {
    return game;
  }
}