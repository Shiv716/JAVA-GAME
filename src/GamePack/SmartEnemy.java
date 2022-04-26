package GamePack;

import java.awt.*;

public class SmartEnemy extends GameObject {
    private Handler handler;

    private GameObject player;

    public SmartEnemy(float x, float y, ID id , Handler handler) {
        super(x, y, id);
        this.handler = handler;

        for(int i = 0 ; i< handler.object.size();i++){
            if(handler.object.get(i).getID()==ID.Player){
                player = handler.object.get(i);
            }
        }
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

        float diffX = x - player.getX() - 16 ;
        float diffY = y - player.getY() - 16 ;
        float distance = (float) Math.sqrt((x- player.getX())*(x- player.getX())+(y- player.getY())*(y - player.getY()));

        velX = (int) ((-1/distance)*diffX);
        velY = (int) ((-1/distance)*diffY);

//        if(y <= 0 || y >=Game.HEIGHT -32) velY*=-1;
//        if(x <= 0 || x >=Game.WIDTH - 16) velX*=-1;
        handler.addObject(new Trail((int)x,(int)y,ID.Trail,handler, Color.green,16,16,0.02f));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fill3DRect((int)x,(int)y,16,16,true);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }

}
