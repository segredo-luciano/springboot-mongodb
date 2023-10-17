package com.segredo.webservicemongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.segredo.webservicemongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
