package fr.kappacite.sgsimulator.player;

import fr.kappacite.sgsimulator.player.defense.Defense;
import fr.kappacite.sgsimulator.player.ships.Ship;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int armament;
    private int shield;
    private int coque;
    private String name;

    private DefensePlayer defense;

    private List<Ship> ships = new ArrayList<>();

    public Player(int armament, int shield, int coque, String name) {
        this.armament = armament;
        this.shield = shield;
        this.coque = coque;
        this.defense = new DefensePlayer(this);
        this.name = name;
    }

    public int getArmament() {
        return armament;
    }

    public int getShield() {
        return shield;
    }

    public int getCoque() {
        return coque;
    }


    public void addShip(Ship ship, int amount){
        for(int i = 0; i<amount; i++){
            this.addShip(ship.clone());
        }
    }

    public void addShip(Ship ship){
        this.ships.add(ship);
    }

    public void removeShip(Ship ship){

        for (Ship ship1 : this.ships) {
            if(ship1.getName().equalsIgnoreCase(ship.getName())) {
                this.ships.remove(ship1);
                return;
            }
        }


    }

    public double getArmamentMultiplier(){
        return Math.pow(1.1, this.getArmament());
    }

    public double getShieldMultiplier(){
        return Math.pow(1.1, this.getShield());
    }

    public double getCoqueMultiplier(){
        return Math.pow(1.1, this.getCoque());
    }

    public DefensePlayer getDefense() {
        return defense;
    }

    public void setDefense(DefensePlayer defense) {
        this.defense = defense;
    }

    public int[] getStats(){
        int armament = 0;
        int coque = 0;
        int shield = 0;
        for (Ship ship : this.ships) {
            armament+=ship.getArmament();
            coque+=ship.getCoque();
            shield+=ship.getShield();
        }

        for (Defense defense : this.getDefense().getDefenses()) {
            armament+=defense.getArmament();
            coque+=defense.getCoque();
        }

        return new int[]{armament, coque, shield};
    }

    public List<Ship> getShips() {
        return ships;
    }

    public String toString(){
        return "[PLAYER] ARMEMENT = " + getStats()[0] + " COQUE = " + getStats()[1] + " SHIELD = " + getStats()[2];

    }

    public String getName() {
        return name;
    }
}
