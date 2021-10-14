import bagel.Image;
import org.lwjgl.system.CallbackI;

public class LifeBar {
    private final Image Full_HEART = new Image("res/level/fullLife.png");
    private final Image No_HEART = new Image("res/level/nolife.png");
    private int maxLife;
    private int currentLife;
    public LifeBar(){
        maxLife = 3;
        currentLife = 3;
    }
    public int getCurrentLife(){ return this.currentLife; }
    public void setCurrentLife(int currentLife){this.currentLife = currentLife; }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }
    public void update(){
        renderLifeBar();
    }
}
