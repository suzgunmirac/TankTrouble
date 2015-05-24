import java.awt.geom.Ellipse2D;

public class Mine extends Bullet
{ 
  public Mine(double xCoordinate, double yCoordinate, TankTrouble tanktrouble, Tank tankNew)
  {
    super(xCoordinate,yCoordinate,tanktrouble,tankNew);
    lifeSpan = 20000;//it will be erased after 20 seconds
    setX(xCoordinate-14);//this will approximately be the center of the tank
    setY(yCoordinate-12);
    isActive = false;//it gets activated after a second
    setIcon("mine.png");//change the image so that it displays a mine
  }
  
  public void updateBullet()
  {
    if (((System.currentTimeMillis()-timer)>(1000))&&!isActive)//if more than a second has passed and the tank is not active...
    {
      isActive = true;//activate  (will be used in physics)
    }
  }
  
  public Ellipse2D.Double getShape()//the getShape method is overrided because a mine is larger
  {
    Ellipse2D.Double shape = new Ellipse2D.Double(getX(),getY(),30,30);
    return shape;
  }
}