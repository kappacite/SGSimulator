package fr.kappacite.sgsimulator.simulator;

import fr.kappacite.sgsimulator.Main;
import fr.kappacite.sgsimulator.player.FightObject;
import fr.kappacite.sgsimulator.player.Player;
import fr.kappacite.sgsimulator.player.defense.Defense;
import fr.kappacite.sgsimulator.player.ships.Ship;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulator {

    public static File simule(Player opponant, Player defenser, long timestamp){

        System.out.println("-------COMBAT : " + opponant.getName() + "(ATK) VS " + defenser.getName() + "(DEF)-------");

        List<Turn> turns = new ArrayList<>();

        List<Defense> defense = new ArrayList<>(defenser.getDefense().getDefenses());

        List<Ship> opponentShips = new ArrayList<>(opponant.getShips());
        List<Ship> defenserShips = new ArrayList<>(defenser.getShips());

        List<FightObject> fightObjectsDefender = new ArrayList<>();
        fightObjectsDefender.addAll(defense);
        fightObjectsDefender.addAll(defenserShips);

        int opponentSize = opponentShips.size();
        int defenderSize = fightObjectsDefender.size();

        Collections.sort(opponentShips, new ShipComparator());
        Collections.sort(fightObjectsDefender, new ShipComparator());
        Collections.reverse(fightObjectsDefender);

        for(int i = 1; i<=14; i++){

            if(opponentShips.size() == 0 || fightObjectsDefender.size() == 0) break;

            Turn turn = new Turn(i, opponant, defenser, timestamp);

            double opponentArmament = 0;
            double defenderArmament = 0;

            List<Ship> toRemoveOpponents = new ArrayList<>();
            List<FightObject> toRemoveFightObjectDefenders = new ArrayList<>();

            for (Ship opponentShip : opponentShips) {
                opponentArmament+=opponentShip.getArmament();
            }

            for (FightObject fightObject : fightObjectsDefender) {
                defenderArmament+=fightObject.getArmament();
            }

            System.out.println("total damage " + (i) + " atk: " + opponentArmament);

            System.out.println("total damage " + (i) + " def: " + defenderArmament);

            for (FightObject fightObject : fightObjectsDefender) {

                fightObject.regenShield(i);

                boolean hasShield = fightObject.getShield() > 0;

                if(opponentArmament <= 0) break;

                if(hasShield){
                    if(opponentArmament > fightObject.getShield()){
                        opponentArmament-= fightObject.getShield();
                        fightObject.removeShield();
                    } else if(opponentArmament == fightObject.getShield()){
                        fightObject.removeShield();
                        break;
                    } else if(opponentArmament < fightObject.getShield()){
                        fightObject.removeShield(opponentArmament);
                        break;
                    }
                }

                if(opponentArmament > fightObject.getCoque()){
                    opponentArmament-=fightObject.getCoque();
                    fightObject.removeCoque(fightObject.getCoque());
                    turn.addDefenderLoses(fightObject);
                    toRemoveFightObjectDefenders.add(fightObject);
                }else if(opponentArmament == fightObject.getCoque()){
                    fightObject.removeCoque(fightObject.getCoque());
                    turn.addDefenderLoses(fightObject);
                    toRemoveFightObjectDefenders.add(fightObject);
                    break;
                }else if(opponentArmament < fightObject.getCoque()){
                    fightObject.removeCoque(opponentArmament);
                    break;
                }

            }

            for (Ship opponentShip : opponentShips) {

                opponentShip.regenShield(i);

                boolean hasShield = opponentShip.getShield() > 0;

                if(defenderArmament <= 0) break;

                System.out.println("(" + i + ") " + opponentShip);

                if(hasShield){
                    if(defenderArmament > opponentShip.getShield()){
                        defenderArmament-= opponentShip.getShield();
                        opponentShip.removeShield();
                    } else if(defenderArmament == opponentShip.getShield()){
                        opponentShip.removeShield();
                        break;
                    } else if(defenderArmament < opponentShip.getShield()){
                        opponentShip.removeShield(defenderArmament);
                        System.out.println("AFTER: " + opponentShip + " DAMAGE = " + (defenderArmament));
                        break;
                    }
                }




                if(defenderArmament > opponentShip.getCoque()){
                    defenderArmament-= opponentShip.getCoque();
                    opponentShip.removeCoque(opponentShip.getCoque());
                    turn.addOpponentShipLose(opponentShip);
                    toRemoveOpponents.add(opponentShip);
                }else if(defenderArmament == opponentShip.getCoque()){
                    opponentShip.removeCoque(opponentShip.getCoque());
                    turn.addOpponentShipLose(opponentShip);
                    toRemoveOpponents.add(opponentShip);
                    defenderArmament-=opponentShip.getCoque();
                    break;
                }else if(defenderArmament < opponentShip.getCoque()) {
                    opponentShip.removeCoque(defenderArmament);
                    defenderArmament -= opponentShip.getCoque();
                    break;
                }
            }

            toRemoveOpponents.forEach(opponentShips::remove);
            toRemoveFightObjectDefenders.forEach(fightObjectsDefender::remove);


            System.out.println(turn.toString());

        }

        File output = new File("/home/simulator/result/" + timestamp + ".txt");
        try {
            if(!output.exists()) output.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(output, true));

            writer.append("\n\n--------FIN DU COMBAT--------\n");
            writer.append("Perte attaquant: ").append(String.valueOf(opponentSize - opponentShips.size())).append("\n");
            writer.append("Perte défenseur: ").append(String.valueOf(defenderSize - fightObjectsDefender.size())).append("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;

    }

}
