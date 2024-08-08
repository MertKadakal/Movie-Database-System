public class FilmShort extends Films {
    private String releaseDate;
    private String writers;
    private String genre;

    public FilmShort(String id, String title, String language, String runtime, String country, String directors,
            String cast, String releaseDate, String writers, String genre) {
        super(id, title, language, runtime, country, directors, cast);
        this.releaseDate = releaseDate;
        this.writers = writers;
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
