package com.uditsharma.springwebflux.service;


import com.uditsharma.springwebflux.dao.ProductDao;
import com.uditsharma.springwebflux.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Service

public class ProductService {

    @Autowired
    ProductDao productDao;


    public List<Product> getAllProducts(){
        Long startTime = System.currentTimeMillis();
        List<Product> customerList = productDao.getProducts();
        Long endTime = System.currentTimeMillis();
        System.out.println("Total time in Execution :" + (endTime-startTime));
        return customerList;
    }

    public Flux<Product> getProductStream() {
        return Flux.range(0,10).
                delayElements(Duration.ofMillis(1000)).
                doOnNext(i->System.out.println("Processing count "+i)).
                map((i)->new Product(i,"Product "+i));
    }
}
