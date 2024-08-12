import java.util.ArrayList;

public class MovieDatabaseSys {

    public static void main(String[] args) {
        String[] commands_txt = FileInput.readFile("src\\IO_1\\commands.txt", true, true);
        String[] films_txt = FileInput.readFile("src\\IO_1\\films.txt", true, true);
        String[] people_txt = FileInput.readFile("src\\IO_1\\people.txt", true, true);
        String output_path = "src\\output.txt";
        FileOutput.writeToFile(output_path, "", false, false);

        //obtain people datas
        ArrayList<Person> peopleList = new ArrayList<>();
        for (int i =0; i < people_txt.length; i++) {
            String[] person = people_txt[i].split("\t");
            switch (person[0]) {
                case ("Director:"):
                    peopleList.add(new ArtistDirector(person[1], person[2], person[3], person[4], person[5]));
                    break;
                case ("Writer:"):
                    peopleList.add(new ArtistWriter(person[1], person[2], person[3], person[4], person[5]));
                    break;
                case ("Actor:"):
                    peopleList.add(new ArtistPerformerActor(person[1], person[2], person[3], person[4], person[5]));
                    break;
                case ("ChildActor:"):
                    peopleList.add(new ArtistPerformerChildActor(person[1], person[2], person[3], person[4], person[5]));
                    break;
                case ("StuntPerformer:"):
                    peopleList.add(new ArtistPerformerStuntPerformer(person[1], person[2], person[3], person[4], person[5], person[6]));
                    break;
                case ("User:"):
                    peopleList.add(new User(person[1], person[2], person[3], person[4]));
                    break;
            }
        }

        //obtain films datas
        ArrayList<Films> filmList = new ArrayList<>();
        for (int i =0; i < films_txt.length; i++) {
            String[] film = films_txt[i].split("\t");
            switch (film[0]) {
                case ("FeatureFilm:"):
                    filmList.add(new FilmFeature(film[1], film[2], film[3], film[4], film[5], film[6], film[7], film[8], film[9], film[10], film[11]));
                    break;
                case ("ShortFilm:"):
                    filmList.add(new FilmShort(film[1], film[2], film[3], film[4], film[5], film[6], film[7], film[8], film[9], film[10]));
                    break;
                case ("TVSeries:"):
                    filmList.add(new FilmTvseries(film[1], film[2], film[3], film[4], film[5], film[6], film[7], film[8], film[9], film[10], film[11], film[12], film[13]));
                    break;
                case ("Documentary:"):
                    filmList.add(new FilmDocumentaries(film[1], film[2], film[3], film[4], film[5], film[6], film[7], film[8]));
                    break;
            }
        }

        //execute commands
        for (int i =0; i < commands_txt.length; i++) {
            String[] command_line = commands_txt[i].split("\t");
            switch (command_line[0]) {
                case "RATE":
                    for (Person person : peopleList) {
                        if (person instanceof User) {
                            if (person.getId().equals(command_line[1])) {
                                ((User)person).getRates().put(command_line[2], command_line[3]);
                            }
                        }
                    }
                    break;
                case "ADD":
                    filmList.add(new FilmFeature(command_line[2], command_line[3], command_line[4], command_line[5], command_line[6], command_line[7], command_line[8], command_line[9], command_line[10], command_line[11], command_line[12]));
                    break;
                case "VIEWFILM":
                    
                    for (int j = 0; j<filmList.size(); j++) {
                        if (filmList.get(j).id.equals(command_line[1])) {
                            if ((filmList.get(j) instanceof FilmFeature) || (filmList.get(j) instanceof FilmShort)) {
                                Films theFilm;
                                if ((filmList.get(j) instanceof FilmFeature)) {
                                    theFilm = (FilmFeature) filmList.get(j);
                                }
                                else {
                                    theFilm = (FilmShort) filmList.get(j);
                                }

                                //convert writer ids to names
                                StringBuilder tem_writers = new StringBuilder("");
                                for (int k = 0; k<theFilm.getWriters().split(",").length; k++) {
                                    for (int m = 0; m<peopleList.size(); m++) {
                                        if (theFilm.getWriters().split(",")[k].equals(peopleList.get(m).id)) {
                                            tem_writers.append(peopleList.get(m).name + " " + peopleList.get(m).surname + ", ");
                                        }
                                    }
                                }
                                tem_writers.delete(tem_writers.length()-2, tem_writers.length());
                                
                                //convert director ids to names
                                StringBuilder tem_directors = new StringBuilder("");
                                for (int k = 0; k<theFilm.getDirectors().split(",").length; k++) {
                                    for (int m = 0; m<peopleList.size(); m++) {
                                        if (theFilm.getDirectors().split(",")[k].equals(peopleList.get(m).id)) {
                                            tem_directors.append(peopleList.get(m).name + " " + peopleList.get(m).surname + ", ");
                                        }
                                    }
                                }
                                tem_directors.delete(tem_directors.length()-2, tem_directors.length());

                                //convert performer ids to names
                                StringBuilder tem_stars = new StringBuilder("");
                                for (int k = 0; k<theFilm.getCast().split(",").length; k++) {
                                    for (int m = 0; m<peopleList.size(); m++) {
                                        if (theFilm.getCast().split(",")[k].equals(peopleList.get(m).id)) {
                                            tem_stars.append(peopleList.get(m).name + " " + peopleList.get(m).surname + ", ");
                                        }
                                    }
                                }
                                tem_stars.delete(tem_stars.length()-2, tem_stars.length());

                                //calculate the average rating score
                                Double total_rating = 0.0;
                                int total_users_rated = 0;
                                for (int k = 0; k<peopleList.size(); k++) {
                                    if (peopleList.get(k) instanceof User) {
                                        User user = (User) peopleList.get(k);
                                        if (user.getRates().containsKey(theFilm.id)) {
                                            total_rating += Integer.parseInt(user.getRates().get(theFilm.id));
                                            total_users_rated++;
                                        }
                                    }
                                }
                                String rating;
                                if (total_users_rated == 0) {
                                    rating = "Awaiting for votes";
                                }
                                else {
                                    rating = String.format("Ratings: %s/10 from %d users", String.valueOf(total_rating/total_users_rated), total_users_rated);
                                }
                                FileOutput.writeToFile(output_path, String.format("%s\t%s\n\n%s\t(%s)\n%s\nWriters: %s\nDirectors: %s\nStars: %s\n%s\n-----------------------------------\n\n", command_line[0], theFilm.id, theFilm.title, theFilm.getReleaseDate().substring(6, 10), theFilm.getGenre(), tem_writers, tem_directors, tem_stars, rating),true, false);
                            }
                            else if ((filmList.get(j) instanceof FilmDocumentaries)) {
                                FilmDocumentaries theFilm = (FilmDocumentaries) filmList.get(j);

                                //convert director ids to names
                                StringBuilder tem_directors = new StringBuilder("");
                                for (int k = 0; k<theFilm.getDirectors().split(",").length; k++) {
                                    for (int m = 0; m<peopleList.size(); m++) {
                                        if (theFilm.getDirectors().split(",")[k].equals(peopleList.get(m).id)) {
                                            tem_directors.append(peopleList.get(m).name + " " + peopleList.get(m).surname + ", ");
                                        }
                                    }
                                }
                                tem_directors.delete(tem_directors.length()-2, tem_directors.length());

                                //convert performer ids to names
                                StringBuilder tem_stars = new StringBuilder("");
                                for (int k = 0; k<theFilm.getCast().split(",").length; k++) {
                                    for (int m = 0; m<peopleList.size(); m++) {
                                        if (theFilm.getCast().split(",")[k].equals(peopleList.get(m).id)) {
                                            tem_stars.append(peopleList.get(m).name + " " + peopleList.get(m).surname + ", ");
                                        }
                                    }
                                }
                                tem_stars.delete(tem_stars.length()-2, tem_stars.length());

                                //calculate the average rating score
                                Double total_rating = 0.0;
                                int total_users_rated = 0;
                                for (int k = 0; k<peopleList.size(); k++) {
                                    if (peopleList.get(k) instanceof User) {
                                        User user = (User) peopleList.get(k);
                                        if (user.getRates().containsKey(theFilm.id)) {
                                            total_rating += Integer.parseInt(user.getRates().get(theFilm.id));
                                            total_users_rated++;
                                        }
                                    }
                                }
                                String rating;
                                if (total_users_rated == 0) {
                                    rating = "Awaiting for votes";
                                }
                                else {
                                    rating = String.format("Ratings: %s/10 from %d users", String.valueOf(total_rating/total_users_rated), total_users_rated);
                                }
                                FileOutput.writeToFile(output_path, String.format("%s\t%s\n\n%s\t(%s)\n\nDirectors: %s\nStars: %s\n%s\n-----------------------------------\n\n", command_line[0], theFilm.id, theFilm.title, theFilm.getReleaseDate().substring(6, 10), tem_directors, tem_stars, rating),true, false);
                            }
                            else if ((filmList.get(j) instanceof FilmTvseries)) {
                                FilmTvseries theFilm = (FilmTvseries) filmList.get(j);

                                //convert writer ids to names
                                StringBuilder tem_writers = new StringBuilder("");
                                for (int k = 0; k<theFilm.getWriters().split(",").length; k++) {
                                    for (int m = 0; m<peopleList.size(); m++) {
                                        if (theFilm.getWriters().split(",")[k].equals(peopleList.get(m).id)) {
                                            tem_writers.append(peopleList.get(m).name + " " + peopleList.get(m).surname + ", ");
                                        }
                                    }
                                }
                                tem_writers.delete(tem_writers.length()-2, tem_writers.length());

                                //convert director ids to names
                                StringBuilder tem_directors = new StringBuilder("");
                                for (int k = 0; k<theFilm.getDirectors().split(",").length; k++) {
                                    for (int m = 0; m<peopleList.size(); m++) {
                                        if (theFilm.getDirectors().split(",")[k].equals(peopleList.get(m).id)) {
                                            tem_directors.append(peopleList.get(m).name + " " + peopleList.get(m).surname + ", ");
                                        }
                                    }
                                }
                                tem_directors.delete(tem_directors.length()-2, tem_directors.length());
 
                                //convert performer ids to names
                                StringBuilder tem_stars = new StringBuilder("");
                                for (int k = 0; k<theFilm.getCast().split(",").length; k++) {
                                    for (int m = 0; m<peopleList.size(); m++) {
                                        if (theFilm.getCast().split(",")[k].equals(peopleList.get(m).id)) {
                                            tem_stars.append(peopleList.get(m).name + " " + peopleList.get(m).surname + ", ");
                                        }
                                    }
                                }
                                tem_stars.delete(tem_stars.length()-2, tem_stars.length());
 
                                //calculate the average rating score
                                Double total_rating = 0.0;
                                int total_users_rated = 0;
                                for (int k = 0; k<peopleList.size(); k++) {
                                    if (peopleList.get(k) instanceof User) {
                                        User user = (User) peopleList.get(k);
                                        if (user.getRates().containsKey(theFilm.id)) {
                                            total_rating += Integer.parseInt(user.getRates().get(theFilm.id));
                                            total_users_rated++;
                                        }
                                    }
                                }
                                String rating;
                                if (total_users_rated == 0) {
                                    rating = "Awaiting for votes";
                                }
                                else {
                                    rating = String.format("Ratings: %s/10 from %d users", String.valueOf(total_rating/total_users_rated), total_users_rated);
                                }
                                FileOutput.writeToFile(output_path, String.format("%s\t%s\n\n%s\t(%s-%s)\n%s seasons, %s episodes\n%s\nWriters: %s\nDirectors: %s\nStars: %s\n%s\n-----------------------------------\n\n", command_line[0], theFilm.id, theFilm.title, theFilm.getStartDate().substring(6, 10), theFilm.getEndDate().substring(6, 10), theFilm.getSeasonNumber(), theFilm.getEpisodeNumber(), theFilm.getGenre().replace(",", ", "), tem_writers, tem_directors, tem_stars, rating),true, false);
                            }
                            break;
                        }
                    }
                    break;
                case "REMOVE":
                    String user_to_remove_rate = command_line[2];
                    String rate_to_be_removed = command_line[3];

                    for (int j = 0; j<peopleList.size(); j++) {
                        if (peopleList.get(j).id.equals(user_to_remove_rate)) {
                            User user = (User) peopleList.get(j);
                            user.getRates().remove(rate_to_be_removed);
                        }
                    }
            }
        }
    }
}