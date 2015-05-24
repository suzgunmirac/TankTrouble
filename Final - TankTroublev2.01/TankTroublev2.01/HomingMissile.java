import java.awt.geom.*;
import java.util.ArrayList;

public class HomingMissile extends Bullet
{
  private boolean pathHit = false;//true if path hits
  private double angLimit = Math.toRadians(4.);
  private double segmeLen = 8.; //the length of the path line segments
  private Tank otherTank;
  private int init;
  //private double rotation;
  private double velocity = Math.abs(this.getVelocity()); //only positive for this class
  private Vector2D direction; //direction from bullet to tank (chosen at the end)
  private Vector2D newDirection; //the direction of the bullet after calculation
  private Vector2D deltap; //difference in position
  private double angDif;
  private double angDir;
  private double angOld;
  private int count; //for testing purposes
  private ArrayList<ArrayList<Double>> paths = new ArrayList<ArrayList<Double>>(); //to denote possible paths
  
  public HomingMissile(double xCoordinate, double yCoordinate, TankTrouble tanktrouble,Tank tankNew, Tank anotherTank)
  {
    super(xCoordinate,yCoordinate,tanktrouble,tankNew);
    otherTank = anotherTank;
    init=0;
    //rotation = tank.getRotation();
    count = 1; //for testing purposes
  }
  
  public void updateBullet()
  {
    if ((init<25)||wallException(this.getShape()))
    {
      super.updateBullet(); //use the superclass method for initialization of velocity
      init++;
    }
    /*else if (init==25)
    {
      this.setVelocities(this.pathFinder());
      this.moveBullet();
    }*/
    else
    {
      /*double angReq = (paths.get((paths.size()-1))).get(count);
      Vector2D dirReq = new Vector2D(angReq, velocity);*/
      
      //this.setVelocities(dirReq.getVector());
      this.setVelocities(this.pathFinder());
      this.moveBullet();
      //count++;
    }
  }
  
  /*public double[] calcVelocity()
  {
    
    Vector2D direction1; //direction from bullet to tank (to be evaluated)
    Vector2D direction2; //direction from bullet to tank (to be evaluated)
    Vector2D olDirection; //old direction of the bullet
    Vector2D deltap1; //difference in position (to be evaluated)
    Vector2D deltap2; //difference in position (to be evaluated)
    
    
    deltap1 = getPosDif(tank);
    deltap2 = getPosDif(otherTank);
    
    olDirection = (new Vector2D(this.getVelVector())).getUnit();
    angOld = olDirection.getAngle();
    
    direction1 = deltap1.getUnit();
    direction2 = deltap2.getUnit();
    
    double angDir1 = direction1.getAngle();
    double angDir2 = direction2.getAngle();
    
    double angDif1 = angDir1 - angOld; //angular difference1
    double angDif2 = angDir2 - angOld; //angular difference2
    
    boolean inBounds1 = (Math.abs(angDif1)<=Math.PI/2);
    boolean inBounds2 = (Math.abs(angDif2)<=Math.PI/2);
    
    if (!inBounds1&&!inBounds2) {newDirection = olDirection;}
    else if (inBounds1&&inBounds2)
    {
      if (getDistance(tank)<getDistance(otherTank)) {deltap = getPosDif(tank);} //which tank to choose?
      else {deltap = getPosDif(otherTank);}
      shootPrep();
    }
    else //the case where only one of inBoundses is correct
    {
      if (inBounds1) {deltap = getPosDif(tank);}
      if (inBounds2) {deltap = getPosDif(otherTank);}
      shootPrep();
    }
    
    return new double[]{velocity*newDirection.getXcomp(),velocity*newDirection.getYcomp()};//returns the new velocity as a vector
  }
  
  public Vector2D getPosDif(Tank atank) //calculates the position difference of the rocket to a player
  {
    return new Vector2D(new double[]{(atank.getX()- this.getX()),(atank.getY()- this.getY())}); //returns position difference as a vector
  }
  
  public double getDistance(Tank atank) //calculates the distance between a player and the rocket
  {
    Vector2D posDif = getPosDif(atank);
    return posDif.mag(); //returns distance
  }
  
  public void shootPrep()
  {
    direction = deltap.getUnit();
    double angDir = direction.getAngle();
    double angDif = angDir - angOld; //angular difference1
    double angNew;
    if (angDif>angLimit) {angNew=angOld+angLimit;} //it can at most change angLimit degrees at each timestep
    else if (angDif<-angLimit) {angNew=angOld-angLimit;}
    else {angNew = angDir;} //if it's less than angLimit lock on to the target
    newDirection = new Vector2D(angNew,1);
  }*/
  
