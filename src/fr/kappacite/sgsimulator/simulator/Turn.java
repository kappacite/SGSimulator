package fr.kappacite.sgsimulator.simulator;

import fr.kappacite.sgsimulator.player.FightObject;
import fr.kappacite.sgsimulator.player.Player;
import fr.kappacite.sgsimulator.player.defense.Defense;
import fr.kappacite.sgsimulator.player.ships.Ship;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Turn {

    private List<Ship> opponentShipsLoses;

    private List<FightObject> defenderLoses;
    private int round;
    private Player opponent, defender;
    private long timestamp;

    public Turn(int round, Player opponent, Player defender, long timestamp){
        this.defenderLoses = new ArrayList<>();
        this.opponentShipsLoses = new ArrayList<>();
        this.round = round;
        this.opponent = opponent;
        this.defender = defender;
        this.timestamp = timestamp;
    }

    public void addOpponentShipLose(Ship ship){
        this.opponentShipsLoses.add(ship);
    }

    public List<Ship> getOpponentShipsLoses() {
        return opponentShipsLoses;
    }

    public void addDefenderLoses(FightObject fightObject){
        this.defenderLoses.add(fightObject);
    }

    public List<FightObject> getDefenderObjectsLose() {
        return defenderLoses;
    }

    public String toString(){

        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        builder.append("\n");
        builder.append("\n");
        builder.append("\n");
        builder.append("------------------------------------\n");
        builder.append("TOUR " + (round) + " " + opponent.getName() + " (ATK) vs " + defender.getName() + " (DEF)\n");
        builder.append("------------------------------------\n");

        for (FightObject fightObject : this.defenderLoses) {
            builder.append("[LOSE] [DEFENCE] (" + fightObject.getName() + ")" + fightObject.toString() + "\n");
        }

        for (Ship opponentShipsLose : opponentShipsLoses) {
            builder.append("[LOSE] [OPPONANT] (" + opponentShipsLose.getName() + ")" + opponentShipsLose.toString() + "\n");
        }

        builder.append("\n");
        builder.append("BILAN DEFENSE: " + this.defenderLoses.size() + " UNITÉS PERDUS\n");
        builder.append("BILAN ATTAQUE: " + this.opponentShipsLoses.size() + " UNITÉS PERDUS");

        File output = new File("/home/simulator/result/" + timestamp + ".txt");
        try {
            if(!output.exists()) output.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(output, true));
            writer.append(builder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public Player getOpponent() {
        return opponent;
    }

    public Player getDefender() {
        return defender;
    }
}
