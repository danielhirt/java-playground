package playground;

import playground.SealedInterfaces.Result.Err;
import playground.SealedInterfaces.Result.Ok;

class SealedInterfaces {
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
}
