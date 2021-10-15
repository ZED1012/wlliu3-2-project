import bagel.Image;
import org.lwjgl.system.CallbackI;
/**
 * the LifeBar class include the visualization of hearts
 */
public class LifeBar {
    private final Image FullHeart = new Image("res/level/fullLife.png");
    private final Image EmptyHeart = new Image("res/level/noLife.png");
    private int maxLife;
    private int life;
    private int Heart_X = 100;
    private int Heart_Y = 15;
    public LifeBar(){
        maxLife = 6;
        life = 3;
    }
    public int getLife(){ return this.life; }
    public void setLife(int life){this.life = life; }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    /**set the heart on screen*/
    public void renderLife(int level) {
        //level 0
        if (level == 0) {
            int count = 0;
            for (int i = 0; i < life; i = i + 1) {
                if (count < life) {
                    FullHeart.draw(Heart_X + i * 50, Heart_Y);
                    count += 1;
                } else {
                    EmptyHeart.draw(Heart_X + i * 50, Heart_Y);
                }
            }
        }
        //level 1
        else {
            int count = 0;
            for (int i = 0; i < life; i = i + 1) {
                if (count < life) {
                    FullHeart.draw(Heart_X + i * 50, Heart_Y);
                    count += 1;
                } else {
                    EmptyHeart.draw(Heart_X + i * 50, Heart_Y);
                }
            }
        }
    }
}



