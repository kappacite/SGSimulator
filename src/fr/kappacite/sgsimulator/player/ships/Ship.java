package fr.kappacite.sgsimulator.player.ships;

import fr.kappacite.sgsimulator.player.FightObject;
import fr.kappacite.sgsimulator.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ship implements FightObject {

    private List<ShipParts> shipParts;
    public int brutArmament;
    public Double brutCoque;
    public Double brutShield;
    public int brutMaxShield;

    public int armament;
    public double coque;
    public double shield;
    private double damageTakenToShield;
    public double maxShield;
    private Player player;
    private UUID uuid;
    private String name;

    public Ship(Player player) {
        this.shipParts = new ArrayList<>();
        this.player = player;
        this.uuid = UUID.randomUUID();
    }

    public Ship(int armament, Double coque, Double shield){
        this.armament = armament;
        this.coque = coque;
        this.shield = shield;
        this.maxShield = shield;

        this.brutArmament = armament;
        this.brutShield = shield;
        this.brutCoque = coque;
    }

    public void addShipParts(List<ShipParts> shipParts){
        for (ShipParts shipPart : shipParts) {
            this.addShipParts(shipPart, 1);
        }
    }

    public void addShipParts(ShipParts shipParts, int amount){

        for(int i = 0; i<amount; i++){
            this.shipParts.add(shipParts);

            this.brutArmament+=shipParts.getArmament();
            this.brutCoque+=shipParts.getCoque();
            this.brutShield+=shipParts.getShield();

            this.armament = (int) Math.floor(brutArmament*(Math.pow(1.1, player.getArmament())));
            this.coque = (int) Math.floor(brutCoque*(Math.pow(1.1, player.getCoque())));
            this.shield = (int) Math.floor(brutShield*(Math.pow(1.1, player.getShield())));

            this.maxShield = shield;
        }
    }

    public int getBrutArmement(){ return brutArmament; }

    public Double getBrutShield(){ return brutShield; }

    public Double getBrutCoque(){ return brutCoque; }

    @Override
    public Integer getArmament() {
        return armament;
    }

    @Override
    public Double getCoque() {
        return coque;
    }

    @Override
    public Double getShield() {
        return shield;
    }

    @Override
    public String toString(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[SHIP] ARMEMENT(" + this.getBrutArmement() + ") = "
                + this.getArmament() + " COQUE(" + this.getBrutCoque() + ") = " + this.getCoque() + " SHIELD(" + this.getBrutShield() + ") = " + this.getShield() + "\n");
        /*for (ShipParts shipPart : this.shipParts) {
            stringBuilder.append(shipPart.toString());
        }*/

        return stringBuilder.toString();

    }

    @Override
    public void regenShield(int round) {

        if(this.maxShield == 0) return;

        double roundMultiplier = (double) (round-2)/10;
        if(roundMultiplier > 1) roundMultiplier = 1;

        //int damageRegen = (int) (this.damageTakenToShield*(1-(roundMultiplier-0.030)));

        double damageRegen = (((this.maxShield-this.shield)*0.1)*(12-(round)));

        System.out.println("regen " + (round-1) + "->" + (round) + " = " + damageRegen);

        if(damageRegen < 0) damageRegen = 0;

        this.shield+=damageRegen;

        if(this.shield > this.maxShield) this.shield = this.maxShield;
    }

    @Override
    public void removeShield(){
        this.damageTakenToShield = (int) this.shield;
        this.shield = 0;
    }

    @Override
    public void removeShield(double shield) {
        this.damageTakenToShield = shield;
        this.shield-=shield;
    }

    @Override
    public void removeCoque(double coque) {
        this.coque-=coque;
    }

    public Ship clone(){

        if(this.shipParts == null){
            Ship ship = new Ship(this.armament, this.coque, this.shield);
            ship.setName(this.name);
            return ship;
        }

        Ship ship = new Ship(this.player);
        ship.addShipParts(this.shipParts);

        return ship;

    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
