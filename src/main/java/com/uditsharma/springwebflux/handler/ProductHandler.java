package com.uditsharma.springwebflux.handler;

import com.uditsharma.springwebflux.dao.ProductDao;
import com.uditsharma.springwebflux.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductHandler {
    @Autowired
    private ProductDao productDao;

    public Mono<ServerResponse> loadProducts(ServerRequest request) {
        Flux<Product> productList = productDao.getProductsList();
        return ServerResponse.ok().body(productList, Product.class);
    }

    public Mono<ServerResponse> loadProductsStream(ServerRequest request) {
        Flux<Product> productList = productDao.getProductsStrem();
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).
                body(productList,Product.class);
    }

    public Mono<ServerResponse> findProduct(ServerRequest request) {
         int productId = Integer.valueOf(request.pathVariable("productId"));
         System.out.print("Product Information is being retrieved for Id : "+productId);
         Mono<Product> productMono =  productDao.getProductsList().filter(c->c.getId() == productId).next();
         return ServerResponse.ok().body(productMono,Product.class);
    }

    public Mono<ServerResponse> saveProduct(ServerRequest request) {
        Mono<Product> product = request.bodyToMono(Product.class);
        Mono<String> saveReposne = product.map( dto->dto.getId()+ ":"+ dto.getProductName());
        return ServerResponse.ok().body(saveReposne,String.class);
    }
}
