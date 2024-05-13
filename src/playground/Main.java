package playground;

import static playground.SealedInterfaces.inspect;
import static playground.SealedInterfaces.thisIsJustRust;

public class Main {

    public static void main(String[] args) {
        thisIsJustRust();
        inspect(null);
    }
}