package GamePack;

import java.awt.*;

public class HUD {
    public static int HEALTH = 100 ;

    public void tick(){

        HEALTH = Game.clamp(HEALTH,0,100);
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fill3DRect(15,15,200,32,true);

        g.setColor(Color.green);
        g.fill3DRect(15,15,HEALTH*2,32,true);

        g.setColor(Color.white);
        g.draw3DRect(15,15,200,32,true);
    }
}
