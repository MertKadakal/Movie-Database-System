public class FilmDocumentaries extends Films {
    private String releaseDate;

    public FilmDocumentaries(String id, String title, String language, String runtime, String country, String directors,
            String cast, String releaseDate) {
        super(id, title, language, runtime, country, directors, cast);
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
