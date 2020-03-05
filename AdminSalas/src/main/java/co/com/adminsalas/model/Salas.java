package co.com.adminsalas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Salas {

	@Id
	private String id;

	private String bloque;
	private String piso;
	private String capacidad;
	private String nombreSala;
	private String horasDisponibles;
}