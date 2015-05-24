import java.awt.geom.Ellipse2D;

public class Mine extends Bullet
{ 
  public Mine(double xCoordinate, double yCoordinate, TankTrouble tanktrouble, Tank tankNew)
  {
    super(xCoordinate,yCoordinate,tanktrouble,tankNew);
    lifeSpan = 20000;
    setX(xCoordinate-14);
    setY(yCoordinate-12);
    isActive = false;//it gets activated after a second
    setIcon("mine.png");
  }
  
  public void updateBullet()
  {
    if (((System.currentTimeMillis()-timer)>(1000))&&!isActive)
    {
      isActive = true;//activate  (will be used in physics)
    }
  }
  
  public Ellipse2D.Double getShape()
  {
    Ellipse2D.Double shape = new Ellipse2D.Double(getX(),getY(),30,30);
    return shape;
  }
}