package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;
import sk.tuke.kpi.oop.game.sk.tuke.kpi.oop.game.tools.FireExtinguisher;
import sk.tuke.kpi.oop.game.sk.tuke.kpi.oop.game.tools.Hammer;
import sk.tuke.kpi.oop.game.sk.tuke.kpi.oop.game.tools.Mjolnir;


public class Reactor extends AbstractActor implements Switchable {
    private double temperature;
    private int damage;
    private Animation normalAnimation;
    private Animation hotAnimation;
    private Animation brokenAnimation;
    private Animation turnedOffAnimation;
    private boolean isRunning;
    private Light light;

    public Reactor() {
        temperature = 0;
        damage = 0;

        normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);

        hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.02f, Animation.PlayMode.LOOP_PINGPONG);

        brokenAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);

        turnedOffAnimation = new Animation("sprites/reactor.png", 80, 80
        );


        turnOff();
    }


    public double getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return damage;
    }


    public boolean isOn() {
        return isRunning;
    }
    public void turnOn() {
        if (damage == 100) {
            return;
        }
        isRunning = true;



        Animation();
    }

    public void turnOff() {
        isRunning = false;



        Animation();
    }

    public void increaseTemperature(int increment) {
        if(increment>0){
            if(damage == 100 || !isRunning)
                return;

            double value;
            if(damage < 33) {
                value = 1.0;
            } else if (damage <= 66) {
                value = 1.5;
            } else {
                value = 2;
            }

            temperature = temperature+Math.ceil(value * increment);

            if (temperature > 2000 && damage < 100) {
                double tempDamage = (temperature - 2000) / (6000 - 2000) * 100.0;
                if (tempDamage < 100) {
                    damage = (int)tempDamage;
                    Animation();
                } else {
                    damage = 100;
                    turnOff();
                }
            }
        }



    }

    public void decreaseTemperature(int decrement) {

        if(decrement>0){
            if (damage == 100 || !isRunning || temperature == 0)
                return;

            temperature = temperature - damage < 50 ? decrement : 0.5 * decrement;


            if (temperature < 0) {
                temperature = 0;
            }

            Animation();
        }


    }




    private void Animation() {
        if (damage == 100) {
            setAnimation(brokenAnimation);
        } else if (!isRunning) {
            setAnimation(turnedOffAnimation);
        } else if (damage > 50) {
            setAnimation(hotAnimation);
        } else {
            setAnimation(normalAnimation);
        }
    }



    public void  repairWith(Hammer hammer){
        if (hammer == null || damage == 0 || damage == 100) {
            return;
        }

        hammer.use();



        int minusDamage = damage - 50;

        if(damage>50){
            hammer.use();
            damage = minusDamage;

        }

        else if(damage<=25){
            hammer.use();
            damage = 0;

        }

        Animation();

    }



    public void  repairWith(Mjolnir mjolnir){
        if (mjolnir == null || damage == 0 || damage == 100) {
            return;
        }

        mjolnir.use();



        int minusDamage = damage - 50;

        if(damage>50){
            mjolnir.use();
            damage = minusDamage;

        }

        else if(damage<=25){
            mjolnir.use();
            damage = 0;

        }

        Animation();

    }





    public void  extinguishWith(FireExtinguisher fireExtinguisher){
        if (fireExtinguisher == null || temperature != 0 || damage == 100) {
            return;
        }

        fireExtinguisher.use();



        double minusTemp = temperature - 4000.0;


        if(temperature>0){
            fireExtinguisher.use();
            temperature = temperature-4000.0;

        }

        Animation();



    }







    public void addLight(Light light) {
        this.light = light;
        this.light.setElectricityFlow(isRunning);
    }

    public void removeLight() {
        light = null;
    }


    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleOn(this);
    }
}
