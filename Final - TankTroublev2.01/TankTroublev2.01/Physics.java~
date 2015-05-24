import java.awt.geom.*; 
import java.applet.AudioClip; 
import java.applet.Applet; 
 
public class Physics 
{ 
  private TankTrouble game; 
  public Physics(TankTrouble tk) 
  { 
    game = tk; 
  } 
   
  public void bulletWall(Bullet bullet) 
  { 
     if (!(bullet instanceof Laser))//do not make this calculation for lasers
     {
      for(int j=0;j<game.map.walls.size();j++) 
      { 
       Area bulletArea = new Area(bullet.getShape()); 
       Area temp = bulletArea; 
       temp.intersect(new Area(game.map.walls.get(j))); 
       if(temp.isEmpty()==false) 
       { 
         if (bullet instanceof Shrapnel) {bullet.setVelocities(new double[]{0,0});}//shrapnels do not bounce! 
         else 
         { 
         if(bullet.previousWall==null) 
         { 
         AudioClip clip = Applet.newAudioClip(TankTrouble.class.getResource("blop.wav")); 
         clip.play(); 
         bullet.previousWall=game.map.walls.get(j); 
         if(game.map.wallType.get(j)==1) 
         { 
           bullet.setRotation(360-bullet.getRotation()); 
         //bullet.setRotation(180-bullet.getRotation()); 
         //bullet.setVelocity(-bullet.getVelocity()); 
        
         } 
         else 
         { 
           bullet.setRotation(180-bullet.getRotation()); 
         //bullet.setRotation(-bullet.getRotation()); 
         //bullet.setVelocity(-bullet.getVelocity()); 
         
         } 
         } 
         else if(!bullet.previousWall.equals(game.map.walls.get(j))) 
         { 
         AudioClip clip = Applet.newAudioClip(TankTrouble.class.getResource("blop.wav")); 
         clip.play(); 
           bullet.previousWall=game.map.walls.get(j); 
         if(game.map.wallType.get(j)==1) 
         { 
           bullet.setRotation(360-bullet.getRotation()); 
         //bullet.setRotation(180-bullet.getRotation()); 
         //bullet.setVelocity(-bullet.getVelocity()); 
          
         } 
         else 
         { 
           bullet.setRotation(180-bullet.getRotation()); 
         //bullet.setRotation(-bullet.getRotation()); 
         //bullet.setVelocity(-bullet.getVelocity()); 
          
         } 
         } 
         } 
       } 
      } 
     }
  } 
   
  public void bulletTank(Bullet bullet) 
  { 
    Area player1Area = game.player1.getArea(); 
    Area player2Area = game.player2.getArea(); 
    player1Area.intersect(new Area(bullet.getShape())); 
    player2Area.intersect(new Area(bullet.getShape())); 
    if((player1Area.isEmpty()==false)&&bullet.isActive)
       { 
      try{ 
          game.thread.sleep(1000); 
      } 
      catch(InterruptedException e){} 
        game.scoring.increaseScore(2); 
        game.restart(); 
       } 
    if((player2Area.isEmpty()==false)&&bullet.isActive)
       { 
       
        try{ 
          game.thread.sleep(1000); 
      } 
      catch(InterruptedException e){} 
        game.scoring.increaseScore(1); 
        game.restart(); 
       } 
  } 
   
  public boolean tankWall(Tank tank) 
  { 
    for(int j=0;j<game.map.walls.size();j++) 
    { 
    Area tankArea = tank.getArea(); 
    tankArea.intersect(new Area(game.map.walls.get(j))); 
    if(tankArea.isEmpty()==false) 
       { 
          return true; 
       } 
    
    } 
    return false; 
  } 
}