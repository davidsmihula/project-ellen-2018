package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor {
    private Switchable device;
    private Reactor reactor;

    public PowerSwitch(Switchable device) {

        setAnimation(new Animation("sprites/switch.png", 16, 16));
        this.device = device;
        updateAnimation();
    }



    public void SwitchOn(){

        device.turnOn();
        updateAnimation();
    }
    public void SwitchOff(){

        device.turnOff();
        updateAnimation();
    }

    public int  Switchable;

    public sk.tuke.kpi.oop.game.Switchable getDevice() {
        return device;
    }

    public void toggle() {
        if(reactor.isOn()) {
            reactor.turnOff();
        } else {
            reactor.turnOn();
        }
    }


    private void updateAnimation(){
        if(device.isOn())
            getAnimation().setTint(Color.WHITE);

        else{ getAnimation().setTint(Color.GRAY);}
    }
}
