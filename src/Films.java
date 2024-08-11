abstract class Films {
    protected String id;
    protected String title;
    protected String language;
    protected String runtime;
    protected String country;
    protected String directors;
    protected String cast;

    public Films(String id, String title, String language, String runtime, String country, String directors,
            String cast) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.runtime = runtime;
        this.country = country;
        this.directors = directors;
        this.cast = cast;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    abstract String getWriters();
    abstract String getGenre();
    abstract String getReleaseDate();
}
