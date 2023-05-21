package com.uditsharma.springwebflux.router;

import com.uditsharma.springwebflux.handler.ProductHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    ProductHandler productHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        System.out.println("router function got registered");
        return RouterFunctions.route().
                GET("/router/products",
                        productHandler::loadProducts).
                GET("/router/product-stream",
                        productHandler::loadProductsStream).
                GET("/router/product/{productId}",productHandler::findProduct).
                POST("/router/saveproduct",productHandler::saveProduct).
                build();
    }
}
