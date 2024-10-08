public class ArtistPerformerStuntPerformer extends ArtistPerformer {
    private String height;
    private String actors;

    public ArtistPerformerStuntPerformer(String id, String name, String surname, String country, String height, String actors) {
        super(id, name, surname, country);
        this.height = height;
        this.actors = actors;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }
}