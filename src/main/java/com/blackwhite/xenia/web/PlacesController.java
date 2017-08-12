package com.blackwhite.xenia.web;

import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackwhite.xenia.domain.Place;
import com.blackwhite.xenia.service.PlacesService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/place")
@AllArgsConstructor
public class PlacesController {

	private final PlacesService placesService;
	
    @GetMapping
    Flux<Place> all() {
        return placesService.all();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Mono<Place> byId(@PathVariable ObjectId id) {
        return placesService.byId(id);
    }
}
