package bioskopapp.repositories;

import bioskopapp.entities.Film;
import java.util.List;

// Abstraction with interface
public interface FilmRepository {
    List<Film> getAllFilms();
    Film getFilmById(int id);
    void addFilm(Film film);
    void updateFilm(Film film);
    void deleteFilm(int id);
}