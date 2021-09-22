package br.com.axellbrendow;

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

        Flux.just(1, 2, 3, 4, 5)
            .subscribe(i -> log.info("num: {}", i));
    }
}
