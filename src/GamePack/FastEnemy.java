package GamePack;

import java.awt.*;

public class FastEnemy extends GameObject {

    private Handler handler;

    public FastEnemy(int x, int y, ID id , Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 2;
        velY = 9;
    }

    public FastEnemy(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

        if(y <= 0 || y >=Game.HEIGHT -32) velY*=-1;
        if(x <= 0 || x >=Game.WIDTH - 16) velX*=-1;
        handler.addObject(new Trail(x,y,ID.Trail,handler,Color.cyan,16,16,0.02f));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fill3DRect(x,y,16,16,true);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,16,16);
    }
}
