package fr.kappacite.sgsimulator.simulator;

import fr.kappacite.sgsimulator.player.FightObject;
import fr.kappacite.sgsimulator.player.ships.Ship;

import java.util.Comparator;

public class ShipComparator implements Comparator<FightObject> {

    @Override
    public int compare(FightObject o1, FightObject o2) {

        int compare = o1.getArmament().compareTo(o2.getArmament());

        if(compare == 0){
            compare = o1.getShield().compareTo(o2.getShield());
        }

        if(compare == 0){
            compare = o1.getCoque().compareTo(o2.getCoque());
        }

        return compare;

    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
