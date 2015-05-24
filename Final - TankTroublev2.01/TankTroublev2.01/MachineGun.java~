
public class MachineGun extends Bullet
{
  private TankTrouble game;
  private Tank tank;
  private double rotation;
  private double velocity;
  private int shootN;
  private AllBullets b;
  //public boolean isActive;//to control the release
  
  public MachineGun(double xCoordinate, double yCoordinate, TankTrouble tanktrouble, Tank tankNew)
  {
    super(xCoordinate,yCoordinate,tanktrouble,tankNew);
    game = getGame();
    b=game.b;
    tank = tankNew;
    lifeSpan = 5000;
    shootN = 0;//to control the firing frequency
    //isActive = true;
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
    
    if(((System.currentTimeMillis()-timer)>(500))&&(shootN==0))//starts firing after half-a-second and when shootN is zero
    {
      double x = getX();
      double y = getY();
      double r = 5;
      double rotAng = Math.toRadians(10*Math.random());
      Vector2D velocity = new Vector2D(getVelVector());
      velocity.rotate(rotAng);
      double curAng = velocity.getAngle();
      
      Bullet temp = new GunAmmo(x+r*Math.cos(curAng),y+r*Math.sin(curAng),getGame(), tank); //This will actually be a GunAmmo object
      temp.setVelocities(velocity.getVector());
      b.insertBullet(temp);
    }
    shootN = (shootN+1)%3;
  }
}