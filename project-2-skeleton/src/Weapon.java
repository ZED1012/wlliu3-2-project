import bagel.Input;
import bagel.Image;
import bagel.Window;
import bagel.Keys;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.awt.*;
import java.util.Random;

public class Weapon {
    private final Image ROCK = new Image("res/level-1/rock.png");
    private final Image BOMB = new Image("res/level-1/bomb.png");
    private double weaponSpeed = 5;
    private double weaponX = Window.getWidth();
    private boolean isPickedUp = false;
    private boolean isshoot = false;
    private boolean dissappear = false;
    private boolean overlap = false;
    private int distance = 0;
    private double weaponY;
    private final Image WEAPON_IMAGE;




    public Weapon(Image weaponImage){
        WEAPON_IMAGE = weaponImage;
    }

    public void update(Input input, Bird bird, int timeScale){
        updateSpeed(timeScale);
        if (!isPickedUp){
            this.weaponX -= weaponSpeed;
        }else{
            if(bird.getHoldWeapon() &&!isshoot){
                if (input.wasPressed(Keys.S)){
                    bird.setHoldWeapon(false);
                    this.isshoot = true;
                }
                setWeaponX(bird.getBox().right());
                setWeaponY(bird.getY());
            }
        }
        renderweapon();
    }
    public void updateSpeed(int timeScale){
        weaponSpeed = 5;
        for (int i= 1; i < timeScale; i++){
            weaponSpeed = weaponSpeed *1.5;
        }
    }
    public void renderweapon(int shootRange){
        if (isshoot){
            if (distance <= shootRange){
                this.weaponX += weaponSpeed;
                distance += weaponSpeed;
            }else {
                this.dissappear = true;
            }
        }
        if(!dissappear && !overlap){
            WEAPON_IMAGE.draw(weaponX,weaponY);
        }
    }
    public Rectangle getBox(){
        return WEAPON_IMAGE.getBoundingBox(new Point(weaponX, weaponY));
    }
    public void setWeaponX(double x){ this.weaponX = x; }
    public void setWeaponY(double y){
        this.weaponY = y;
    }
    public void setOverlap(boolean bool){
        this.overlap = bool;
    }
    public boolean getHold(){
        return this.isPickedUp;
    }
    public boolean getOverlap(){
        return this.overlap;
    }
    public void setHold(boolean bool){
        this.isPickedUp = bool;
    }
    public boolean getRemove(){
        return this.dissappear;
    }
    public boolean getShoot(){
        return this.isshoot;
    }
    public Image getWeaponImage(){
        return this.WEAPON_IMAGE;
    }
    public Image getROCK(){
        return this.ROCK;
    }
    public void setRemove(boolean bool){
        this.dissappear = bool;
    }
    public Image getBOMB(){
        return this.BOMB;
    }
}
