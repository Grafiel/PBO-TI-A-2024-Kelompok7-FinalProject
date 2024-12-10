package bioskopapp;

import bioskopapp.repositories.*;
import bioskopapp.services.*;
import bioskopapp.views.*;

public class Main {
    public static void main(String[] args) {
        // Initialize repositories
        FilmRepository filmRepo = new FilmRepositoryImpl();

        // Initialize services
        BookingService bookingService = new BookingServiceImpl(filmRepo);

        // Initialize view
        CinemaView view = new CinemaView(bookingService);

        // Run application
        view.showMainMenu();
    }
}