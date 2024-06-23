package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tb_empleado")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmpleadoEntity {
	@Id
	@Column(name = "dni_empleado", length = 8, nullable = false, unique = true, columnDefinition = "CHAR(8)")
	private String dniEmpleado;
	
	@Column(name= "nombre_empleado", length = 45, nullable = false)
	private String nombreEmpleado;
	
	@Column(name= "apellido_empleado", length = 45, nullable = false)
	private String apellidoEmpleado;
	
    @Column(name= "fecha_nacimiento",
            nullable = false,
            updatable= false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
	
	@Column(name= "direccion", length = 45, nullable = false)
	private String direccion;
	
	@Column(name= "correo", length = 45, nullable = false, unique = true)
	private String correo;
	
	@ManyToOne
	@JoinColumn(name = "area_id", nullable = false	)
	private AreaEntity areaId;
	
	

}
