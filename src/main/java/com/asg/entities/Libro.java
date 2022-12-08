package com.asg.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "libros")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "referencia")	
	private Integer referencia;
	
	@NotEmpty
	@Column(name = "titulo")
	private String titulo;
	
	@NotEmpty
	@Column(name = "autor")
	private String autor;	
	
	@Lob
	@Column(name = "sinopsis")
	private String sinopsis;
	
	@Column(name = "foto_portada")
	private String fotoPortada;
	
	@NotNull	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "fecha_adquisicion")	
	private Date fechaAdquisicion;	
	
	@Column(name = "expurgado")
	private boolean expurgado;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Socio socio;
	
	public Libro() {
		super();
	}
	
	public Libro(String titulo, String autor, Date fechaAdquisicion) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public Integer getReferencia() {
		return referencia;
	}

	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getFotoPortada() {
		return fotoPortada;
	}

	public void setFotoPortada(String fotoPortada) {
		this.fotoPortada = fotoPortada;
	}

	public Date getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public boolean isExpurgado() {
		return expurgado;
	}

	public void setExpurgado(boolean expurgado) {
		this.expurgado = expurgado;
	}
	
	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

}
