import bagel.DrawOptions;
import bagel.Image;
import bagel.Window;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PipeSet {
    private final Image PIPE_IMAGE = new Image("res/pipe.png");
    private final int PIPE_GAP = 168;
    private double PIPE_SPEED = 5;
    private final double TOP_PIPE_Y = -PIPE_GAP / 2.0;
    private final double BOTTOM_PIPE_Y = Window.getHeight() + PIPE_GAP / 2.0;
    private final DrawOptions ROTATOR = new DrawOptions().setRotation(Math.PI);
    private double pipeX = Window.getWidth();
    private final double RANDOM_POSITION;
    private List<Integer> pipeTypes = new ArrayList<>();
    private boolean LevelUp;

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

}