package GamePack;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements  Runnable{

    private static final long serialVersionUID = 1550691097823471818L;

    public static final float WIDTH = 640 , HEIGHT = WIDTH /12 * 9;
    private Thread thread;
    private boolean running = false;

    private Menu menu;

    private Handler handler;

    private Random r ;

    private HUD hud;

    private Spawn spawner;

    private Shop shop;

    public static boolean paused = false;

    public int diff =0;
    //0=normal
    //1=hard

    public enum STATE{
        Menu , Game , Help , End , Select , Shop
    }

    public static STATE gameState = STATE.Menu;

    public Game(){
        handler = new Handler();
        hud = new HUD();
        shop = new Shop(handler,hud);
        menu = new Menu(this,handler,hud);
        this.addKeyListener(new KeyInput(handler,this));
        this.addMouseListener(menu);
        this.addMouseListener(shop);

        new Window((int)WIDTH ,(int) HEIGHT , "Building a game baby! " , this);
        spawner = new Spawn(handler, hud,this);

        r = new Random();
        if(gameState==STATE.Game){
        handler.addObject(new Player(r.nextInt((int)WIDTH/2-32),r.nextInt((int)HEIGHT/2-32), ID.Player,handler));
        handler.addObject(new BasicEnemy(r.nextInt((int)WIDTH/2-32),r.nextInt((int)HEIGHT/2-32), ID.BasicEnemy,handler));
        }else{
            for(int i=0;i<20;i++){
                handler.addObject(new MenuParticle(r.nextInt((int) WIDTH),r.nextInt((int)HEIGHT), ID.MenuParticle,handler));
            }
        }
    }

    public synchronized void start(){
        thread = new Thread (this);
        thread.start();
        running = true;
    }

    public synchronized void stop (){
        try {
            thread.join();
            running = false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0 ;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta>=1){
                tick();
                delta--;
            }
            if(running){
                render();
                frames++;
                if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                   // System.out.println("fps: "+frames);
                    frames = 0;
                }
            }
        }
        stop();
    }

    private void tick(){
        if(gameState==STATE.Game){
            if(!paused){
                hud.tick();
                spawner.tick();
                handler.tick();
                if(HUD.HEALTH<=0){
                    HUD.HEALTH=100;
                    gameState=STATE.End;
                    handler.clearEnemys();
                    for(int i=0;i<20;i++){
                        handler.addObject(new MenuParticle(r.nextInt((int) WIDTH),r.nextInt((int)HEIGHT), ID.MenuParticle,handler));
                    }
                }
            }
        }
        else if(gameState==STATE.Menu || gameState==STATE.End || gameState==STATE.Select){
            menu.tick();
            handler.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fill3DRect(0,0 ,(int) WIDTH,(int)HEIGHT,true);


        if (paused) {
            g.setColor(Color.white);
            g.drawString("Paused!",280,100);
        }

        if(gameState==STATE.Game){
            hud.render(g);
            handler.render(g);
        }
        else if(gameState==STATE.Shop){
            shop.render(g);
        }
        else if(gameState==STATE.Menu || gameState==STATE.Help || gameState==STATE.End || gameState==STATE.Select){
            menu.render(g);
            handler.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float var , float min , float max){
        if(var>=max) {
            return var = max;
        }
        else if(var<= min){
            return var = min;
        }
        else{
            return var;
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
