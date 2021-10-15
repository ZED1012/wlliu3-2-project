import bagel.Image;
import org.lwjgl.system.CallbackI;

public class LifeBar {
    private final Image Full_HEART = new Image("res/level/fullLife.png");
    private final Image No_HEART = new Image("res/level/nolife.png");
    private int maxLife;
    private int life;
    public LifeBar(){
        maxLife = 3;
        life = 3;
    }
    public int getlife(){ return this.life; }
    public void setlife(int currentLife){this.life = life; }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }
    public void update(){
        renderLifeBar();
    }


}
