package GamePack;

import java.awt.*;

public class BasicEnemy extends GameObject {

    public BasicEnemy(int x, int y, ID id) {
        super(x, y, id);
        velX = 5;
        velY = 5;
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

        if(y <= 4 || y >=Game.HEIGHT -32) velY*=-1;
        if(x <= 4 || x >=Game.WIDTH - 16) velX*=-1;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fill3DRect(x,y,16,16,true);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,16,16);
    }
}
