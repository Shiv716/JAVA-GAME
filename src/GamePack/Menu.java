package GamePack;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;

    public Menu( Game game , Handler handler , HUD hud){
        this.game= game;
        this.handler = handler;
        this.hud=hud;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        if(Game.gameState == Game.STATE.Menu) {
            //Play Button:
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                Game.gameState = Game.STATE.Game;
                handler.addObject(new Player(r.nextInt((int) Game.WIDTH / 2 - 32), r.nextInt((int) Game.HEIGHT / 2 - 32), ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt((int) Game.WIDTH / 2 - 32), r.nextInt((int) Game.HEIGHT / 2 - 32), ID.BasicEnemy, handler));
            }

            //Help Button:
            if (mouseOver(mx, my, 210,250,200, 64)) {
                Game.gameState = Game.STATE.Help;
            }

            //Quit Button:
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                System.exit(1);
            }
        }

        //Back Button (For Help):
        if (Game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, 210,350,200,64)) {
                Game.gameState = Game.STATE.Menu;
            }
        }

        //Back Button (END GAME):
        if (Game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, 210,350,200,64)) {
                Game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                handler.addObject(new Player(r.nextInt((int) Game.WIDTH / 2 - 32), r.nextInt((int) Game.HEIGHT / 2 - 32), ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt((int) Game.WIDTH / 2 - 32), r.nextInt((int) Game.HEIGHT / 2 - 32), ID.BasicEnemy, handler));
            }
        }
    }

    public void mouseRelease (MouseEvent e){

    }

    private boolean mouseOver(int mx , int my , int x , int y, int width, int height){
        if(mx>x && mx<x+width){
            if(my>y && my < y+height){
                return true;
            }else{
                return false;
            }
        }else
            return false;
    }

    public void tick(){

    }

    public void render(Graphics g){

        if(Game.gameState == Game.STATE.Menu){
            Font fnt = new Font("arial",1,50);
            Font fnt2 = new Font("arial",1,30);
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Menu",240,70);

            g.setFont(fnt2);
            g.draw3DRect(210,150,200,64,true);
            g.drawString("Play",270,190);

            g.draw3DRect(210,250,200,64,true);
            g.drawString("Help",270,290);

            g.draw3DRect(210,350,200,64,true);
            g.drawString("Quit",270,390);
        }
        else  if (Game.gameState == Game.STATE.Help){
            Font fnt = new Font("arial",1,50);
            Font fnt2 = new Font("arial",1,20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help",240,70);

            g.setFont(fnt2);
            g.drawString("Use WASD for moving the player",150,200);

            g.setFont(fnt2);
            g.draw3DRect(210,350,200,64,true);
            g.drawString("Back",290,390);

        }
        else  if (Game.gameState == Game.STATE.End){
            Font fnt = new Font("arial",1,50);
            Font fnt2 = new Font("arial",1,20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over",180,70);

            g.setFont(fnt2);
            g.drawString("You lose with a score of: "+ hud.getScore(),170,200);

            g.setFont(fnt2);
            g.draw3DRect(210,350,200,64,true);
            g.drawString("TRY AGAIN!",245,390);

        }
    }
}
