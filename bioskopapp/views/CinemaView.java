package bioskopapp.views;

import bioskopapp.entities.*;
import bioskopapp.services.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CinemaView {
    private BookingService bookingService;
    private Scanner scanner;

    public CinemaView(BookingService bookingService) {
        this.bookingService = bookingService;
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\nMENU BIOSKOP:");
            System.out.println("1. Lihat Daftar Film");
            System.out.println("2. Pesan Tiket");
            System.out.println("3. Batalkan Pesanan");
            System.out.println("0. Keluar");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showFilmList();
                    break;
                case 2:
                    handleBooking();
                    break;
                case 3:
                    handleCancellation();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void showFilmList() {
        System.out.println("\nDAFTAR FILM:");
        for (Film film : bookingService.getAllFilms()) {
            System.out.println(film.getId() + ". " + film.getTitle());
            System.out.println("   Genre: " + film.getGenre());
            System.out.println("   Durasi: " + film.getDuration());
            System.out.println("   Rating: " + film.getRating());
            System.out.println();
        }
    }

    private void handleBooking() {
        try {
            System.out.print("Masukkan ID film: ");
            int filmId = Integer.parseInt(scanner.nextLine());

            System.out.print("Masukkan nomor kursi: ");
            String seatNumber = scanner.nextLine();

            System.out.println("Pilih tipe studio (1: Regular, 2: VIP): ");
            String studioType = scanner.nextLine().equals("2") ? "VIP" : "Regular";

            LocalDateTime showTime = LocalDateTime.now();

            Ticket ticket = bookingService.bookTicket(filmId, seatNumber, showTime, studioType);
            System.out.println("Tiket berhasil dipesan!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleCancellation() {
        System.out.print("Masukkan ID tiket: ");
        int ticketId = Integer.parseInt(scanner.nextLine());
        bookingService.cancelBooking(ticketId);
        System.out.println("Tiket berhasil dibatalkan!");
    }
}