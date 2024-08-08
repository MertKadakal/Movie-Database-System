public class ArtistPerformerStuntPerformer extends ArtistPerformer {
    private String height;

    public ArtistPerformerStuntPerformer(String id, String name, String surname, String country, String height) {
        super(id, name, surname, country);
        this.height = height;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
