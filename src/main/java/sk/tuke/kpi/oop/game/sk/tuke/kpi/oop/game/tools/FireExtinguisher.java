package sk.tuke.kpi.oop.game.sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class FireExtinguisher extends AbstractActor {

    private Animation extunisher;
    private int uses;

    public FireExtinguisher(){
        uses=1;

        setAnimation(new Animation("sprites/extinguisher.png", 16, 16));
    }

    public void use (){
        uses=uses-1;
        if(uses==0){
            getScene().removeActor(this);
        }

    }

    public int getUses() {
        return uses;
    }
}








