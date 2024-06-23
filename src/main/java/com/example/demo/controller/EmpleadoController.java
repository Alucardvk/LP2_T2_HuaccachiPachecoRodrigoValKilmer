package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.entity.AreaEntity;
import com.example.demo.entity.EmpleadoEntity;
import com.example.demo.repository.AreaRepository;
import com.example.demo.repository.EmpleadoRepository;

@Controller
public class EmpleadoController {
	
	@Autowired
	private EmpleadoRepository repository;
	@Autowired
	private AreaRepository repository2;
	
	@GetMapping("/")
	public String Home(Model model) {
		List<EmpleadoEntity> listaempleado = repository.findAll();
		model.addAttribute("listaempleado",listaempleado);
		return "home";
	}
	
	@GetMapping("/registrar_empleado")
	public String showregistrarEmpleado(Model model) {
		List<AreaEntity> listaArea = repository2.findAll();
		model.addAttribute("listarareas", listaArea);
		model.addAttribute("emp", new EmpleadoEntity());
		return "registrar_empleado";
	}
	

	
	@PostMapping("/registrar_empleado")
	public String registrarEmpleado(@ModelAttribute EmpleadoEntity empleado, Model model) {
		if(repository.findByCorreo(empleado.getCorreo())!=null) {
			model.addAttribute("errorMessage", "El correo electronico ya esta en uso");
			model.addAttribute("emp", new EmpleadoEntity());
			return "registrar_empleado";
		}
		repository.save(empleado);
		return "redirect:/";
	}
	
	
    
    
    
    @GetMapping("/editar_empleado/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") String dniEmpleado, Model model) {
        EmpleadoEntity empleado = repository.findById(dniEmpleado).orElseThrow(null); 
        List<AreaEntity> listaAreas = repository2.findAll();
        model.addAttribute("listaAreas", listaAreas);
        model.addAttribute("empleado", empleado);
        return "actualizar_empleados"; 
    }

    @PostMapping("/actualizar_empleado")
    public String actualizarEmpleado(@ModelAttribute("empleado") EmpleadoEntity empleado) {
        repository.save(empleado); 
        return "redirect:/"; 
    }
	
	@GetMapping("/delete/{dniEmpleado}")
	public String eliminarEmpleado(@PathVariable("dniEmpleado")String dniEmpleado) {
		repository.deleteById(dniEmpleado);
		return "redirect:/";
	}
	
	
	
}
