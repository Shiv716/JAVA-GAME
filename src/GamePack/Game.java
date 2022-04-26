package GamePack;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements  Runnable{

    private static final long serialVersionUID = 1550691097823471818L;

    public static final float WIDTH = 640 , HEIGHT = WIDTH /12 * 9;
    private Thread thread;
    private boolean running = false;

    private Handler handler;

    private Random r ;

    private HUD hud;

    private Spawn spawner;

    public Game(){
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        new Window((int)WIDTH ,(int) HEIGHT , "Building a game baby! " , this);

        hud = new HUD();
        spawner = new Spawn(handler, hud);
        r = new Random();
        handler.addObject(new Player(r.nextInt((int)WIDTH/2-32),r.nextInt((int)HEIGHT/2-32), ID.Player,handler));
        handler.addObject(new BasicEnemy(r.nextInt((int)WIDTH/2-32),r.nextInt((int)HEIGHT/2-32), ID.BasicEnemy,handler));
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
        handler.tick();
        hud.tick();
        spawner.tick();
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

        handler.render(g);
        hud.render(g);

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
