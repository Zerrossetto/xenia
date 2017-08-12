package it.blackwhitestudio.xenia.domain.data.mongodb;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import it.blackwhitestudio.xenia.domain.Place;

public interface PlacesRepository extends ReactiveMongoRepository<Place, ObjectId> {

}
