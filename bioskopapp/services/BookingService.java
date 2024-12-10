package bioskopapp.services;

import bioskopapp.entities.*;
import java.time.LocalDateTime;
import java.util.List;

// Abstraction with interface
public interface BookingService {
    List<Film> getAllFilms();
    Film getFilmById(int id);
    Ticket bookTicket(int filmId, String seatNumber, LocalDateTime showTime, String studioType);
    void cancelBooking(int ticketId);
    void processPayment(int ticketId, String paymentMethod);
}