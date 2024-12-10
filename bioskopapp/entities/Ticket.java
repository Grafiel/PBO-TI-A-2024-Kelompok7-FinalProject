package bioskopapp.entities;

import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private Film film;
    private String seatNumber;
    private LocalDateTime showTime;
    private String studioType;
    private double price;
    private String paymentMethod;

    public Ticket(Film film, String seatNumber, LocalDateTime showTime, String studioType) {
        this.film = film;
        this.seatNumber = seatNumber;
        this.showTime = showTime;
        this.studioType = studioType;
        this.price = calculatePrice();
    }

    private double calculatePrice() {
        return studioType.equals("VIP") ? 100000 : 50000;
    }

    // Getters
    public int getId() { return id; }
    public Film getFilm() { return film; }
    public String getSeatNumber() { return seatNumber; }
    public LocalDateTime getShowTime() { return showTime; }
    public String getStudioType() { return studioType; }
    public double getPrice() { return price; }
    public String getPaymentMethod() { return paymentMethod; }

    // Setter with validation (Encapsulation)
    public void setPaymentMethod(String method) {
        if (method.equals("Kartu Kredit") || method.equals("E-wallet") || method.equals("Transfer Bank")) {
            this.paymentMethod = method;
        } else {
            throw new IllegalArgumentException("Metode pembayaran tidak valid");
        }
    }

    public void setId(int id) {
        this.id = id;
    }
}