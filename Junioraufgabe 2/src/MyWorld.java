import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
    private Button normalPlayButton;
    private Button specialPlayButton;
    private NumInput numHor;
    private NumInput numVert;
    private NumInput numColSize;
    
    public MyWorld()
    {    
        super(3,5,100,true); 
        
        normalPlayButton = new Button(200, 75, 36 ,"Normal");
        addObject(normalPlayButton, 1, 3);
        
        specialPlayButton = new Button(200, 75, 36, "Spezial");
        addObject(specialPlayButton, 1, 4);
        
        numHor = new NumInput(200, 75, 3, 30, 1, 40);
        addObject(numHor, 1, 0);
        
        numVert = new NumInput(200, 75, 3, 30, 1, 40);
        addObject(numVert, 1, 1);
        
        numColSize = new NumInput(200, 75, 25, 100, 25, 40);
        addObject(numColSize, 1, 2);
        
        GreenfootImage image = new GreenfootImage(5,5);
        image.setColor(new Color(44,62,80));
        image.fill();
        setBackground(image);
        
        //addObject(button, cellsHor/2, cellsVert+1);
        //addObject(new Grid(), cellsHor/2+1, cellsVert/2-1);
        
    }
    
    public void act() {
        if(normalPlayButton.isClicked()) {
            Game game = new Game(
                numHor.getValue(),
                numVert.getValue(),
                numColSize.getValue(),
                false
            );
            Greenfoot.setWorld(game);
        }else if(specialPlayButton.isClicked()) {
            Game game = new Game(
                numHor.getValue(),
                numVert.getValue(),
                numColSize.getValue(),
                true
            );
            Greenfoot.setWorld(game);
        }
    }
    
}
