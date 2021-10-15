import bagel.Image;
import org.lwjgl.system.CallbackI;

public class LifeBar {
    private final Image Full_HEART = new Image("res/level/fullLife.png");
    private final Image No_HEART = new Image("res/level/nolife.png");
    private int maxLife;
    private int Life;
    public LifeBar(){
        maxLife = 3;
        Life = 3;
    }
    public int getCurrentLife(){ return this.Life; }
    public void setCurrentLife(int currentLife){this.Life = currentLife; }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }
    public void update(){
        renderLifeBar();
    }
}
