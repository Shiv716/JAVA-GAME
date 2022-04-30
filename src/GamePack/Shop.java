package GamePack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {

    private Handler handler;
    private HUD hud;
    private int B1 =100;
    private int B2 =100;
    private int B3 =100;

    public Shop(Handler handler , HUD hud){
        this.handler=handler;
        this.hud=hud;
    }

    public void render(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("arial",0,48));
        g.drawString("SHOP", (int) (Game.WIDTH/2-80),50);

        //BOX 1:
        g.setFont(new Font("arial",0,12));
        g.drawString("Upgrade Health",110,120);
        g.drawString("Cost: "+ B1,110,140);
        g.draw3DRect(100,100,100,80,true);

        //BOX 2:
        g.drawString("Upgrade Speed",260,120);
        g.drawString("Cost: "+ B2,260,140);
        g.draw3DRect(250,100,100,80,true);

        //BOX 3:
        g.drawString("Refill Health",410,120);
        g.drawString("Cost: "+ B3,410,140);
        g.draw3DRect(400,100,100,80,true);

        g.drawString("Score: "+hud.getScore(),(int)Game.WIDTH/2-50,300);
        g.drawString("Press SPACE to go back",(int)Game.WIDTH/2-85,330);
    }

    public void mousePressed(MouseEvent e){
        int mx=e.getX();
        int my=e.getY();

        //BOX 1:
        if(mx>=100 && mx<=200){
            if(my>=100 && my<=180) {
                //Selected BOX 1
                if (hud.getScore() >= B1) {
                    hud.setScore(hud.getScore() - B1);
                    B1 += 100;
                    hud.bounds+=20;
                    HUD.HEALTH = (100+(hud.bounds/2));
                }
            }
        }

        //BOX 2:
        if(mx>250 && mx<350){
            if(my>=100 && my<=180){
                //Selected BOX 2
                if(hud.getScore()>=B2){
                    hud.setScore(hud.getScore()-B2);
                    B2+=100;
                    handler.spd++;
                }
            }
        }

        //BOX 3:
        if(mx>=400 && mx<=500){
            if(my>=100 && my<=180){
                //Selected BOX 3
                if(hud.getScore()>=B3){
                    hud.setScore(hud.getScore()-B3);
                    B3+=1000;
                    HUD.HEALTH = (100+ (hud.bounds/2));
                }
            }
        }
    }
}
