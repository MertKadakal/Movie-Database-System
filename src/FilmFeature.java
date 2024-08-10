public class FilmFeature extends Films {
    private String releaseDate;
    private String budget;
    private String writers;
    private String genre;

    public FilmFeature(String id, String title, String language, String directors, String runtime, String country,
            String cast, String genre, String releaseDate, String writers, String budget) {
        super(id, title, language, runtime, country, directors, cast);
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.writers = writers;
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
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
