package com.example.demo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {
	
	
	
	  private final List<Servicio> servicios = new ArrayList<>();
	
	 public ServicioController() {
	        servicios.add(new Servicio(1, "Atención médica", "Consulta general y chequeos preventivos.", false));
	        servicios.add(new Servicio(2, "Telemedicina", "Orientación veterinaria a distancia, vía videollamada.", false));
	        servicios.add(new Servicio(3, "Internamiento", "Hospitalización con monitoreo constante.", true));
	        servicios.add(new Servicio(4, "Farmacia", "Medicamentos y productos veterinarios disponibles en sede.", false));
	        servicios.add(new Servicio(5, "Imágenes", "Radiografías, ecografías y estudios de diagnóstico por imágenes.", false));
	        servicios.add(new Servicio(6, "Petshop", "Alimentos, accesorios y productos para el cuidado diario.", false));
	        servicios.add(new Servicio(7, "Baño y peluquería", "Grooming profesional según el tipo de pelaje.", false));
	        servicios.add(new Servicio(8, "Emergencias", "Atención de urgencias todos los días del año.", true));
	    }

	    @GetMapping
	    public List<Servicio> obtenerTodos() {
	        return servicios;
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Servicio> obtenerPorId(@PathVariable int id) {
	        Optional<Servicio> encontrado = servicios.stream()
	                .filter(s -> s.getId() == id)
	                .findFirst();

	        return encontrado
	                .map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @GetMapping("/emergencia")
	    public List<Servicio> obtenerDisponibles24h() {
	        return servicios.stream()
	                .filter(Servicio::isDisponible24h)
	                .collect(Collectors.toList());
	    }
	}