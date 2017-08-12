package com.blackwhite.xenia.domain.data.mongodb;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.blackwhite.xenia.domain.Place;

public interface PlacesRepository extends ReactiveMongoRepository<Place, ObjectId> {

}
