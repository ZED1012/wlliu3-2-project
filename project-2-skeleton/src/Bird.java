import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.awt.*;
import java.lang.Math;

public class Bird {
    private final Image WING_DOWN_IMAGE = new Image("res/level-0/birdWingDown.png");
    private final Image WING_UP_IMAGE = new Image("res/level-0/birdWingUp.png");
    private final double X = 200;
    private final double FLY_SIZE = 6;
    private final double FALL_SIZE = 0.4;
    private final double INITIAL_Y = 350;
    private final double Y_TERMINAL_VELOCITY = 10;
    private final double SWITCH_FRAME = 10;
    private int frameCount = 0;
    private double y;
    private double yVelocity;
    private Rectangle boundingBox;
    private boolean holdWeapon = false;
    public boolean getHoldWeapon(){return this.holdWeapon;}
    public void setHoldWeapon(boolean holdWeapon){this.holdWeapon = holdWeapon;}

    public Bird() {
        y = INITIAL_Y;
        yVelocity = 0;
        boundingBox = WING_DOWN_IMAGE.getBoundingBoxAt(new Point(X, y));
    }


    /**
     *getters
     */
    public double getY() {
        return y;
    }

    public double getX() {
        return X;
    }

    public Rectangle getBox() {
        return boundingBox;
    }

    public void setY(){this.y = y;}
}

