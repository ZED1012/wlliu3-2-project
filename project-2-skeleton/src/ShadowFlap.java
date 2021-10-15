import bagel.*;
import bagel.Font;
import bagel.Image;
import bagel.util.Rectangle;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 2, 2021
 *
 * Please filling your name below
 * @author: WENTAO LIU 1104900
 */
public class ShadowFlap extends AbstractGame {
    private final Image PLASTIC_PIPE = new Image("res/level/plasticPipe.png");
    private final Image STEEL_PIPE = new Image("res/level-1/steelPipe.png");
    private final Image LEVEL0_BACKGROUND = new Image("res/level-0/background.png");
    private final Image LEVEL1_BACKGROUND = new Image("res/level-1/background.png");
    private final Image ROCK = new Image("res/level-1/rock.png");
    private final Image BOMB = new Image("res/level-1/bomb.png");
    private final Font FONT = new Font("res/font/slkscr.ttf", FONT_SIZE);
    private final String INSTRUCTION_MSG = "PRESS SPACE TO START";
    private final String GAME_OVER_MSG = "GAME OVER!";
    private final String CONGRATS_MSG = "CONGRATULATIONS!";
    private final String SCORE_MSG = "SCORE: ";
    private final String FINAL_SCORE_MSG = "FINAL SCORE: ";
    private final int FONT_SIZE = 48;
    private final int SCORE_MSG_OFFSET = 75;
    private Bird bird;
    private int score;
    private boolean gameOn;
    private boolean collision;
    private boolean win;
    private boolean isLevelUp = false;
    private ArrayList<PipeSet> plasticPipeSet = new ArrayList<>();;
    private ArrayList<PipeSet> pipeSets = new ArrayList<>();
    private int timeScale = 1;
    private final int MAX_SCALE = 5;
    private final int MIN_SCALE = 1;
    private int frameCount = 0;
    private final double SWITCH_FRAME = 100;
    private final String LEVEL_UP_MES = "LEVEL_UP!";
    private boolean levelUpCount = false;
    private int levelUpCount = 0;
    private boolean flameCollision = false;

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
    public void update(INput input) {
        if(! isLevelUp){
            LEVEL0_BACKGROUND.draw(Window.getWidth() /2.0 ,Window.getHeight()/2.0);
        }else{
            LEVEL1_BACKGROUND.draw(Window.getWidth() /2.0 ,Window.getHeight()/2.0);
        }
        if (input.wasPressed(Keys.ESCAPE)){window.close();}
        if (! gameOn){renderInstructionScreen(input);}
        if (LifeBar.getLife() == 0 ){renderGameOverScreen();}
        if (birdOutofBound()){
            if (lifeBar.getLife() > 0){
                lifeBar.setLife(lifeBar.getLife()-1);
                bird.setY(350);
            }
        }
        if (win){renderWinScreen();}

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
        public void renderInstructionScreen(Input input) {
            FONT.drawString(INSTRUCTION_MSG, (Window.getWidth()/2.0-(FONT.getWidth(CONGRATS_MSG)/2.0)), (Window.getHeight()/2.0-(FONT_SIZE/2.0)));
            if(input.wasPressed(Keys.S)&& );
            if (input.wasPressed(Keys.Space)){
                gameOn = true;
            }
        }

    }

