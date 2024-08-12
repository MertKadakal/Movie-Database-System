public class FilmDocumentaries extends Films {
    private String releaseDate;

    public FilmDocumentaries(String id, String title, String language, String directors, String runtime, String country, 
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

    @Override
    String getWriters() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWriters'");
    }

    @Override
    String getGenre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGenre'");
    }
}
