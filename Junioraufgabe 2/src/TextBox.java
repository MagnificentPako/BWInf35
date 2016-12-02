import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class TextBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextBox extends Actor
{
    /**
     * Act - do whatever the TextBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int width;
    private int height;
    
    private Color fieldInactive;
    private Color fieldActive;
    private Color textInactive;
    private Color textActive;
    private Color shadow;
    
    private String text;
    private int fontSize;
    
    private int shadowSize;
    
    public TextBox(int width, int height, int fontSize, String text) {
        this.width = width;
        this.height = height;
        this.text = text;
        this.fontSize = fontSize;
        
        this.shadow = new Color(211,84,0);
        this.fieldActive = new Color(230,126,34);
        this.fieldInactive = new Color(149,165,166);
        this.textActive = new Color(236,240,241);
        
        this.shadowSize = 3;
        
        updateImage();
    }
    
    public void act() 
    {
        updateImage();
    }
    
    public void updateImage() {
        GreenfootImage img = new GreenfootImage(this.width+this.shadowSize, this.height+this.shadowSize);
        
        img.setColor(this.shadow);
        img.fillRect(5,5, this.width+this.shadowSize, this.height+this.shadowSize);
        
        img.setColor(this.fieldActive);
        img.fillRect(0, 0, this.width, this.height);
        
        img.setColor(this.textActive);
        GreenfootImage textImage = new GreenfootImage(
            this.text,
            this.fontSize,
            this.textActive,
            this.fieldActive
        );
        int x = (this.width/2)-(textImage.getWidth()/2);
        int y = (this.height/2)-(textImage.getHeight()/2);
        img.drawImage(textImage, x, y);
        
        setImage(img);
    }
    
}
