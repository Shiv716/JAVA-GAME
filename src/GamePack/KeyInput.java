package GamePack;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean [] keyDown = new boolean[4];

    Game game;

    public KeyInput(Handler handler , Game game) {
        this.handler = handler;
        this.game = game;
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent k){
        int key = k.getKeyCode();

        for(int i =0 ; i<handler.object.size();i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Player) {
                //all the key-events for player
                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(-handler.spd);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(handler.spd);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(handler.spd);
                    keyDown[2] = true;
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-handler.spd);
                    keyDown[3] = true;
                }
            }
        }

            if(key==KeyEvent.VK_P) {
                if (Game.gameState == Game.STATE.Game) {
                    if (Game.paused) Game.paused = false;
                    else Game.paused = true;
                }
            }
            if(key==KeyEvent.VK_ESCAPE) System.exit(1);
            if(key==KeyEvent.VK_SPACE){
                if(Game.gameState== Game.STATE.Game){
                    Game.gameState= Game.STATE.Shop;
                }else if (Game.gameState== Game.STATE.Shop){
                    Game.gameState= Game.STATE.Game;
                }
            }
    }

    public void keyReleased(KeyEvent k){
        int key = k.getKeyCode();

        for(int i =0 ; i<handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID()==ID.Player){
                //all the key-events for player
                if(key==KeyEvent.VK_W) keyDown[0]=false;//tempObject.setVelY(0);
                if(key==KeyEvent.VK_S) keyDown[1]=false;//tempObject.setVelY(0);
                if(key==KeyEvent.VK_D) keyDown[2]=false;//tempObject.setVelX(0);
                if(key==KeyEvent.VK_A) keyDown[3]=false;//tempObject.setVelX(0);

                //vertical movement:-
                if(keyDown[0] && keyDown[1]) tempObject.setVelY(0);
                //horizontal movement:-
                if(keyDown[2] && keyDown[3]) tempObject.setVelX(0);
            }
        }
    }
}