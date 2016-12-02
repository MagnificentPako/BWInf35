import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameButton extends Actor
{
    
    
    public enum State {
        START(new Color(30,136,229),Color.WHITE,"Start"),
        STARTED(new Color(255,160,0),Color.BLACK,"Reset"),
        RESTART(new Color(46,204,113), Color.BLACK,"Restart");
        
        private Color bg;
        private Color fg;
        private String text;
        
        State(Color background, Color foreground, String text) {
            this.bg = background;
            this.fg = foreground;
            this.text = text;
        }
        
        public Color getBackground() {return this.bg;}
        public Color getForeground() {return this.fg;}
        public String getText() {return this.text;}
        
    }
    
    private State state;
    
    public GameButton() {
        this.state = State.START;
        updateImage();
    }
    
    public void act() {
        if(Greenfoot.mouseClicked(this)) {
            switch(this.state) {
                case START: {this.state = State.STARTED; break;}
                case STARTED: {getWorldOfType(Game.class).resetGame(); break;}
                case RESTART: {getWorldOfType(Game.class).resetGame(); break;}
            }
        }
        updateImage();
    }   
    
    public void updateImage() {
        this.getImage().scale(Game.cellsSize*(Game.cellsHor+1), Game.cellsSize);
        this.getImage().setColor(this.state.getBackground());
        this.getImage().fill();
        this.getImage().setColor(this.state.getForeground());
        
        GreenfootImage font = new GreenfootImage(
            this.state.getText(),
            24,
            this.state.getForeground(),
            this.state.getBackground()
        );
        
        this.getImage().drawImage(font,
        (this.getImage().getWidth()/2)-font.getWidth()/2, 
        (this.getImage().getHeight()/2)-font.getHeight()/2);
        
    }
    
    public State getState() {return this.state;}
    public void setState(State state) { this.state = state; updateImage();}
    
}
