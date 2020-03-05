package co.com.adminsalas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.adminsalas.model.Salas;
import co.com.adminsalas.repo.SalaRepositorio;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/salas")
@AllArgsConstructor
public class SalasController {

	private SalaRepositorio repository;

	/**
	 * Controlador que devuelve todos las salas
	 * 
	 * @return Flux<Salas>
	 */
	@GetMapping
	public Flux<Salas> getAllSalas() {
		return repository.findAll();
	}

	/**
	 * Controlador que devuelve una sala identificado con el id
	 * 
	 * @return Mono<ResponseEntity<Salas>>
	 */
	@GetMapping("{id}")
	public Mono<ResponseEntity<Salas>> getSala(@PathVariable String id) {
		return repository.findById(id).map(sala -> ResponseEntity.ok(sala))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	/**
	 * Controlador que almacena un sala enviado en el body
	 * 
	 * @param sala
	 * @return Mono<Salas>
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Salas> saveSala(@RequestBody Salas sala) {
		return repository.save(sala);

	}

	/**
	 * Controlador que elimina una sala por el id
	 * 
	 * @param id
	 * @return <ResponseEntity<Void>>
	 */
	@DeleteMapping("{id}")
	public Mono<ResponseEntity<Void>> deleteSala(@PathVariable(value = "id") String id) {
		return repository.findById(id).flatMap(
				existingSala -> repository.delete(existingSala).then(Mono.just(ResponseEntity.ok().<Void>build())))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	/**
	 * Controlador que elimina todos las salas
	 * 
	 * @return Mono<Void>
	 */
	@DeleteMapping
	public Mono<Void> deleteAllSalas() {
		return repository.deleteAll();
	}

}
