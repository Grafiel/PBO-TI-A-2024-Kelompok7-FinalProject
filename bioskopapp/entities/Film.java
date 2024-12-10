package bioskopapp.entities;

public class Film {
    private int id;
    private String title;
    private String genre;
    private String duration;
    private Double rating;

    // Encapsulation with private fields and public methods
    public Film(int id, String title, String genre, String duration) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = 0.0;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getDuration() { return duration; }
    public Double getRating() { return rating; }

    // Setter with validation (Encapsulation)
    public void setRating(Double rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Rating harus antara 0-5");
        }
    }
}