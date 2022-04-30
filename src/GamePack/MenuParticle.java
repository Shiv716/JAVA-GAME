package GamePack;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject{
    private Handler handler;

    private Random r = new Random();

    private Color col;


    public MenuParticle(float x, float y, ID id , Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = r.nextInt(7- -7)+ -7;
        velY = r.nextInt(7- -7)+ -7;
        if(velX==0)velY=1;
        if(velY==0)velX=1;


        col = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

        if(y <= 0 || y >=Game.HEIGHT -32) velY*=-1;
        if(x <= 0 || x >=Game.WIDTH - 16) velX*=-1;
        handler.addObject(new Trail((int)x,(int)y,ID.Trail,handler, col,16,16,0.05f));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(col);
        g.fill3DRect((int)x,(int)y,16,16,true);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }
}
