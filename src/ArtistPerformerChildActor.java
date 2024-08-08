public class ArtistPerformerChildActor extends ArtistPerformer {
    private String age;

    public ArtistPerformerChildActor(String id, String name, String surname, String country, String age) {
        super(id, name, surname, country);
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
