    package sk.tuke.kpi.oop.game.sk.tuke.kpi.oop.game.tools;

    import sk.tuke.kpi.gamelib.framework.AbstractActor;

    public abstract class BreakableTool extends AbstractActor {
        private int remainingUses;


        public void use() {
            remainingUses--;

            if (remainingUses == 0) {
                getScene().removeActor(this);
            }
        }


    }
