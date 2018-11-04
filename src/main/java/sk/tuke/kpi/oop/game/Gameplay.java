package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.gamelib.map.MapMarker;
import sk.tuke.kpi.oop.game.tools.Hammer;

import java.util.Map;

public class Gameplay extends Scenario {


    @Override
    public void setupPlay(@NotNull Scene scene) {
        Map<String, MapMarker> markers = scene.getMap().getMarkers();
        Reactor reactor = new Reactor();

        MapMarker reactorArea1 = markers.get("reactor-area-1");

        scene.addActor(reactor, reactorArea1.getPosX(), reactorArea1.getPosY());
        reactor.turnOn();


        Cooler cooler = new Cooler(reactor);
        MapMarker coolerArea1 = markers.get("cooler-area-1");
        scene.addActor(cooler, coolerArea1.getPosX(), coolerArea1.getPosY());
        cooler.turnOn();
        new ActionSequence<>(
            new Wait(5),
            new Invoke(cooler::turnOn) //referencia na metodu
        ).scheduleOn(cooler); //namapovanie na objekt,vykona sa ked sa vytvori cooler

        Hammer hammer= new Hammer();
        scene.addActor(hammer,  60,60);

        new ActionSequence<>(
            new Wait(10),
            new Invoke(()->{reactor.increaseTemperature(4000);}), //lambda výrazu
            new Invoke(()->{reactor.decreaseTemperature(1000);}),
            new Wait(10),
            new Invoke (()->{reactor.repairWith(hammer);})
        ).scheduleOn(scene);
    }

}

