package com.uditsharma.springwebflux.streaming.controller;

import com.uditsharma.springwebflux.streaming.service.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class StreamingServiceController {

    @Autowired
    private StreamingService streamingService;

    @GetMapping(value = "video/{title}",produces = "video/mp4")
    public Mono<Resource> getVideos(@PathVariable String title, @RequestHeader("Range") String range){
        System.out.println("Range in Bytes {} "+ range);
        return streamingService.getVideos(title);

    }
}
