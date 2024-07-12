package com.jarey.godliterature.challenge_literatura.entidad;

import com.jarey.godliterature.challenge_literatura.model.Autor;
import com.jarey.godliterature.challenge_literatura.model.Libro;
import com.jarey.godliterature.challenge_literatura.util.CadenaUtil;
import jakarta.persistence.*;

@Entity
@Table(name = "Libro")
public class Entidad_Libro {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String titulo;
        private String lenguaje;
        private Integer descargas;
        @OneToOne(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Entidad_Autor autor;

        public Entidad_Libro() {

        }

        public Entidad_Libro(Libro libro) {
            this.titulo = CadenaUtil.limitarLaLongitud(libro.titulo(), 200);
            this.descargas = libro.descarga();
            if (!libro.lenguaje().isEmpty())
                this.lenguaje = libro.lenguaje().get(0);
            if (!libro.autores().isEmpty()) {
                for (Autor autor : libro.autores()) {
                    this.autor = new Entidad_Autor(autor);
                    break;
                }
            }

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getLenguaje() {
            return lenguaje;
        }

        public void setLenguaje(String lenguaje) {
            this.lenguaje = lenguaje;
        }

        public Integer getDescargas() {
            return descargas;
        }

        public void setDescargas(Integer descargas) {
            this.descargas = descargas;
        }

        @Override
        public String toString() {
            return "Entidad_Libro [id=" + id + ", titulo=" + titulo + ", lenguaje=" + lenguaje + ", descargas=" + descargas
                    + ", autores=" + autor + "]";
        }

        public Entidad_Autor getAutor() {
            return autor;
        }

        public void setAutor(Entidad_Autor autor) {
            this.autor = autor;
        }

    }
