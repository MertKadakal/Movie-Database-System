import java.util.ArrayList;

public class MovieDatabaseSys {

    public static void main(String[] args) {
        String[] commands_txt = FileInput.readFile("src\\IO_1\\commands.txt", true, true);
        String[] films_txt = FileInput.readFile("src\\IO_1\\films.txt", true, true);
        String[] people_txt = FileInput.readFile("src\\IO_1\\people.txt", true, true);
        String output_path = "src\\output.txt";

        //obtain people datas
        ArrayList<Person> peopleList = new ArrayList<>();
        for (int i =0; i < people_txt.length; i++) {
            String[] person = people_txt[i].split("\t");
            switch (person[0]) {
                case ("Director"):
                    peopleList.add(new ArtistDirector(person[1], person[2], person[3], person[4], person[5]));
                    break;
                case ("Writer"):
                    peopleList.add(new ArtistWriter(person[1], person[2], person[3], person[4], person[5]));
                    break;
                case ("Actor"):
                    peopleList.add(new ArtistPerformerActor(person[1], person[2], person[3], person[4], person[5]));
                    break;
                case ("ChildActor"):
                    peopleList.add(new ArtistPerformerChildActor(person[1], person[2], person[3], person[4], person[5]));
                    break;
                case ("StuntPerformer"):
                    peopleList.add(new ArtistPerformerStuntPerformer(person[1], person[2], person[3], person[4], person[5], person[6]));
                    break;
                case ("User"):
                    peopleList.add(new User(person[1], person[2], person[3], person[4]));
                    break;
            }
        }

        //obtain films datas
        ArrayList<Films> filmList = new ArrayList<>();
        for (int i =0; i < films_txt.length; i++) {
            String[] film = films_txt[i].split("\t");
            switch (film[0]) {
                case ("FeatureFilm"):
                    filmList.add(new FilmFeature(film[1], film[2], film[3], film[4], film[5], film[6], film[7], film[8], film[9], film[10], film[11]));
                    break;
                case ("ShortFilm"):
                    filmList.add(new FilmShort(film[1], film[2], film[3], film[4], film[5], film[6], film[7], film[8], film[9], film[10]));
                    break;
                case ("TVSeries"):
                    filmList.add(new FilmTvseries(film[1], film[2], film[3], film[4], film[5], film[6], film[7], film[8], film[9], film[10], film[11], film[12], film[13]));
                    break;
                case ("Documentary"):
                    filmList.add(new FilmDocumentaries(film[1], film[2], film[3], film[4], film[5], film[6], film[7], film[8]));
                    break;
            }
        }
    }
}