package com.uditsharma.springwebflux.dao;

import com.uditsharma.springwebflux.dto.Product;
import org.springframework.stereotype.Component;

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
        return IntStream.range(0,10).peek(ProductDao::executeSleep).mapToObj((i)->new Product(i,"Product "+i)).
                collect(Collectors.toList());
    }
}
