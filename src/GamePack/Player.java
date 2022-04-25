package GamePack;

import java.awt.*;
import java.sql.Struct;
import java.util.Random;

public class Player extends GameObject{

    Random r = new Random();

    Handler handler;

    public Player(int x, int y, ID id , Handler handler) {
        super(x, y, id);
        this.handler = handler;
//        velX = r.nextInt(5)+1;
//        velY = r.nextInt(5);
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

        x= Game.clamp(x,0,Game.WIDTH-37);
        y= Game.clamp(y,0,Game.HEIGHT-60);
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.white);
        g.fill3DRect(x,y , 32,32,true);


    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    private void collision(){
        for(int i = 0 ; i< handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID()==ID.BasicEnemy){
                if(getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    HUD.HEALTH-=2;
                }
            }
        }
    }

}
