import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class NumInput extends Actor {

     private int width;
    private int height;

    private Color fieldInactive;
    private Color fieldActive;
    private Color textInactive;
    private Color textActive;
    private Color shadow;

    private int value;
    private int min;
    private int max;
    private int fontSize;

    private int shadowSize;

    private int numWidth;
    private int plusWidth;
    private int minusWidth;

    public NumInput(int width, int height, int start, int max, int min, int fontSize) {
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
        this.min = min;
        this.max = max;
        this.value = start;

        int oneFourth = this.width/4;

        this.numWidth = oneFourth*2;
        this.plusWidth = oneFourth;
        this.minusWidth = oneFourth;

        this.shadow = new Color(192,57,43);
        this.fieldActive = new Color(231,76,60);
        this.fieldInactive = new Color(149,165,166);
        this.textActive = new Color(236,240,241);

        this.shadowSize = 3;

        updateImage();
    }

    public int getValue() {
        return this.value;
    }
    
    public void act() {
        int button, mouseY, mouseX = 0;
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null) {
            button = mouse.getButton();
            if(Greenfoot.mouseClicked(this)) {
                 mouseX = (mouse.getX()+1)*100;
                 mouseY = (mouse.getY()+1)*100;
                 int relX = mouseX-(this.getX()*100);
                 int relY = mouseY-(this.getY()*100);

                 if(relX >= numWidth) {
                     if(relX < numWidth+minusWidth) {
                         this.value--;
                         if(this.value < this.min) this.value = this.min;
                         updateImage();
                     }else{
                         this.value++;
                         if(this.value > this.max) this.value = this.max;
                         updateImage();
                     }
                 }
            }
        }
    }

    public void updateImage() {
        int numWidth;
        int plusWidth;
        int minusWidth;

        int numX;
        int numY;
        int plusX;
        int plusY;
        int minusX;
        int minusY;

        int oneFourth = this.width/4;
        numWidth = oneFourth*2;
        plusWidth = oneFourth;
        minusWidth = oneFourth;

        GreenfootImage image = new GreenfootImage(this.width+this.shadowSize,this.height+this.shadowSize);

        image.setColor(this.shadow);
        image.fillRect(3, 3, this.width+this.shadowSize, this.height+this.shadowSize);

        image.setColor(this.fieldActive);
        image.fillRect(0, 0, this.width, this.height);

        image.setColor(this.shadow);

        image.drawLine(numWidth, 0, numWidth, this.height);
        image.drawLine(numWidth+plusWidth, 0, numWidth+plusWidth, this.height);

        GreenfootImage numImage = textToImage(String.valueOf(this.value));
        numX = (numWidth/2)-(numImage.getWidth()/2);
        numY = (this.height/2)-(numImage.getHeight()/2);
        image.drawImage(numImage, numX, numY);

        GreenfootImage minusImage = textToImage("-");
        minusX = (minusWidth/2)-(minusImage.getWidth()/2);
        minusY = (this.height/2)-(minusImage.getHeight()/2);
        image.drawImage(minusImage, numWidth+minusX, minusY);

        GreenfootImage plusImage = textToImage("+");
        plusX = (plusWidth/2)-(plusImage.getWidth()/2);
        plusY = (this.height/2)-(plusImage.getHeight()/2);
        image.drawImage(plusImage, numWidth+minusWidth+plusX, plusY);

        setImage(image);
    }

    private GreenfootImage textToImage(String txt, int size) {
        GreenfootImage img = new GreenfootImage(
            txt,
            size,
            this.textActive,
            this.fieldActive
        );
        return img;
    }

    private GreenfootImage textToImage(String txt) {
        return textToImage(txt, this.fontSize);
    }

}
