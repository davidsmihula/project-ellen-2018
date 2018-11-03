package sk.tuke.kpi.oop.game.sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends AbstractActor {
    private Animation hammer;
    private int uses;

    public Hammer(){
        uses=1;

        setAnimation(new Animation("sprites/hammer.png", 16, 16));
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
