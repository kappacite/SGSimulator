package fr.kappacite.sgsimulator;

import fr.kappacite.sgsimulator.player.DefensePlayer;
import fr.kappacite.sgsimulator.player.Player;
import fr.kappacite.sgsimulator.player.defense.Defense;
import fr.kappacite.sgsimulator.player.ships.Ship;
import fr.kappacite.sgsimulator.simulator.Simulator;

public class Main {

   /* public static void main(String[] args){

        Player etoile = new Player(0, 0, 0, "étoile de glace");
        Player godrix = new Player(0, 0, 0, "GODRIX");

        Ship pilleurs = new Ship(18, 293d, 394d);
        Ship attaque = new Ship(178, 293d, 0d);

        DefensePlayer defensePlayer = new DefensePlayer(godrix);
        defensePlayer.addDefenses(new Defense(97, 54d, DefensePlayer.DefenseType.LASER_TURRET), 13);
        defensePlayer.addDefenses(new Defense(7, 4d, DefensePlayer.DefenseType.GATLING_TURRET), 95);

        godrix.setDefense(defensePlayer);
        etoile.addShip(pilleurs, 5);
        etoile.addShip(attaque, 3);

        Simulator.simule(etoile, godrix, System.currentTimeMillis()/1000);

    }*/

    public static void testShield(double maxShield, int[] damagePerTurn){

        double shield = maxShield;

        for(int i = 1;i <(damagePerTurn.length+1); i++){

            shield-=damagePerTurn[i-1];
            System.out.println("TURN " + i + " DAMAGE = " + damagePerTurn[i-1]);

            double damageRegen = (((maxShield-shield)*0.1)*(12-(i+1)));
            System.out.println("TURN " + i + " SHIELD = " + shield + " REGEN = " + damageRegen);
            shield+=damageRegen;
            System.out.println("TURN " + i + " FINAL SHIELD = " + shield);

        }


    }

}
