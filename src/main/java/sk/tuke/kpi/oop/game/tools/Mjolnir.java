package sk.tuke.kpi.oop.game.sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Mjolnir extends Hammer {

    private int uses;

    public Mjolnir(){
        uses=4;

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
