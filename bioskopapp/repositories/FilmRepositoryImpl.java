package bioskopapp.repositories;

import bioskopapp.entities.Film;
import bioskopapp.config.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Polymorphism through interface implementation
public class FilmRepositoryImpl implements FilmRepository {
    private Connection conn;

    public FilmRepositoryImpl() {
        this.conn = Database.getConnection();
    }

    @Override
    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM films");

            while(rs.next()) {
                Film film = new Film(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("duration")
                );
                film.setRating(rs.getDouble("rating"));
                films.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }

    @Override
    public Film getFilmById(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM films WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                Film film = new Film(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("duration")
                );
                film.setRating(rs.getDouble("rating"));
                return film;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addFilm(Film film) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO films (title, genre, duration, rating) VALUES (?, ?, ?, ?)"
            );
            pstmt.setString(1, film.getTitle());
            pstmt.setString(2, film.getGenre());
            pstmt.setString(3, film.getDuration());
            pstmt.setDouble(4, film.getRating());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFilm(Film film) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE films SET title=?, genre=?, duration=?, rating=? WHERE id=?"
            );
            pstmt.setString(1, film.getTitle());
            pstmt.setString(2, film.getGenre());
            pstmt.setString(3, film.getDuration());
            pstmt.setDouble(4, film.getRating());
            pstmt.setInt(5, film.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFilm(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM films WHERE id=?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}