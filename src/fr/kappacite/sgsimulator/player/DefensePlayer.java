package fr.kappacite.sgsimulator.player;

import fr.kappacite.sgsimulator.player.Player;
import fr.kappacite.sgsimulator.player.defense.Defense;

import java.util.ArrayList;
import java.util.List;

public class DefensePlayer {

    private List<Defense> defenses;
    private Player player;

    public int armement;
    public int coque;

    public DefensePlayer(Player player){
        this.defenses = new ArrayList<>();
        this.player = player;
    }

    public void addDefenses(Defense defense, int amount){
        for(int i = 0; i<amount; i++){
            this.defenses.add(defense.clone());

            this.armement+= defense.getArmament();
            this.coque+= (int) Math.round(defense.getCoque());
        }
    }

    public void removeDefense(Defense defenseType){
        this.defenses.remove(defenseType);

        this.armement-= defenseType.getArmament();
        this.coque-= (int) Math.round(defenseType.getCoque());
    }

    public List<Defense> getDefenses() {
        return defenses;
    }

    public int getArmement() {
        return armement;
    }

    public int getCoque() {
        return coque;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append("[DEFENCE] ARMEMENT = " + this.getArmement() + " COQUE = " + this.getCoque() + "\n");

        for (Defense defense : this.defenses) {
            builder.append(defense.getDefenseType().name() + " - ARMEMENT: " + defense.getArmament() + " COQUE: "
                    + defense.getCoque() + "\n");
        }
        return builder.toString();
    }

    public enum DefenseType {

        GATLING_TURRET(4.198, 2.822),
        LASER_TURRET(60.179, 40.077),
        ECM_TURRET(200.131, 150.15),
        ION_SATELLITE(1449.90, 1100.16);

        private double armament;
        private double coque;

        DefenseType(double armament, double coque) {
            this.armament = armament;
            this.coque = coque;
        }

        public double getArmament() {
            return armament;
        }

        public double getCoque() {
            return coque;
        }
    }
}
