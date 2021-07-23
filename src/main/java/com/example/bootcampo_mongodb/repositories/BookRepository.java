package com.example.bootcampo_mongodb.repositories;

import com.example.bootcampo_mongodb.entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<List<Book>> findBooksByAuthor(String author);
}
