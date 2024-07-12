package com.jarey.godliterature.challenge_literatura.client;

import com.jarey.godliterature.challenge_literatura.entidad.Entidad_Autor;
import com.jarey.godliterature.challenge_literatura.entidad.Entidad_Libro;
import com.jarey.godliterature.challenge_literatura.map.ConvertirDatos;
import com.jarey.godliterature.challenge_literatura.model.Resultados;
import com.jarey.godliterature.challenge_literatura.repository.IAutor;
import com.jarey.godliterature.challenge_literatura.repository.ILibro;
import com.jarey.godliterature.challenge_literatura.service.ConsumoAPI;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvertirDatos conversor = new ConvertirDatos();

    private ILibro libroRepositorio;
    private IAutor autorRepositorio;

    public Principal(ILibro libroRepositorio, IAutor autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void menu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
					Selecciona alguna de las opciones con los numeros disponibles:
						1.- Buscar libro por titulo
						2.- Lista liibros registrados
						3.- Lista autores registrados
						4.- Lista autores vivos en un determinado año
						5.- Listar libros por idioma
						0 - Salir
						""";
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    buscarLibros();
                    break;
                case 3:
                    buscarAutores();
                    break;
                case 4:
                    buscarAutoresVivo();
                    break;
                case 5:
                    buscarPorIdiomas();
                    break;
                case 0:
                    System.out.println("Hasta luego, vuelve cuando quieras");
                    break;
                default:
                    System.out.println("Opción no encontrada");
            }
        }

    }

    private void buscarLibros() {

        List<Entidad_Libro> libros = libroRepositorio.findAll();

        if (!libros.isEmpty()) {

            for (Entidad_Libro libro : libros) {
                System.out.println("\n--------- LIBROS -------\n");
                System.out.println(" Titulo: " + libro.getTitulo());
                System.out.println(" Autor: " + libro.getAutor().getNombre());
                System.out.println(" Idioma: " + libro.getLenguaje());
                System.out.println(" Descargas: " + libro.getDescargas());
                System.out.println("\n-------------------------\n\n");
            }

        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
        }

    }

    private void buscarAutores() {
        List<Entidad_Autor> autores = autorRepositorio.findAll();

        if (!autores.isEmpty()) {
            for (Entidad_Autor autor : autores) {
                System.out.println("\n ---------- Autores -------\n");
                System.out.println(" Nombre: " + autor.getNombre());
                System.out.println(" Fecha de Nacimiento: " + autor.getFechaNacimiento());
                System.out.println(" Fecha de Fallecimiento: " + autor.getFechaFallecimiento());
                System.out.println(" Libros: " + autor.getLibros().getTitulo());
                System.out.println("\n-------------------------\n");
            }
        } else {
            System.out.println("\n----- NO SE ENCONTRARON RESULTADOS ----\n");

        }

    }

    private void buscarAutoresVivo() {
        System.out.println("Escriba el año para en el que vivio: ");
        var anio = teclado.nextInt();
        teclado.nextLine();

        List<Entidad_Autor> autores = autorRepositorio.findForYear(anio);

        if (!autores.isEmpty()) {
            for (Entidad_Autor autor : autores) {
                System.out.println("\n\n---------- Autores Vivos -------\n");
                System.out.println(" Nombre: " + autor.getNombre());
                System.out.println(" Fecha de nacimiento: " + autor.getFechaNacimiento());
                System.out.println(" Fecha de fallecimiento: " + autor.getFechaFallecimiento());
                System.out.println(" Libros: " + autor.getLibros().getTitulo());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- SIN RESULTADOS ENCONTRADOS ---- \n\n");

        }
    }

    private void buscarPorIdiomas() {

        var menu = """
				Seleccione un Idioma:
					1.- Español
					2.- Ingles
				
					""";
        System.out.println(menu);
        var idioma = teclado.nextInt();
        teclado.nextLine();

        String seleccion = "";

        if(idioma == 1) {
            seleccion = "es";
        } else 	if(idioma == 2) {
            seleccion = "en";
        }

        List<Entidad_Libro> libros = libroRepositorio.findForLanguaje(seleccion);

        if (!libros.isEmpty()) {

            for (Entidad_Libro libro : libros) {
                System.out.println("\n\n---------- LIBROS POR IDIOMA-------\n");
                System.out.println(" Titulo: " + libro.getTitulo());
                System.out.println(" Autor: " + libro.getAutor().getNombre());
                System.out.println(" Idioma: " + libro.getLenguaje());
                System.out.println(" Descargas: " + libro.getDescargas());
                System.out.println("\n-------------------------\n\n");
            }

        } else {
            System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
        }


    }

    private void buscarLibroWeb() {
        Resultados datos = getDatosSerie();

        if (!datos.resultado().isEmpty()) {

            Entidad_Libro libro = new Entidad_Libro(datos.resultado().get(0));
            libro = libroRepositorio.save(libro);

        }

        System.out.println("Datos: ");
        System.out.println(datos);
    }

    private Resultados getDatosSerie() {
        System.out.println("Ingresa el nombre del libro que deseas buscar: ");
        var titulo = teclado.nextLine();
        titulo = titulo.replace(" ", "%20");
        System.out.println("Titlulo : " + titulo);
        System.out.println(URL_BASE + titulo);
        var json = consumoApi.obtenerDatos(URL_BASE + titulo);
        System.out.println(json);
        Resultados datos = conversor.obtenerDatos(json, Resultados.class);
        return datos;
    }

}
