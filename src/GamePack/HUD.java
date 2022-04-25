package GamePack;

import java.awt.*;

public class HUD {
    public static int HEALTH = 100 ;
    private int greenValue= 25;

    private int score = 0;
    private int level = 1;

    public void Score (int score){
        this.score=score;
    }

    public int getScore(){
        return score;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel ( int level){
        this.level=level;
    }

    public void tick(){
        HEALTH = Game.clamp(HEALTH,0,100);
        greenValue = Game.clamp(greenValue,0,255);

        greenValue = HEALTH*2;

        score++;
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fill3DRect(15,15,200,32,true);

        g.setColor(new Color(75,greenValue,0));
        g.fill3DRect(15,15,HEALTH*2,32,true);

        g.setColor(Color.white);
        g.draw3DRect(15,15,200,32,true);

        g.drawString("Score: "+score,10,64);
        g.drawString("Level: "+level,10,80);
    }
}