import java.util.ArrayList;

public class MovieDatabaseSys {

    public static void main(String[] args) {
        String[] commands_txt = FileInput.readFile("src\\IO_1\\commands.txt", true, true);
        String[] films_txt = FileInput.readFile("src\\IO_1\\films.txt", true, true);
        String[] people_txt = FileInput.readFile("src\\IO_1\\people.txt", true, true);
        String output_path = "src\\output.txt";

        ArrayList<Person> people = new ArrayList<>();
        //obtain people datas
        for (int i =0; i < people_txt.length; i++) {
            String[] person = people_txt[i].split("\t");
            switch (person[0]) {
                case ("Director"):
                    people.add(new ArtistDirector(person[1], person[2], person[3], person[4], person[5]));
                    break;
                case ("Writer"):
                    people.add(new ArtistWriter(person[1], person[2], person[3], person[4], person[5]));
                    break;
                case ("Actor"):
                    people.add(new ArtistPerformerActor(person[1], person[2], person[3], person[4], person[5]));
                    break;
                case ("ChildActor"):
                    people.add(new ArtistPerformerChildActor(person[1], person[2], person[3], person[4], person[5]));
                    break;
                case ("StuntPerformer"):
                    people.add(new ArtistPerformerStuntPerformer(person[1], person[2], person[3], person[4], person[5]));
                    break;
                case ("User"):
                    people.add(new User(person[1], person[2], person[3], person[4]));
                    break;
            }
        }
    }
}