  public boolean wallException(Ellipse2D.Double shape)//to check if it hits a wall
  {
    TankTrouble game = this.getGame();
    
    boolean wallExceptionV = false;
    
    for(int j=0;j<game.map.walls.size();j++)
    {
      Area bulletArea = new Area(shape);
      Area temp = bulletArea;
      temp.intersect(new Area(game.map.walls.get(j)));
      if (!temp.isEmpty()) {wallExceptionV = true; break;} //if there is intersection wallException is true
    }
    
    return wallExceptionV;
  }
  
  public boolean tankException(Ellipse2D.Double shape)//to check if it hits a player
  {
    TankTrouble game = this.getGame();
    boolean tankException1 = false;
    boolean tankException2 = false;
    Area bulletArea = new Area(shape);
    Area temp1 = tank.getArea();
    Area temp2 = otherTank.getArea();
    
    temp1.intersect(bulletArea);
    tankException1 = !temp1.isEmpty();
    
    temp2.intersect(bulletArea);
    tankException2 = !temp2.isEmpty();
    
    return (tankException1||tankException2); //returns true if it intersect with either one of the tanks
  }
  
  public double[] pathFinder() //returns the velocity necessary to hit the target
  {
    //int divergeAng = (int) (Math.toDegrees(angLimit)); //how many degrees to each side at most
    
    double angReq;//required angle
    Vector2D dirReq;//required direction
    
    paths.clear(); //clears the paths arrayList before each movement
    angOld = (new Vector2D(this.getVelVector())).getAngle(); //starting angle
    
    //setting up...
    ArrayList<Double> initialPath = new ArrayList<Double>();
    initialPath.add(angOld);//create the first path
    paths.add(initialPath);//add the first path to paths
    extendPaths();
    for (int i =0; i<paths.size(); i++)
    {
      paths.get(i).remove(0);// remove the initialization element from all extensions
    }
    //printPaths();
    
    while (!pathHit) {extendPaths();} //will stop when a path finds a target and when it stops the last element of paths will be the path
    angReq = (paths.get(paths.size()-1)).get(0);
    dirReq = new Vector2D(angReq, velocity);
    return dirReq.getVector(); //returns the required velocity at this coordinate
  }
  
  public void extendPaths() //extends all the paths in the arrayList while removing the old ones
  {
    int divergeAng = (int) (Math.toDegrees(angLimit)/4); //how many degrees to each side at most
    int pathsSize = paths.size();
    mainLoop: for (int i = 0; i<pathsSize; i++)
    {
      ArrayList<Double> dummy = paths.get(0);
      for (int cons = -divergeAng; cons<=divergeAng; cons++)
      {
        dummy.add(dummy.get(dummy.size()-1)+4*cons*Math.toRadians(1));
        paths.add(new ArrayList<Double>(dummy));//for each path add the line segment with new angles
        dummy.remove(dummy.size()-1);
        if (pathWall(paths.get(paths.size()-1))){paths.remove(paths.size()-1);} //if it collides with a wall remove the path
        else if (pathTank(paths.get(paths.size()-1))) {pathHit=true; break mainLoop;} //if it collides with a wall remove the path and no need to continue since this was our aim
      } //if hit, the path that works will be the last path among all the paths
      paths.remove(0);//remove the first element after it's angle variations are copied to the end
    }
  }
  
  public boolean pathWall(ArrayList<Double> apath) //this might not be working for some reason
  {
    double[] rockPos = this.getPosVector(); //rocket position
    double px = rockPos[0];
    double py = rockPos[1];
    for (int i = 0; i<apath.size(); i++)//increment px and py by adding all the x and line segment lengths
    {
      px += segmeLen*Math.cos(apath.get(i));
      py += segmeLen*Math.sin(apath.get(i));
    }
    return wallException(new Ellipse2D.Double(px,py,5,5)); //returns true if the path intersects with a wall
  }
  
  public boolean pathTank(ArrayList<Double> apath) //this might not be working for some reason
  {
    double[] rockPos = this.getPosVector(); //rocket position
    double px = rockPos[0];
    double py = rockPos[1];
    for (int i = 0; i<apath.size(); i++)//increment px and py by adding all the x and line segment lengths
    {
      px += segmeLen*Math.cos(apath.get(i));
      py += segmeLen*Math.sin(apath.get(i));
    }
    return tankException(new Ellipse2D.Double(px,py,5,5)); //returns true if the path intersects with either one of the tanks
  } 
  
  public void printPaths()
  {
    for (int i=0; i<paths.size(); i++)
    {
      System.out.print(i+ "\n");
      for (int a=0; a<paths.get(i).size(); a++)
      {
        System.out.print(paths.get(i).get(a)+ " ");
      }
      System.out.print("\n");
    }
  }
}