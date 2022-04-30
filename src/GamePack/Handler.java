package GamePack;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<>();

    public void tick(){

        for(int i = 0 ; i<object.size() ; i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g ){

        //Enhanced-for=loop:
        for( GameObject tempObject : object){
            tempObject.render(g);
        }
    }

    public void clearEnemys(){
        for( GameObject tempObject : object){
            if(tempObject.getID()!=ID.Player){
                object.clear();
                if(Game.gameState!=Game.STATE.End)
                addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(),ID.Player,this ));
            }
        }
    }

    public void addObject (GameObject object){
        this.object.add(object);
    }

    public void  removeObject(GameObject object){
        this.object.remove(object);
    }
}

