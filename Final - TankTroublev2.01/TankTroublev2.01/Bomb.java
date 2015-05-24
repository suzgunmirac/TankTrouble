import java.awt.geom.Ellipse2D;

public class Bomb extends Bullet
{
  //private double rotation;
  private int sharN; //number of shrapnel pieces
  private boolean hasExploded;
  private boolean sparked;
  private AllBullets b;
  private TankTrouble game;
  
  public Bomb(double xCoordinate, double yCoordinate, TankTrouble tanktrouble,Tank tankNew)
  {
    super(xCoordinate,yCoordinate,tanktrouble,tankNew);
    //rotation = tank.getRotation();
    lifeSpan = 2000;
    hasExploded = false;
    game = getGame();
    b = game.b;
    sharN = 20;// the number of shrapnels sent off upon explosion
    sparked=false;
    
    setIcon("Bomb.png");
  }
  
  public void updateBullet()
  {
    if((System.currentTimeMillis()-timer<lifeSpan-100)&&!sparked)//-100 is so that it can fire the shrapnels before it gets erased
    {
      super.updateBullet(); //use the superclass method for initialization of velocity
    }
    else if (!hasExploded||(sparked&&!hasExploded))
    {
      double angStep = 2.*Math.PI/sharN;
      
      double x = getX();
      double y = getY();
      double r = 10;
      double ang=0;
      Vector2D velocity;
      for (int i=0; i<sharN; i++)
      {
        Bullet temp = new Shrapnel(x+r*Math.cos(ang),y+r*Math.sin(ang),getGame(), game.player1); //the initial position is determined by the angle
        velocity = new Vector2D(ang, getVelocity());//Vector2D is used to determine the velocity vector with a certain magnitude and angle
        temp.setVelocities(velocity.getVector());//velocity is set
        ang+=angStep;//angle is incremented
        b.insertBullet(temp);//and inserted to the AllBullets object
      }
      hasExploded=true;//after doing this turn hasExploded true so that it doesn't explode twice
    }
  }
  
  public Ellipse2D.Double getShape()
  {
    Ellipse2D.Double shape = new Ellipse2D.Double(getX(),getY(),10,10);
    return shape;
  }
  
  public boolean getExplosion() {return hasExploded;}//controlling setting the bomb and exploding it
  public boolean getSparked() {return sparked;}
  public void spark() {sparked=true;}
  public void setExplosion(boolean exploded) {hasExploded=exploded;}
}