    public void updateTimeScale(Input input){
        if (input.wasPressed(Keys.L)) && timeScale < MAX_SCALE){
            timeScale += 1;
        }
        if (input.wasPressed(Keys.K)) && timeScale > MIN_SCALE{
            timeScale -= 1;
        }
    }
    if(gameOn && !(LifeBar.getlife() == 0) && !win){
        if(!leveUp){
            updateTimeScale(input);
            if (frameCount % 100 == 0){
                plasticPipeSet.add(new PlasticPipeSet(PLASTIC_PIPE, isLevelUp));
            }
            frameCount ++;
            bird.update(input);
            Rectangle birdBox = bird,getBox();
            updatePipeSet(plasticPipeSet,birdBox);
            lifeBar.update();
        }
        if(isLevelUp){
            update TimeScale(input);
            if (frameCount % 100 == 0){randomAddPipe();}
            if (frameCount % GENERATE_WEAPON == 0){
                randomAddWeapon();
                weapons.get(weapon.size() -1).setOverlap(overlapDetection(weapons.get(weapons.size() -1).getBox(), pipeSets));
            }
            frameCount += 1;
            bird.update(input);
            Rectangle birdBox = bird.getBox();
            updateWeapons(BirdBox, input);
            updatePipeSet(pipeSets,birdBox);
            lifeBar.update();
        }
    }
    public void updatePipeSet(ArrayList<PipeSet>pipeSets, Rectangle birdBox){
        for (PipeSet pipeSet: pipeSets){
            pipeSet.update(timeScale);
            Rectangle topPipBox = pipeSet getTopBox();
            Rectangle bottomPipeBox = pipeSet getBottomBox();
            collision = detectCollison(birdBox, topPipBox,bottomPipeBox);
            if(collision && !pipeset.getIsCollide()){
                lifeBar.setlife(lifeBar.getlife() - 1);
                pipeSet.setIsCollide(true);
            }
            updateScore(pipeSet);
            if (pipeSet.getPipeImage().equals(STEEL_PIPE)){
                SteelPipeSet steelPipe = (steelPipeSet) pipeSet;
                Rectangle topFlameBox = steelPipe.getTopFlame();
                Rectangle bottomFlameBox = steelPipe.getBottomFlame();
                if(steelPipe.getFrameCount() % 20 == 0){
                    flameCollision = detectCollion(birdBox, topFlameBox, bottomFlameBox);
                    if (flameCollision && !steelPipe.getCollionWithFlame()){
                        LifeBar.setLife(LifeBar.getlife() -1);
                        steelPipe.set CollideWithFlame(true);
                    }
                }
            }
        }
    }
    if (LevelUp && !isLevelUp){
        renderlevelUpScreen();
        levelUpCount ++;
        if(levelUpCount == 150){
            resetGame();
        }
    }
    public void resetGame(){
        isLevelUp = true;
        gameOn = false;
        bird.setLevelUp(true);
        timeScale = 1;
        LifeBar.setMaxLife(6);
        LifeBar.setlife(6);
        score = 0;
        frameCount = 0;
        bird.setY(350);
    }
    public boolean overlapDetection(Rectangle weaponBox, ArrayList<PipeSet>pipeSets){
        for(PipeSet pipe: pipeSets){
            Rectangle topPipeBox = pipe.getTopBox();
            Rectangle bottomPipeBox = pipe.getBottomBox();
            boolean overlap = detectCollion(weaponBox,topPipeBox,bottomPipeBox);
            if (overlap){
                return true;
            }
        }
        return false;
    }
    public void updateWeapons(Rectangle birdBox, Input input){
        for (Weapon weapon: weapons){
            Rectangle weaponBox = weapon.getBox();
            if(birdBox.intersects(weaponBox)&& !weapon.getIsPickedUp()&& !weapon.getoverlap()){
                if(!bird.getHoldWeapon()){
                    bird.setHoldWeapon(true);
                    weapon.setIsPickedUp(true);
                }
            }
            weapon.update(input,bird,timeScale);
            checkWeaponPipeCollion(weapon);
        }
    }
    public void checkWeaponPipeCollion(Weapon weapon){
        Rectangle weaponBox = weapon.getBox();
        for (PipeSet pipe: pipeSets){
            Rectangle topPipeBox  = pipe.getTopBox();
            Rectangle bottomPipeBox = pipe.getBottomBox();
            if (!weapon.getDisappear() && weapon.getIsShoot()){
                boolean weaponCollision = detectCollision();
                if(weapon.getWeaponImage().equals(ROCK)&& pipe.getImage.equals(PLASTIC_PIPE) && weaponCollision){
                    weapon.setDisappear(true);
                    pipe.setIsCollide(true);
                    pipe.setIsPass(true);
                    score +=1;
                }
                if(weapon.getWeaponImage().equals(BOMB) && weaponCollision){
                    weapon.setDisappear(true);
                    pipe.setIsCollide(true);
                    pipe.setIsPass(true);
                    score +=1;
                }
            }
        }
    }
}
