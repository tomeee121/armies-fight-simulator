package org.example.fight.methods;

import java.util.Scanner;
import java.util.function.Supplier;

class StateTest {
    private interface State extends Supplier<State> {}

    public static void main(String[] args) {
        new StateTest().run();
    }

    public void run() {
        State state = this::start;
        while (state != null) {
            state = state.get();
        }
    }

    State start() {
        System.out.println("napisz: 'null' lub: lub: 'misio' lub: 'kot'");
        Scanner scanner = new Scanner(System.in);
        return switch (scanner.nextLine()) {
            case "null" -> null;
            case "misio" -> this::misio;
            case "kot" -> this::kot;
            default -> this::start;
        };
    }

    State misio() {
        System.out.println("w metodzie misio");
        return this::start;
    }

    State kot() {
        System.out.println("w metodzie kot");
        return this::start;
    }
}
