import bagel.*;
import bagel.util.Rectangle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 2, 2021
 *
 * Please filling your name below
 * @author:
 */
public class ShadowFlap extends AbstractGame {
    private final Image PLASTIC_PIPE = new Image("res/level/plasticPipe.png");
    private final Image STEEL_PIPE = new Image("res/level-1/steelPipe.png");
    private final Image LEVEL0_BACKGROUND_IMAGE = new Image("res/level-0/background.png");
    private final String INSTRUCTION_MSG = "PRESS SPACE TO START";
    private final String GAME_OVER_MSG = "GAME OVER!";
    private final String CONGRATS_MSG = "CONGRATULATIONS!";
    private final String SCORE_MSG = "SCORE: ";
    private final String FINAL_SCORE_MSG = "FINAL SCORE: ";
    private final int FONT_SIZE = 48;
    private final Font FONT = new Font("res/slkscr.ttf", FONT_SIZE);
    private final int SCORE_MSG_OFFSET = 75;
    private Bird bird;
    private int score;
    private boolean gameOn;
    private boolean collision;
    private boolean win;
    private boolean isLevelUp = false;
    private ArrayList<PipeSet> plasticPipeSet = new ArrayList<>();;
    private ArrayList<PipeSet> pipeSets = new ArrayList<>();

    public ShadowFlap() {
        super(1024, 768, "ShadowFlap");
        bird = new Bird();
        score = 0;
        gameOn = false;
        collision = false;
        win = false;
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowFlap game = new ShadowFlap();
        game.run();
    }

    public void randomAddPipe(){
        Random rand = new Random();
        if (rand.nextBoolean()){
            pipeSets.add(new SteelPipeSet(STEEL_PIPE, isLevelUp));
        }else{
            pipeSets.add(new PlasticPipeSet(PLASTIC_PIPE, isLevelUp));
        }
    }
    public class PlasticPipeSet extends PipeSet{
        public PlasticPipeSet(Image pipImage_boolean isLevelUp){
            super(pipeImage, isLevelUp);
        }

    }
    public class SteelPipeSet extends PipeSet{
        public SteelPipeSet(Image pipImage_boolean isLevelUp){
            super(pipeImage, isLevelUp);
        }

    }


    /**
     * Performs a state update.
     * allows the game to exit when the escape key is pressed.
     */
    @Override
    public void update(Input input) {
        public boolean birdOutOfBound() {
            return (bird.getY() > Window.getHeight()) || (bird.getY() < 0);
        }
        public boolean detectCollision(Rectangle birdBox, Rectangle topPipeBox, Rectangle bottomPipeBox) {
            // check for collision
            return birdBox.intersects(topPipeBox) ||
                    birdBox.intersects(bottomPipeBox);
        }
        public void renderGameOverScreen() {
            FONT.drawString(GAME_OVER_MSG, (Window.getWidth()/2.0-(FONT.getWidth(GAME_OVER_MSG)/2.0)), (Window.getHeight()/2.0-(FONT_SIZE/2.0)));
            String finalScoreMsg = FINAL_SCORE_MSG + score;
            FONT.drawString(finalScoreMsg, (Window.getWidth()/2.0-(FONT.getWidth(finalScoreMsg)/2.0)), (Window.getHeight()/2.0-(FONT_SIZE/2.0))+SCORE_MSG_OFFSET);
        }
        public void renderWinScreen() {
            FONT.drawString(CONGRATS_MSG, (Window.getWidth()/2.0-(FONT.getWidth(CONGRATS_MSG)/2.0)), (Window.getHeight()/2.0-(FONT_SIZE/2.0)));
            String finalScoreMsg = FINAL_SCORE_MSG + score;
            FONT.drawString(finalScoreMsg, (Window.getWidth()/2.0-(FONT.getWidth(finalScoreMsg)/2.0)), (Window.getHeight()/2.0-(FONT_SIZE/2.0))+SCORE_MSG_OFFSET);
        }



    }

}
