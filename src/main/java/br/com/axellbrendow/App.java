package br.com.axellbrendow;

import java.time.Instant;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class App 
{
    private static int a = 5;

    public static void main( String[] args )
    {
        // -------------------------------------------------------------------------------
        // Mono.just("Hello World!")
        //     .subscribe(log::info);

        // -------------------------------------------------------------------------------
        // Flux.just(1, 2, 3, 4, 5)
        //     .subscribe(i -> log.info("num: {}", i));

        // -------------------------------------------------------------------------------
        // Flux.range(1, 5)
        //     .repeat()
        //     .subscribe(i -> log.info("num: {}", i));

        // -------------------------------------------------------------------------------
        // Flux.from(Mono.justOrEmpty(Optional.ofNullable(null)))
        //     .repeat()
        //     .subscribe(v -> log.info("just or empty: {}", v));

        // -------------------------------------------------------------------------------
        // Flux.from(Mono.justOrEmpty(Optional.ofNullable("Hello World")))
        //     .repeat()
        //     .subscribe(v -> log.info("just or empty: {}", v));

        // -------------------------------------------------------------------------------
        // Flux.from(Mono.error(new RuntimeException("test")))
        //     .repeat()
        //     .subscribe(
        //         v -> log.info("just or empty: {}", v),
        //         error -> log.error("Error: {}", error),
        //         () -> log.info("Completed")
        //     );

        // -------------------------------------------------------------------------------
        // // Subscribers of this mono will always get the initial value of a
        // var just = Mono.just(a);

        // // Mono.defer will generate a new Mono.just for each subscriber rerunning the lambda function
        // var defer = Mono.defer(() -> Mono.just(a));

        // just.subscribe(v -> System.out.println("just: " + v));
        // defer.subscribe(v -> System.out.println("defer: " + v));

        // a = 7;

        // just.subscribe(v -> System.out.println("just: " + v));
        // defer.subscribe(v -> System.out.println("defer: " + v));

        // -------------------------------------------------------------------------------
        // Flux.range(2010, 10)
        //     .index()
        //     .subscribe(v -> log.info("[{}] = {}", v.getT1(), v.getT2()));

        // -------------------------------------------------------------------------------
        // Flux.range(2010, 10)
        //     .timestamp()
        //     .index()
        //     .subscribe(v -> {
        //         Long index = v.getT1();
        //         Instant isoDate = Instant.ofEpochMilli(v.getT2().getT1());
        //         Integer value = v.getT2().getT2();
        //         log.info("[{}] = Date: {}, Value: {}", index, isoDate, value);
        //     });

        // -------------------------------------------------------------------------------
        Flux.concat(Mono.just(1), Mono.just(2))
            .reduce(0, Integer::sum)
            .subscribe(v -> log.info("v = {}", v));
    }
}
