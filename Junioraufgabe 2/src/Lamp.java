import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List;
/**
 * Write a description of class Lamp here.
 *
 * @author Paul Kosel
 * @version (a version number or a date)
 */
public class Lamp extends Actor
{
    /**
     * Act - do whatever the Lamp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public enum State {
        ON(new Color(255,234,0)), OFF(new Color(84,110,122));

        private Color color;

        State(Color col) {
            this.color = col;
        }

        public Color getColor() {
            return this.color;
        }

    }

    public enum GameType {
        NORMAL, WEIRD
    }

    private State state;
    public static GameType gameType;

    public Lamp(GameType type) {
        this.state = State.OFF;
        gameType = type;
       updateImage();
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(this)) {
            updateLamp(Game.button.getState() == GameButton.State.STARTED ? true : false);
        }
    }

    public void updateLamp(boolean shouldTravel) {
        this.state = this.state == State.OFF ? State.ON : State.OFF;
        if(shouldTravel) {
            if(Game.special) {
                this.getWorld().getObjects(Lamp.class).forEach((lamp) -> {
                    if(lamp.getX() <= this.getX() && lamp.getY() <= this.getY() && lamp != this) lamp.updateLamp(false);
                });
            }else{
                List<Lamp> neighbours = getNeighbours(1, false, Lamp.class);
                for(Lamp lamp : neighbours) {
                    lamp.updateLamp(false);
                }
            }
        }
        updateImage();
    }

    public void reset() {
        this.state = State.OFF;
        updateImage();
    }

    private void updateImage() {
        this.getImage().scale(Game.cellsSize, Game.cellsSize);
        this.getImage().setColor(this.state.getColor());
        this.getImage().fill();
        this.getImage().setColor(new Color(33,33,33));
        this.getImage().drawRect(0, 0, Game.cellsSize-1, Game.cellsSize-1);
    }

    public State getState() {return this.state;}
    public void setState(State state) {this.state = state; updateImage();}

}
