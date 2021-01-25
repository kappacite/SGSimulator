package fr.kappacite.sgsimulator.player.ships;

public enum ShipParts {

    PROJECTILE_TURRET(1, 0, 0, PartsType.ARMAMENT),
    MARK_III_MISSILE(10, 0, 0, PartsType.ARMAMENT),
    LASER_CANNON(18, 0, 0, PartsType.ARMAMENT),
    GOAULD_ION_CANNON(25, 0, 0, PartsType.ARMAMENT),
    GOAULD_SHIELD(0, 60, 0, PartsType.SHIELD),
    ASGARD_SHIELD(0, 175, 0, PartsType.SHIELD),
    TAURI_HULL(0, 0, 40, PartsType.COQUE),
    GOAULD_HULL(0, 0, 220, PartsType.COQUE);

    private int armament;
    private int shield;
    private int coque;
    private PartsType partsType;

    ShipParts(int armament, int shield, int coque, PartsType partsType) {
        this.armament = armament;
        this.shield = shield;
        this.coque = coque;
        this.partsType = partsType;
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

    public PartsType getPartsType() {
        return partsType;
    }

    public static enum PartsType {

        ARMAMENT,
        COQUE,
        SHIELD;

    }

    @Override
    public String toString(){
        return "PARTS: " + this.name() + " - ARMEMENT: " + this.armament + " COQUE: " + this.coque + " SHIELD: " + shield + "\n";
    }

}
