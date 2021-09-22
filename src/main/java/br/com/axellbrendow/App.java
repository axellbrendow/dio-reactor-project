package br.com.axellbrendow;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class App 
{
    public static void main( String[] args )
    {
        // Mono.just("Hello World!")
        //     .subscribe(log::info);

        // Flux.just(1, 2, 3, 4, 5)
        //     .subscribe(i -> log.info("num: {}", i));

        // Flux.range(1, 5)
        //     .repeat()
        //     .subscribe(i -> log.info("num: {}", i));

        // Flux.from(Mono.justOrEmpty(Optional.ofNullable(null)))
        //     .repeat()
        //     .subscribe(v -> log.info("just or empty: {}", v));

        // Flux.from(Mono.justOrEmpty(Optional.ofNullable("Hello World")))
        //     .repeat()
        //     .subscribe(v -> log.info("just or empty: {}", v));

        Flux.from(Mono.error(new RuntimeException("test")))
            .repeat()
            .subscribe(
                v -> log.info("just or empty: {}", v),
                error -> log.error("Error: {}", error),
                () -> log.info("Completed")
            );
    }
}
