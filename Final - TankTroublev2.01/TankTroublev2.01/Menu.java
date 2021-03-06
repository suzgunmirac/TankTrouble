import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;

public class Menu{
 private BufferedImage background;
 private BufferedImage button1;
 private BufferedImage button2;
 private BufferedImage creditsbutton;
 private BufferedImage exitbutton;
 private boolean condition1=false;
 private boolean condition2=false;
 private boolean condition3=false; //condition for credits
 private boolean condition4=false; //condition for exit
 
 public Menu(){
    try {
    background = ImageIO.read(new File("background.png"));
    button1 = ImageIO.read(new File("exit.png")); // exit
    button2 = ImageIO.read(new File("play.png"));
    creditsbutton = ImageIO.read (new File ("credits.png"));
   // exitbutton = ImageIO.read (new File ("exit.png"));
    
     } catch (IOException e) {
       System.out.println("ERROR: Icon Not Found");
     }  
   
 }
 
 public void changePlayer2(boolean condition)
 {
   if(condition)
   {
     condition2=true;
   }
   else
   {
    condition2=false;
   }
 }
 
  public void changePlayer1(boolean condition)
 {
   if(condition)
   {
     condition1=true;
   }
   else
   {
    condition1=false;
   }
 }
  
   public void gotoCredits(boolean condition)
 {
   if(condition)
   {
     condition3=true;
   }
   else
   {
    condition3=false;
   }
 }
  
  

 public void renderMenu(Graphics g)
  {  
   if(condition2)
   {
   try {
     button2 = ImageIO.read(new File("playTank.png"));

     } catch (IOException e) {
       System.out.println("icon2 not found");
     }
   }
   else{
     try {
       button2 = ImageIO.read(new File("play.png"));
    
     } catch (IOException e) {
       System.out.println("icon2 not found");
     }
   }
   
   
   if(condition1)
   {
      try {
    
    button1 = ImageIO.read(new File("exitTank.png"));
    
     } catch (IOException e) {
       System.out.println("icon1 not found");
     }
   }
   else
   {
     try {
    
    button1 = ImageIO.read(new File("exit.png"));
    
     } catch (IOException e) {
       System.out.println("icon1 not found");
     }
   }
   
   
   if(condition3)
   {
      try {
    
    creditsbutton = ImageIO.read(new File("creditsTank.png"));
    
     } catch (IOException e) {
       System.out.println("creditsTank not found");
     }
   }
   else
   {
     try {
    
    creditsbutton  = ImageIO.read(new File("credits.png"));
    
     } catch (IOException e) {
       System.out.println("credits not found");
     }
   }
   
     
    g.drawImage(background,0,0,null);
    g.drawImage(button1,1000,450,null);
    g.drawImage(button2,1000,150,null);
    g.drawImage(creditsbutton, 1000, 300, null);
  //  g.drawImage(exitbutton, 1000, 600, null);
  }
  
}