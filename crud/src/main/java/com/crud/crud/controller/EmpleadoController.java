package com.crud.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crud.crud.exepcion.Exepcion;
import com.crud.crud.model.Empleado;
import com.crud.crud.repository.EmpleadoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmpleadoController {
    
    @Autowired
	private EmpleadoRepositorio empleadoRepositorio;
	
	@GetMapping("/empleados")
	public List<Empleado> tenerEmpleado(){
		return empleadoRepositorio.findAll();
	}		
	
	@PostMapping("/empleados")
	public Empleado crearEmpleado(@RequestBody Empleado empleado) {
		return empleadoRepositorio.save(empleado);
	}
	
	@GetMapping("/empleados/{id}")
	public ResponseEntity<Empleado> tenerEmpleadoID(@PathVariable Long id) {
		Empleado empleado = empleadoRepositorio.findById(id)
				.orElseThrow(() -> new Exepcion("No existe"));
		return ResponseEntity.ok(empleado);
	}
	
	
	@PutMapping("/empleados/{id}")
	public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleado){
		Empleado empl = empleadoRepositorio.findById(id)
				.orElseThrow(() -> new Exepcion("No existe"));
		
		empl.setFirstName(empleado.getFirstName());
		empl.setLastName(empleado.getLastName());
		empl.setEmailId(empleado.getEmailId());
		
		Empleado actualizarEmpleado = empleadoRepositorio.save(empleado);
		return ResponseEntity.ok(actualizarEmpleado);
	}
	
	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Long id){
		Empleado empleado = empleadoRepositorio.findById(id)
				.orElseThrow(() -> new Exepcion("No existe"));
		
		empleadoRepositorio.delete(empleado);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
