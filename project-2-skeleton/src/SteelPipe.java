public class SteelPipe {
    private final Image FLAME_IMAGE = new Image("res/level-1/flame.png");
    private final double SWITCH_FLAME = 20;
    private int frameCount = 0;
    private int flameDuration = 0;
    private boolean collideWithFlame = false;

    public void update(int timeScale){
        frameCount += 1;
        if (frameCount % 20 == 0){
            if(!super.getIsCollide()&& flameDuration < 30){
                ShootFlame();
                frameCount -= 1;
                flameDuration += 1;
            }
        }
        if (flameDuration == 30){
            flameDuration = 0;
            frameCount += 30;

        }
        super.update(timeScale);
    }
    public void shootFlame(){
        FLAME_IMAGE.draw(super.getPipeX(),super.getTOP_PIPE_Y() + window.getHeight()/ 2.0 + 10);
        FLAME_IMAGE.draw(super.getPipeX(),super.getBottom_PIPE_Y() - window.getHeight()/ 2.0 - 10,super.getRotator());
    }
}
