public class Shrapnel extends Bullet
{
  private TankTrouble game;
  private Tank tank;
  private AllBullets b;
  
  public Shrapnel(double xCoordinate, double yCoordinate, TankTrouble tanktrouble, Tank tankNew)
  {
    super(xCoordinate,yCoordinate,tanktrouble,tankNew);
    lifeSpan = 5000;
    setX(xCoordinate);//make the initial x the specified xcoordinate instead of adjusting to the tank
    setY(yCoordinate);//make the initial y the specified ycoordinate instead of adjusting to the tank
  }
  
  public void updateBullet()//the updateBullet method is overrided because there is no need to adjust the velocity of a shrapnel
  {
    moveBullet();//shrapnels only advance in a straight path until they collide with a wall and stop
  }
}