import java.awt.*;

public class Bomb extends Weapon{
    private final int SHOOT_RANGE = 50;
    public Bomb(Image bombImage){super(bombImage);}
    public  void update(Input input, Bird bird, int timeScale){
        super.update(input,bird,timeScale);
        super.renderweapon(SHOOT_RANGE);
    }
}
