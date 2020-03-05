package co.com.adminsalas.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import co.com.adminsalas.model.Salas;


public interface SalaRepositorio extends ReactiveMongoRepository<Salas, String>{

}
