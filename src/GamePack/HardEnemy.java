package GamePack;

import java.awt.*;
import java.util.Random;

public class HardEnemy extends GameObject {
    private Handler handler;
    private Random r = new Random();

    public HardEnemy(float x, float y, ID id , Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 5;
        velY = 5;
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

        if(y <= 0 || y >=Game.HEIGHT - 32) {
            if(velY<0) {
                velY=-(r.nextInt(6)+1)*-1;
            }
            else{
                velY=(r.nextInt(6)+1)*-1;
            }
        }
        if(x <= 0 || x >=Game.WIDTH - 16) {
            if(velX<0) {
                velX=-(r.nextInt(6)+1)*-1;
            }
            else{
                velX=(r.nextInt(6)+1)*-1;
            }
        }
        handler.addObject(new Trail((int)x,(int)y,ID.Trail,handler, Color.yellow,16,16,0.02f));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fill3DRect((int)x,(int)y,16,16,true);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }
}
