package GamePack;

import java.awt.*;

public class HUD {
    public static float HEALTH = 100 ;
    private float greenValue= 255f;

    private int score = 0;
    private int level = 1;
    public int bounds=0;

    public void setScore (int score){
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
        HEALTH = Game.clamp(HEALTH,0,100+(bounds/2));
        greenValue = Game.clamp(greenValue,0,255);

        greenValue = HEALTH*2;

        score++;
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fill3DRect(15,15,200+bounds,32,true);

        g.setColor(new Color(75,(int)greenValue,0));
        g.fill3DRect(15,15, (int) (HEALTH*2),32,true);

        g.setColor(Color.white);
        g.draw3DRect(15,15,200+bounds,32,true);

        g.drawString("Score: "+score,10,64);
        g.drawString("Level: "+level,10,80);
        g.drawString("SPACE-BAR for shop: ",10,97);
    }
}
