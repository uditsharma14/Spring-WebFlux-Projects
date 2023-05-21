package com.uditsharma.springwebflux;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest{

    @Test
    public void testMono(){
        Mono<String> stringMono = Mono.just("Udit").log();
        stringMono.subscribe(System.out::println);
    }

    @Test
    public void testMonoWithException(){
        Mono<?> stringMono = Mono.just("Udit")
                        .then(Mono.error(new RuntimeException("Exception occured with publisher")))
                        .log();
        stringMono.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
    }


    @Test
    public void testFlux(){
        Flux<String> stringFlux = Flux.just("Udit","Tvisha","Rekha","Sharma").
                concatWithValues("GoodGuys").
                concatWith(Flux.error(new RuntimeException("Exception Occured with Flux Publisher"))).
                concatWithValues("This Will not Execute");
        stringFlux.subscribe(System.out::println);
    }
}

