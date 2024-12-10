package bioskopapp.services;

import bioskopapp.entities.*;
import bioskopapp.repositories.*;
import java.time.LocalDateTime;
import java.util.List;

// Polymorphism through interface implementation
public class BookingServiceImpl implements BookingService {
    private FilmRepository filmRepository;

    public BookingServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> getAllFilms() {
        return filmRepository.getAllFilms();
    }

    @Override
    public Film getFilmById(int id) {
        return filmRepository.getFilmById(id);
    }

    @Override
    public Ticket bookTicket(int filmId, String seatNumber, LocalDateTime showTime, String studioType) {
        Film film = getFilmById(filmId);
        if (film == null) {
            throw new IllegalArgumentException("Film tidak ditemukan");
        }
        return new Ticket(film, seatNumber, showTime, studioType);
    }

    @Override
    public void cancelBooking(int ticketId) {
        // Implementation for ticket cancellation
    }

    @Override
    public void processPayment(int ticketId, String paymentMethod) {
        // Implementation for payment processing
    }
}