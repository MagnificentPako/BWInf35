import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class TextBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    /**
     * Act - do whatever the TextBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int width;
    private int height;
    private Color fieldActive;
    private Color textActive;
    private Color shadow;
    
    private String text;
    private int fontSize;
    
    private boolean isClicked = false;
    
    private int shadowSize;
    
    public Button(int width, int height, int fontSize, String text) {
        this.width = width;
        this.height = height;
        this.text = text;
        this.fontSize = fontSize;
        
        this.shadow = new Color(192,57,43);
        this.fieldActive = new Color(231,76,60);
        this.textActive = new Color(236,240,241);
        
        this.shadowSize = 3;
        
        updateImage();
    }
    
    public void act() 
    {
        updateImage();
        if(Greenfoot.mouseClicked(this)) {
            this.isClicked = true;
        }
    }
    
    public boolean isClicked() {
        if(this.isClicked) {
            this.isClicked = false;
            return true;
        }else{
            return false;
        }
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
