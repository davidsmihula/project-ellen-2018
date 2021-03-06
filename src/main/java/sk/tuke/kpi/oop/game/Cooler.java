package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable {

    private Reactor reactor;
    private boolean isOn;

    public Cooler(Reactor reactor) {
        this.reactor = reactor;
        setAnimation(new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
        turnOff();

        if(this.reactor==null ){

           nula();
        }


    }

public void nula(){
    isOn = false;
    getAnimation().play();
    }


    public void turnOn() {
        isOn = false;
        getAnimation().play();

    }

    public void turnOff() {
        isOn = false;
        getAnimation().stop();
    }

    public boolean isOn() {
        return isOn;

    }

    private void coolReactor() {
        if (isOn) {
            reactor.decreaseTemperature(1);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke(this::coolReactor)).scheduleOn(this);
    }



}
