package fr.kappacite.sgsimulator.player;

public interface FightObject {

    Integer getArmament();

    Double getCoque();

    Double getShield();

    String toString();

    String getName();

    void regenShield(int round);

    void removeShield(double shield);

    void removeShield();

    void removeCoque(double coque);

}
