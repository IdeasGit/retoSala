package co.com.adminsalas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import co.com.adminsalas.model.Salas;
import co.com.adminsalas.repo.SalaRepositorio;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ApplicationAdminSalasWeb {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationAdminSalasWeb.class, args);
	}

	@Bean
	CommandLineRunner init(SalaRepositorio repository) {
		return args -> {

			Flux<Salas> sala = Flux.just(
					Salas.builder()
							.bloque("C")
							.piso("11")
							.capacidad("10")
							.nombreSala("Paramos")
							.horasDisponibles("11-12")
							.build(),
					Salas.builder()
							.bloque("B")
							.piso("7")
							.capacidad("15")
							.nombreSala("Paramos 2")
							.horasDisponibles("10-12")
							.build())
						.flatMap(p -> repository.save(p));

			sala.thenMany(repository.findAll()).subscribe(System.out::println);

		};
	}

}
