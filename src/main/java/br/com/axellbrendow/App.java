package br.com.axellbrendow;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class App 
{
    public static void main( String[] args )
    {
        Mono.just("Hello World!")
            .subscribe(log::info);
    }
}
