import java.awt.Graphics;
import java.util.LinkedList;

public class AllBullets {
  public LinkedList<Bullet> bullets = new LinkedList<Bullet>();
  
  Bullet temp;
  TankTrouble game;
  
  public AllBullets(TankTrouble tanktrouble)
  {
    game = tanktrouble;
  }
  
  public void updateAllBullets(){
    for(int i = 0 ; i < bullets.size() ; i++)
    {
      temp = bullets.get(i);
      game.physics.bulletWall(temp);
      game.physics.bulletTank(temp);
      if(System.currentTimeMillis()-temp.timer>=temp.lifeSpan)
      {
        temp.tank.setShoot(true);
        eraseBullet(temp);
      }
      else
      temp.updateBullet();
    }
  }
  
  public void renderAllBullets(Graphics g){
    for(int i = 0 ; i < bullets.size() ; i++)
    {
      temp = bullets.get(i);
      temp.renderBullet(g);
    }
  }
  
  public void insertBullet(Bullet bulletNew)
  {
    bullets.add(bulletNew);
  }
  
  public void eraseBullet(Bullet bulletOld)
  {
    bullets.remove(bulletOld); 
  }
  
}