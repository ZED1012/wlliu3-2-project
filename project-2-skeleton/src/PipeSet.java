import bagel.DrawOptions;
import bagel.Image;
import bagel.Window;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PipeSet {
    private final Image PLASTIC_PIPE_IMAGE = new Image("res/level/plasticPipe.png");
    private final Image STEEL_PIPE_IMAGE = new Image("res/level-1/steelPipe.png");
    private final Image FLAME_IMAGE = new Image("res/level-1/flame.png");
    private Image PIPE_IMAGE;
    private final int PIPE_GAP = 168;
    private double pipeSpeed = 5;
    private final double TOP_PIPE_Y;
    private final double BOTTOM_PIPE_Y;
    private final DrawOptions ROTATOR = new DrawOptions().setRotation(Math.PI);
    private double pipeX = Window.getWidth();
    private final double RANDOM_POSITION;
    private List<Integer> pipeTypes = new ArrayList<>();
    private boolean LevelUp;
    private boolean isCollide = false;
    private boolean isPass = false;
    public boolean getIsPass(){return this.isPass;}
    public void setIsPass(boolean isPass){this.isPass = isPass;}
    private int CountFrame = 0;
    private final int FLAME_START = 20;
    private final int FLAME_END = 50;
    public void renderPipSet(){
        if (! isCollide){
            PIPE_IMAGE.draw(pipeX,TOP_PIPE_Y);
            PIPE_IMAGE.draw(pipeX,BOTTOM_PIPE_Y,ROTATOR);
        }
    }
    public void update(int timeScale){
        renderPipSet();
        updateSpeed(timeScale);
    }
    public void updateScore(PipeSet pipeSet){
        if (bird.getX() > pipeSet.getTopBox().right() && !pipeSet.getIsPass()){
            score += 1;
            pipeSet.setIsPass(true);
        }
        String scoreMsg = SCORE_MSG + score;
        FONT.drawString(scoreMsg, 100, 100);
        if(score == 10 && !isLevelUp){
            LevelUp = true;
        }
        if (score == 30) {
            win = true;
        }
        }
    public PipeSet(Image pipeImage, boolean isLevelUp) {
        LevelUp = isLevelUp;
        PIPE_IMAGE = pipeImage;
        if(! LevelUp){
            pipeTypes.add(100);
            pipeTypes.add(300);
            pipeTypes.add(500);
            Random rand = new Random();
            RANDOM_POSITION = pipeTypes.get(rand.nextInt(pipeTypes.size()));
        }else{
            double start = 100;
            double end = 500;
            double rand = new Random().nextDouble();
            RANDOM_POSITION = start + (rand *(end - start));
        }
        TOP_PIPE_Y = RANDOM_POSITION - PIPE_IMAGE.getHeight() / 2;
        BOTTOM_PIPE_Y = PIPE_IMAGE.getHeight() / 2 + RANDOM_POSITION + PIPE_GAP;
    }
    public Rectangle getTopBox() {
        return PIPE_IMAGE.getBoundingBoxAt(new Point(pipeX, TOP_PIPE_Y));

    }

    public Rectangle getBottomBox() {
        return PIPE_IMAGE.getBoundingBoxAt(new Point(pipeX, BOTTOM_PIPE_Y));

    }
    public Rectangle getTopBoxFlame(){
        if(CountFrame>FLAME_START  && PIPE_IMAGE == STEEL_PIPE_IMAGE){
            return FLAME_IMAGE.getBoundingBoxAt(new Point(pipeX, TOP_PIPE_Y+Window.getHeight()/2+10));
        }
        else {
            return PIPE_IMAGE.getBoundingBoxAt(new Point(pipeX, TOP_PIPE_Y));
        }
    }
    public Rectangle getBottomBoxFlame(){
        if(CountFrame>FLAME_START && PIPE_IMAGE == STEEL_PIPE_IMAGE){
            return FLAME_IMAGE.getBoundingBoxAt(new Point(pipeX,BOTTOM_PIPE_Y-Window.getHeight()/2-10));
        }
        else{
            return PIPE_IMAGE.getBoundingBoxAt(new Point(pipeX, BOTTOM_PIPE_Y));
        }
    }

    public void updateSpeed(int timeScale){
        pipeSpeed = 5;
        for (int i = 1; i < timeScale; i++){
            pipeSpeed = pipeSpeed * 1.5;
        }
        pipeX -= pipeSpeed;
    }

}



