package playground;

import playground.SealedInterfaces.Result.Err;
import playground.SealedInterfaces.Result.Ok;
import playground.SealedInterfaces.WebEvent.*;

class SealedInterfaces {

    sealed interface WebEvent permits PageLoad, PageUnload, KeyPress, Paste, Click {
        record PageLoad() implements WebEvent {};
        record PageUnload() implements WebEvent {};
        record KeyPress(char character) implements WebEvent {};
        record Paste(String data) implements WebEvent {};
        record Click(double x, double y) implements WebEvent {};
    }

    sealed interface Result<T> permits Err, Ok {
        record Ok<T>(T value) implements Result<T> {};
        record Err<T>(Throwable err) implements Result<T> {};
    }

    interface Do {
        Result<?> theThing(int n);
    }

    static void thisIsJustRust() {
        Do lambdaaaa = (int i) -> i == 1 ?
                new Ok<String>("Yay!") :
                new Err<Throwable>(new Exception("sadge :("));

        var res = lambdaaaa.theThing(42);

        switch (res) {
            case Ok<?> ok -> System.out.println(ok.value());
            case Err<?> err -> System.out.println(err.err().getMessage());
        };
    }

    static void inspect(WebEvent event) {
        switch (event) {
            case PageLoad ignored -> System.out.println("Page loaded!");
            case PageUnload ignored -> System.out.println("Page unloaded!");
            case KeyPress kp when kp.character() == 'w' -> System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWW");
            case KeyPress kp -> System.out.println("Pressed: " + kp.character());
            case Paste p -> System.out.println("Pasted: " + p.data());
            case Click c -> System.out.println("Clicked at: x=" + c.x() + ", y=" + c.y());
            case null -> System.out.println("null sadge :(");
        }
    }
}
