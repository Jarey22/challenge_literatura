package com.jarey.godliterature.challenge_literatura.entidad;

import com.jarey.godliterature.challenge_literatura.model.Autor;


import com.jarey.godliterature.challenge_literatura.util.CadenaUtil;
import jakarta.persistence.*;

@Entity
@Table(name = "Autor")
public class Entidad_Autor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nombre;
        private Integer fechaNacimiento;
        private Integer fechaFallecimiento;


        @OneToOne
        @JoinTable(
                name = "Libro",
                joinColumns = @JoinColumn(name = "autor_id"),
                inverseJoinColumns = @JoinColumn(name = "id"))
        private Entidad_Libro libros;


        public Entidad_Autor() {

        }

        public Entidad_Autor(Autor autor) {
            this.nombre = CadenaUtil.limitarLaLongitud(autor.nombre(), 200);

            if (autor.cumpleanios() == null)
                this.fechaNacimiento = 1980;
            else
                this.fechaNacimiento = autor.cumpleanios();

            if (autor.aniomuerte() == null)
                this.fechaFallecimiento = 3022;
            else
                this.fechaFallecimiento = autor.aniomuerte();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Integer getFechaNacimiento() {
            return fechaNacimiento;
        }

        public void setFechaNacimiento(Integer fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        public Integer getFechaFallecimiento() {
            return fechaFallecimiento;
        }

        public void setFechaFallecimiento(Integer fechaFallecimiento) {
            this.fechaFallecimiento = fechaFallecimiento;
        }


        @Override
        public String toString() {
            return "Entidad_Autor [id=" + id + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento
                    + ", fechaFallecimiento=" + fechaFallecimiento + ", libro="  + "]";
        }

        public Entidad_Libro getLibros() {
            return libros;
        }

        public void setLibros(Entidad_Libro libros) {
            this.libros = libros;
        }

    }
