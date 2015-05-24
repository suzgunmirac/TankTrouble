import java.awt.geom.Ellipse2D;

public class TrollRocket extends Bullet
{ 
  
  private Tank tank, opponent;
  private double rotation;
  private double velocity;
  private AllBullets b;
  
  private TankTrouble game;
  
  public TrollRocket (double xCoordinate, double yCoordinate, TankTrouble tanktrouble,Tank tankNew, Tank anotherTank)
  {
    super(xCoordinate,yCoordinate,tanktrouble,tankNew);
    tank = tankNew;
    opponent = anotherTank;
    game = getGame();
    b=game.b;
    lifeSpan = 50000;

    setIcon("mine.png");  
    
    velocity = this.getVelocity();
    rotation = this.getRotation();
    this.setVelocities(new double[]{(-(velocity) * Math.sin(Math.toRadians(rotation))),((velocity) * Math.cos(Math.toRadians(rotation)))});
    
    
  }
  
 public void updateBullet()
  {
    setX(tank.getX()-5-(32*Math.sin(Math.toRadians(tank.getRotation()))));
    setY(tank.getY()-5+(32*Math.cos(Math.toRadians(tank.getRotation()))));
    rotation = tank.getRotation();//update rotation
    this.setVelocities(new double[]{(-(velocity) * Math.sin(Math.toRadians(rotation))),((velocity) * Math.cos(Math.toRadians(rotation)))});
    
    this.moveBullet();
    
  }
  
  
  
}