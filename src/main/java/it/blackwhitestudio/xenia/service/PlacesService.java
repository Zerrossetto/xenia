package it.blackwhitestudio.xenia.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import it.blackwhitestudio.xenia.domain.Place;
import it.blackwhitestudio.xenia.domain.data.mongodb.PlacesRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class PlacesService {

	private final PlacesRepository repository;
	
    public Mono<Place> byId(ObjectId id) {
        return repository.findById(id);
    }
    
    public Flux<Place> all() {
        return this.repository.findAll();
    }
}
