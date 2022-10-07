package org.example.game;

import java.util.ArrayList;
import java.util.List;

public interface CommandI {

    default Iterable<Bombardier> getBombardiers(Army army) {
        var iterator = army.iterator();
        List<Bombardier> bombardiers = new ArrayList<>();
        Warrior temp;
        while (iterator.hasNext()) {
            temp = iterator.next();
            if (temp instanceof Bombardier) {
                bombardiers.add((Bombardier) temp);
            }
        }
        return bombardiers;
    }

    void bombard(Army army);
}
