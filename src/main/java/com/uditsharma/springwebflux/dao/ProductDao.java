package com.uditsharma.springwebflux.dao;

import com.uditsharma.springwebflux.dto.Product;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ProductDao {

    private static void executeSleep(int i){
        try {
            System.out.println("Product "+i +" Sleeping for 1 min");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Product> getProducts(){
        return IntStream.range(0,10).peek(ProductDao::executeSleep).
                mapToObj((i)->new Product(i,"Product "+i)).
                collect(Collectors.toList());
    }
    public Flux<Product> getProductsStrem(){
        return Flux.range(0,10).
                delayElements(Duration.ofMillis(1000)).
                doOnNext(i->System.out.println("Processing count "+i)).
                map((i)->new Product(i,"Product "+i));
    }

    public Flux<Product> getProductsList(){
        return Flux.range(0,10).
                doOnNext(i->System.out.println("Processing count "+i)).
                map((i)->new Product(i,"Product "+i));
    }
}
