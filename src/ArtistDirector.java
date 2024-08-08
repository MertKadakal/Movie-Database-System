public class ArtistDirector extends Artist {
    private String agent;

    public ArtistDirector(String id, String name, String surname, String country, String agent) {
        super(id, name, surname, country);
        this.agent = agent;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
