import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Laser extends Bullet
{
  private TankTrouble game;
  private Tank tank;
  private AllBullets b;
  private double dist;//distance taken by laser
  private double distLim;//laser durability
  private double guideLim;//guide extent
  private ArrayList<double[]> pathPoints; //points where the laser is reflected or ends
  private ArrayList<double[]> guidePoints; //like pathPoints but ends earlier (to guide the user)
  private double[] curPoint;
  private double sX;//x step
  private double sY;//y step
  private double step;
  public boolean isReady;
  private boolean isDeactivated;//to understand when to shorten laser's lifeSpan
  
  public Laser(double xCoordinate, double yCoordinate, TankTrouble tanktrouble, Tank tankNew)
  {
    super(xCoordinate,yCoordinate,tanktrouble,tankNew);
    lifeSpan = 5000;
    game = tanktrouble;
    b=game.b;
    tank = tankNew;
    isActive = false;//will be false if no intersection is expected and will turn true otherwise
    isReady = false;//will become true when user clicks to shoot for the second time
    isDeactivated = false;//will become true after the lifeSpan is shortened
    distLim = 520;
    guideLim = 220;
    double rotation = tank.getRotation();
    curPoint = getPosVector();//gets the initial position
    step = getVelocity(); //laser is actually instantenous
    sX = -(step) * Math.sin(Math.toRadians(rotation));
    sY =  (step) * Math.cos(Math.toRadians(rotation));
    pathPoints = new ArrayList<double[]>();
    guidePoints = new ArrayList<double[]>();
    pathPoints.add(new double[]{curPoint[0],curPoint[1]});//adds a point to avoid problems (in case its rendered without updates)
  }
  
  public void updateBullet()
  {
    setX(tank.getX()-5-(32*Math.sin(Math.toRadians(tank.getRotation()))));//update starting point
    setY(tank.getY()-5+(32*Math.cos(Math.toRadians(tank.getRotation()))));
    double rotation = tank.getRotation();
    sX = -(step) * Math.sin(Math.toRadians(rotation));//update laser direction
    sY =  (step) * Math.cos(Math.toRadians(rotation));
    curPoint = getPosVector();//gets the initial position
    pathPoints = new ArrayList<double[]>();//reset
    guidePoints = new ArrayList<double[]>();//reset
    pathPoints.add(new double[]{curPoint[0],curPoint[1]});//adds the initial point
    keyPointCalc();
    //System.out.println("AfterKeyPointCalc:");
    //printPoints(pathPoints);// test
    
    if (!isReady) {isActive=false;}//if not clicked to shoot for the second time deactivate
    else if((!isDeactivated) && isReady) //if clicked, remove laser after 0.1 sec
    {
      isDeactivated=true;
      timer = System.currentTimeMillis();
      lifeSpan = 500;
    }
  }
  
  public void keyPointCalc()
  {
    dist=0;//current distance
    while((dist<distLim)&&!isActive)
    {
      advance();//move the laser path
      dist+=step;//increment distance
      laserTankCheck();
      if (!isActive) {laserWallCheck();}
      arrangeGuide();
      //System.out.println(curPoint[0]+" "+curPoint[1]);//test
    }
    if (dist>=distLim) {pathPoints.add(new double[]{curPoint[0],curPoint[1]});}//adds the final point if the tank is not shot
  }
  
  public void laserWallCheck()//checks for collision with the wall and changes laser direction accordingly
  {
    for(int j=0;j<game.map.walls.size();j++)
    {
      Area bulletArea = new Area(new Ellipse2D.Double(curPoint[0],curPoint[1],5,5));//this isn't really the shape of the laser
      Area temp = bulletArea;
      temp.intersect(new Area(game.map.walls.get(j)));
      if(!temp.isEmpty())
      {
        pathPoints.add(new double[]{curPoint[0],curPoint[1]});//if it hits a wall add a reflection point
        //bullet.previousWall=game.map.walls.get(j);
        if(game.map.wallType.get(j)==1) {sX*=-1;}//reflection over y
        else {sY*=-1;}//reflection over x
        break;
      }
    }
  }
  
  public void laserTankCheck()
  {
    Area bulletArea = new Area(new Ellipse2D.Double(curPoint[0],curPoint[1],5,5));//this isn't really the shape of the laser
    Area player1Area = game.player1.getArea();
    Area player2Area = game.player2.getArea();
    player1Area.intersect(bulletArea);
    player2Area.intersect(bulletArea);
    if((!player1Area.isEmpty())||(!player2Area.isEmpty()))
    {
      pathPoints.add(new double[]{curPoint[0],curPoint[1]});//if it hits a wall add an endpoint
      isActive=true;
    }
  }
  
  public void arrangeGuide()
  {
    if (((dist>=guideLim)||(isActive))&&(guidePoints.size()==0))//do not if you already did it and do so when guideLim is exceeded or the the laser becomes active
    {
      for (int i=0; i<pathPoints.size(); i++)
      {
        guidePoints.add(pathPoints.get(i));
      }
      if (!isActive)//if this method is not called because laser got activated
      {
        guidePoints.add(new double[]{curPoint[0],curPoint[1]});//add the currentpoint where guideLim is exceeded
      }
    }
  }
  
  public void advance()
  {
    curPoint[0]+=sX;
    curPoint[1]+=sY;
  }
  
  
  public void drawPath(Graphics2D g2, ArrayList<double[]> points)
  {
    double[] point1;
    double[] point2;
    for (int i=0; i<points.size()-1; i++)
    {
      point1 = points.get(i);
      point2 = points.get(i+1);
      g2.drawLine((int) point1[0],(int) point1[1],(int) point2[0],(int) point2[1]);
    }
    //g2.drawLine(0,0,100,100);//DENEME
  }
  
  
  public Ellipse2D.Double getShape()
  {
    Ellipse2D.Double shape = new Ellipse2D.Double(curPoint[0],curPoint[1],5,5);
    return shape;
  }
  
  public void renderBullet(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.RED);
    if (!this.isReady)
    {
      float[] dash = { 1f, 1f, 1f };
      BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash, 2f );
      g2.setStroke(bs);
      //printPoints(guidePoints);// test
      drawPath(g2, guidePoints); //actually is guidePoints
    }
    else
    {
      BasicStroke bs = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f);
      g2.setStroke(bs);
      drawPath(g2, pathPoints);
    }
    g2.setColor(Color.BLACK);
  }
  
  public void printPoints(ArrayList<double[]> points)
  {
    double[] point;
    for (int i=0; i< points.size(); i++)
    {
      point = points.get(i);
      System.out.println(i+" "+point[0]+" "+point[1]);
    }
    System.out.print("\n");
  }
  ///////////////////////////////////////////////
}