package com.example.demo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {




	    private final List<Especialidad> especialidades = new ArrayList<>();

	    public EspecialidadController() {
	        especialidades.add(new Especialidad(
	                1,
	                "Medicina interna",
	                "Diagnóstico y tratamiento integral de nuestros pacientes.",
	                "Caninos y felinos"
	        ));
	        especialidades.add(new Especialidad(
	                2,
	                "Cirugía",
	                "Procedimientos quirúrgicos con equipo especializado.",
	                "Caninos y felinos"
	        ));
	        especialidades.add(new Especialidad(
	                3,
	                "Laboratorio",
	                "Análisis clínicos con resultados confiables y oportunos.",
	                "Caninos y felinos"
	        ));
	        especialidades.add(new Especialidad(
	                4,
	                "Dermatología Veterinaria",
	                "Tratamiento especializado de alergias, infecciones de piel y oídos.",
	                "Caninos y felinos"
	        ));
	        especialidades.add(new Especialidad(
	                5,
	                "Animales Exóticos",
	                "Atención médica integral, nutrición y prevención para mascotas no convencionales.",
	                "Aves, reptiles y pequeños mamíferos"
	        ));
	        especialidades.add(new Especialidad(
	                6,
	                "Odontología Veterinaria",
	                "Profilaxis dental ultrasónica, extracciones y prevención de enfermedades bucales.",
	                "Caninos y felinos"
	        ));
	        especialidades.add(new Especialidad(
	                7,
	                "Traumatología y Ortopedia",
	                "Evaluación y corrección de fracturas, luxaciones y problemas articulares.",
	                "Caninos y felinos"
	        ));
	    }

	   
	    @GetMapping
	    public List<Especialidad> obtenerTodas() {
	        return especialidades;
	    }


	    @GetMapping("/{id}")
	    public ResponseEntity<Especialidad> obtenerPorId(@PathVariable int id) {
	        Optional<Especialidad> encontrada = especialidades.stream()
	                .filter(e -> e.getId() == id)
	                .findFirst();

	        return encontrada
	                .map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	}