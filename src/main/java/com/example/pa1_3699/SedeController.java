package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sedes")
public class SedeController {

	private final List<Sede> sedes = new ArrayList<>();
	 
    public SedeController() {
        sedes.add(new Sede(1, "Sede Chavez", "Av. Jorge Chávez # 258", "Miraflores", "01 445 1200", "Lunes a domingo, 24 horas"));
        sedes.add(new Sede(2, "Sede Benavides", "Av. Benavides # 1241", "Miraflores", "01 445 1201", "Lunes a domingo, 24 horas"));
        sedes.add(new Sede(3, "Sede La Molina", "Ca. Caobas # 164", "La Molina", "01 445 1202", "Lunes a sábado, 8:00 am - 10:00 pm"));
        sedes.add(new Sede(4, "Sede Magdalena", "Av. Juan de Aliaga # 410", "Magdalena", "01 445 1203", "Lunes a sábado, 8:00 am - 10:00 pm"));
        sedes.add(new Sede(5, "Sede San Borja", "Av. Gálvez Barrenechea # 819", "San Borja", "01 445 1204", "Lunes a domingo, 24 horas"));
    }
 
    /** Devuelve todas las sedes. */
    @GetMapping
    public List<Sede> obtenerTodas() {
        return sedes;
    }
 
    /** Devuelve una sede por su id, o 404 si no existe. */
    @GetMapping("/{id}")
    public ResponseEntity<Sede> obtenerPorId(@PathVariable int id) {
        Optional<Sede> encontrada = sedes.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
 
        return encontrada
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
 
    /** Filtra las sedes por distrito, ej: /api/sedes/buscar?distrito=Miraflores */
    @GetMapping("/buscar")
    public List<Sede> buscarPorDistrito(@RequestParam String distrito) {
        return sedes.stream()
                .filter(s -> s.getDistrito().equalsIgnoreCase(distrito))
                .collect(Collectors.toList());
    }
}