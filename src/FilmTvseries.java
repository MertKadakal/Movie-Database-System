public class FilmTvseries extends Films {
    private String startDate;
    private String endDate;
    private String seasonNumber;
    private String episodeNumber;
    private String genre;
    private String writers;

    public FilmTvseries(String id, String title, String language, String directors, String runtime, String country, 
            String cast, String genre, String writers, String startDate, String endDate, String seasonNumber, String episodeNumber) {
        super(id, title, language, runtime, country, directors, cast);
        this.startDate = startDate;
        this.endDate = endDate;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.genre = genre;
        this.writers = writers;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(String seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(String episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    @Override
    String getReleaseDate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReleaseDate'");
    }
}
