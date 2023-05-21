package com.uditsharma.springwebflux.controller;

import com.uditsharma.springwebflux.dto.Product;
import com.uditsharma.springwebflux.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/all-products")
    public List<Product> getProductDetails(){
        return productService.getAllProducts();
    }

    @GetMapping(value = "/all-products-stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> getProductDetailsStream(){
        return productService.getProductStream();
    }
}
