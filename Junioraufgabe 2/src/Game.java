import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends World
{

    /**
     * Constructor for objects of class Game.
     * 
     */
    public static int cellsVert;
    public static int cellsHor;
    public static int cellsSize;
    
    public static boolean special;
    
    public static GameButton button;
    
    public Game(int cVert, int cHor, int cS, boolean spec)
    {    
        
        super(cHor,cVert+1,cS,true); 
                
        cellsVert = cVert;
        cellsHor = cHor;
        cellsSize = cS;
        special = spec;
        
        for(int y = 0; y < cellsVert; y++) {
            for(int x = 0; x < cellsHor; x++) {
                addObject(new Lamp(Lamp.GameType.NORMAL), x, y);
            } 
        }
        
        button = new GameButton();
        
        addObject(button, cellsHor/2, cellsVert+1);
        //addObject(new Grid(), cellsHor/2+1, cellsVert/2-1);
        
    }
    
    public void act() {
        if(this.button.getState() == GameButton.State.STARTED) {
                for(Lamp lamp : getObjects(Lamp.class)) {
            if(lamp.getState() == Lamp.State.OFF) return;
            }
            this.button.setState(GameButton.State.RESTART);
        }

    }
    
    public void resetGame() {
        getObjects(GameButton.class).get(0).setState(GameButton.State.START);
        for(Lamp lamp : getObjects(Lamp.class)) {lamp.setState(Lamp.State.OFF);}
    }
}
