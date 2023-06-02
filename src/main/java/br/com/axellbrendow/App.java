package br.com.axellbrendow;

import java.time.Instant;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class App {
    private static int a = 5;

    private static void monoHelloWorld() {
        Mono.just("Hello World!")
                .subscribe(log::info);
    }

    private static void fluxJust() {
        Flux.just(1, 2, 3, 4, 5)
                .subscribe(i -> log.info("num: {}", i));
    }

    private static void fluxRangeAndRepeat() {
        Flux.range(1, 5)
                .repeat()
                .subscribe(i -> log.info("num: {}", i));
    }

    private static void fluxFromMonoFromOptionalFromNull() {
        Flux.from(Mono.justOrEmpty(Optional.ofNullable(null)))
                .repeat()
                .subscribe(v -> log.info("just or empty: {}", v));
    }

    private static void fluxFromMonoFromOptionalFromString() {
        Flux.from(Mono.justOrEmpty(Optional.ofNullable("Hello World")))
                .repeat()
                .subscribe(v -> log.info("just or empty: {}", v));
    }

    private static void fluxFromMonoError() {
        Flux.from(Mono.error(new RuntimeException("test")))
                .repeat()
                .subscribe(
                        v -> log.info("just or empty: {}", v),
                        error -> log.error("Error: {}", error),
                        () -> log.info("Completed"));
    }

    private static void monoDefer() {
        // Subscribers of this mono will always get the initial value of a
        var just = Mono.just(a);

        // Mono.defer will generate a new Mono.just for each subscriber rerunning the
        // lambda function
        var defer = Mono.defer(() -> Mono.just(a));

        just.subscribe(v -> System.out.println("just: " + v));
        defer.subscribe(v -> System.out.println("defer: " + v));

        a = 7;

        just.subscribe(v -> System.out.println("just: " + v));
        defer.subscribe(v -> System.out.println("defer: " + v));
    }

    private static void fluxIndex() {
        Flux.range(2010, 10)
                .index()
                .subscribe(v -> log.info("[{}] = {}", v.getT1(), v.getT2()));
    }

    private static void fluxIndexWithTimestamp() {
        Flux.range(2010, 10)
                .timestamp()
                .index()
                .subscribe(v -> {
                    Long index = v.getT1();
                    Instant isoDate = Instant.ofEpochMilli(v.getT2().getT1());
                    Integer value = v.getT2().getT2();
                    log.info("[{}] = Date: {}, Value: {}", index, isoDate, value);
                });
        /*
         * Prints:
         * [0] = Date: 2023-06-02T23:26:59.366Z, Value: 2010
         * [1] = Date: 2023-06-02T23:26:59.381Z, Value: 2011
         * [2] = Date: 2023-06-02T23:26:59.381Z, Value: 2012
         * [3] = Date: 2023-06-02T23:26:59.381Z, Value: 2013
         * [4] = Date: 2023-06-02T23:26:59.381Z, Value: 2014
         * [5] = Date: 2023-06-02T23:26:59.381Z, Value: 2015
         * [6] = Date: 2023-06-02T23:26:59.381Z, Value: 2016
         * [7] = Date: 2023-06-02T23:26:59.382Z, Value: 2017
         * [8] = Date: 2023-06-02T23:26:59.382Z, Value: 2018
         * [9] = Date: 2023-06-02T23:26:59.382Z, Value: 2019
         */
    }

    private static void fluxConcatMonosAndReduce() {
        Flux.concat(Mono.just(1), Mono.just(2))
                .reduce(0, Integer::sum)
                .subscribe(v -> log.info("v = {}", v));
    }

    public static void main(String[] args) {
        // monoHelloWorld();
        // fluxJust();
        // fluxRangeAndRepeat();
        // fluxFromMonoFromOptionalFromNull();
        // fluxFromMonoFromOptionalFromString();
        // fluxFromMonoError();
        // monoDefer();
        // fluxIndex();
        fluxIndexWithTimestamp();
        // fluxConcatMonosAndReduce();
    }
}
