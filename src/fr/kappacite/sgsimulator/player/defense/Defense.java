package fr.kappacite.sgsimulator.player.defense;

import fr.kappacite.sgsimulator.player.DefensePlayer;
import fr.kappacite.sgsimulator.player.FightObject;
import fr.kappacite.sgsimulator.player.Player;

public class Defense implements FightObject {

    private int armement;
    private Double coque;
    private DefensePlayer.DefenseType defenseType;
    private Player player;
    private String name;

    public Defense(Player player, DefensePlayer.DefenseType defenseType) {
        this.armement = (int) Math.floor(defenseType.getArmament()*player.getArmamentMultiplier());
        this.coque = Math.floor(defenseType.getCoque()*player.getCoqueMultiplier());;
        this.defenseType = defenseType;
        this.player = player;
    }

    public Defense(int armement, Double coque, DefensePlayer.DefenseType defenseType){
        this.armement = armement;
        this.coque = coque;
        this.defenseType = defenseType;
    }

    @Override
    public Integer getArmament() {
        return armement;
    }

    @Override
    public Double getCoque() {
        return coque;
    }

    @Override
    public Double getShield() { return 0d; }

    @Override
    public void regenShield(int round) {

    }

    @Override
    public void removeShield(double shield) {

    }

    @Override
    public void removeShield() { }

    @Override
    public void removeCoque(double coque) {
        this.coque-=coque;
    }

    public DefensePlayer.DefenseType getDefenseType() {
        return defenseType;
    }

    @Override
    public String toString(){
        String defence = "[DEFENCE] ARMEMENT = " + this.getArmament() + " COQUE = " + this.getCoque() + " TYPE = " + this.getDefenseType().toString();
        return defence;
    }

    public Defense clone(){

        if(this.player == null){
            Defense defense = new Defense(this.armement, this.coque, this.defenseType);
            defense.setName(this.name);
            return defense;
        }

        Defense defense = new Defense(this.player, this.defenseType);
        defense.setName(this.name);

        return defense;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
