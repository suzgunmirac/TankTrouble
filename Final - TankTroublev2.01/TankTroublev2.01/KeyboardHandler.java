import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyboardHandler extends KeyAdapter{
  
  TankTrouble game;
  
  public KeyboardHandler(TankTrouble tanktrouble)
  {
    game = tanktrouble;
  }
  
  public void keyPressed(KeyEvent event)
  {
    game.keyPressed(event);
  }
  
  public void keyReleased(KeyEvent event)
  {
    game.keyReleased(event);
  }
}