package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Wrench extends BreakableTool {

    private int remainingUses;


    private int uses;

    public Wrench(){
        uses=2;

        setAnimation(new Animation("sprites/wrench.png", 16, 16));
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
