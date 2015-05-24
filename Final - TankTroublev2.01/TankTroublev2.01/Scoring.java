import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.geom.*;

public class Scoring{
   private TankTrouble game;
   private int score1;
   private int score2;
   private final int shiftX = -10;
   private final int shiftY = 60;
   
  public Scoring(TankTrouble tk)
  {
    game = tk;
  }
  
  public void renderScoring(Graphics g)
  {
    Font font = new Font("Arial", Font.PLAIN, 20);
    g.setFont(font);
  //  g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.drawString(game.player1.tankName,game.map.originX+game.map.w+100+shiftX,90+shiftY); 
    g.drawImage(game.player1.icon,game.map.originX+game.map.w+100+shiftX,120+shiftY,null);
    g.drawString(Integer.toString(score1),game.map.originX+game.map.w+150+shiftX,150+shiftY);
    g.drawString(game.player2.tankName,game.map.originX+game.map.w+100+shiftX,220+shiftY); 
    g.drawImage(game.player2.icon,game.map.originX+game.map.w+100+shiftX,250+shiftY,null);
    g.drawString(Integer.toString(score2),game.map.originX+game.map.w+150+shiftX,280+shiftY);
  }
  
  public void increaseScore(int a)
  {
    if(a==1)
      score1++;
    if(a==2)
      score2++;
  }
}