import java.awt.*;

public class Weapon {
    private double weaponSpeed = 5;
    private double weaponX = Window.getWidth();
    private boolean isPickedUp = false;
    private boolean isshoot = false;
    private boolean dissappear = false;
    private boolean overlap = false;
    private int distance = 0;
    private double weaponY;
    private final Image WEAPON_IMAGE;
    private final double RANDOM_POSITION;

    public Weapon(Image weaponImage){
        WEAPON_IMAGE = weaponImage;
    }

    public void update(Input input, Bird bird, int timeScale){
        updateSpeed(timeScale);
        if (!isPickedUp){
            this.weaponX -= weaponSpeed;
        }else{
            if(bird.getHoldWeapon() && !isshoot){
                if (input.wasPressed(Keys.S)){
                    bird.setHoldWeapon(false);
                    this.isshoot = true;
                }
                setweaponX(bird.getBox().right());
                setweaponY(bird.getY());
            }
        }
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
                this.weaponX += 5;
                distance += 5;
            }else {
                this.dissappear = true;
            }
        }
        if(!dissappear && !overlap){
            WEAPON_IMAGE.draw(weaponX,weaponY);
        }
    }
}
