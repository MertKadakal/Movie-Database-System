import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MovieDatabaseSys {

    public static void main(String[] args) {
        String[] commands_txt = FileInput.readFile("src\\IO_1\\commands.txt", true, true);
        String[] films_txt = FileInput.readFile("src\\IO_1\\films.txt", true, true);
        String[] people_txt = FileInput.readFile("src\\IO_1\\people.txt", true, true);
        HashMap<String, ArrayList<String>> rated_films_by_user = new HashMap<>();
        HashMap<String, ArrayList<Integer>> rated_films_by_filmId = new HashMap<>();
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
                    rated_films_by_filmId.put(film[1], new ArrayList<>());
                    break;
                case ("ShortFilm:"):
                    filmList.add(new FilmShort(film[1], film[2], film[3], film[4], film[5], film[6], film[7], film[8], film[9], film[10]));
                    rated_films_by_filmId.put(film[1], new ArrayList<>());
                    break;
                case ("TVSeries:"):
                    filmList.add(new FilmTvseries(film[1], film[2], film[3], film[4], film[5], film[6], film[7], film[8], film[9], film[10], film[11], film[12], film[13]));
                    rated_films_by_filmId.put(film[1], new ArrayList<>());
                    break;
                case ("Documentary:"):
                    filmList.add(new FilmDocumentaries(film[1], film[2], film[3], film[4], film[5], film[6], film[7], film[8]));
                    rated_films_by_filmId.put(film[1], new ArrayList<>());
                    break;
            }
        }

        //execute commands
        for (int i =0; i < commands_txt.length; i++) {
            String[] command_line = commands_txt[i].split("\t");
            FileOutput.writeToFile(output_path, String.format("%s\n\n", commands_txt[i]),true, false);
            switch (command_line[0]) {
                case "RATE":
                    String user_id = command_line[1];
                    boolean check_id_user = true;
                    boolean check_rated_before = false;

                    for (Person user : peopleList) {
                        if (user.id.equals(user_id) && (user.getClass().getName().equals("User"))) {
                            check_id_user = false;
                        }
                    }
                    if (rated_films_by_user.containsKey(user_id)) {
                        if (rated_films_by_user.get(user_id).contains(command_line[2])) { 
                            check_rated_before = true;
                        }
                    }
                    

                    if (check_id_user) {
                        FileOutput.writeToFile(output_path, String.format("Command failed\nUser ID: %s\nFilm ID: %s\n\n-----------------------------------------------------------------------------------------------------\n", user_id, command_line[2]),true, false);
                    }
                    else if (check_rated_before) {
                        FileOutput.writeToFile(output_path, "This film was earlier rated\n\n-----------------------------------------------------------------------------------------------------\n",true, false);
                    }
                    else {
                        for (Person person : peopleList) {
                            if (person instanceof User) {
                                if (person.getId().equals(command_line[1])) {
                                    ((User)person).getRates().put(command_line[2], command_line[3]);
                                    if (!(rated_films_by_user.containsKey(user_id))) {
                                        rated_films_by_user.put(user_id, new ArrayList<>());
                                    }
                                    if (!(rated_films_by_filmId.containsKey(command_line[2]))) {
                                        rated_films_by_filmId.put(command_line[2], new ArrayList<>());
                                    }
                                    rated_films_by_user.get(user_id).add(command_line[2]);
                                    rated_films_by_filmId.get(command_line[2]).add(Integer.parseInt(command_line[3]));
                                }
                            }
                        }
                        String film_type = null;
                        String flim_title = null;
                        for (Films film : filmList) {
                            if (film.id.equals(command_line[2])) {
                                film_type = film.getClass().getName();
                                flim_title = film.title;
                            }
                        }
                        FileOutput.writeToFile(output_path, String.format("Film rated successfully\nFilm Type: %s\nFilm title: %s\n\n-----------------------------------------------------------------------------------------------------\n", film_type, flim_title),true, false);
                    }
                    
                    break;
                case "ADD":
                    String film_id = command_line[2];
                    String film_director = command_line[5];
                    boolean check_id = true;
                    boolean check_director = true;

                    for (Films film : filmList) {
                        if (film.id.equals(film_id)) {
                            check_id = false;
                        }
                        if (film.directors.equals(film_director)) {
                            check_director = false;
                        }
                    }

                    if (check_director && check_id) {
                        filmList.add(new FilmFeature(command_line[2], command_line[3], command_line[4], command_line[5], command_line[6], command_line[7], command_line[8], command_line[9], command_line[10], command_line[11], command_line[12]));
                        rated_films_by_filmId.put(command_line[2], new ArrayList<>());
                        FileOutput.writeToFile(output_path, String.format("FeatureFilm added successfully\nFilm ID: %s\nFilm title: %s\n\n-----------------------------------------------------------------------------------------------------\n", film_id, command_line[3]),true, false);
                    }
                    else {
                        FileOutput.writeToFile(output_path, String.format("Command Failed\nFilm ID: %s\nFilm title: %s\n\n-----------------------------------------------------------------------------------------------------\n", film_id, command_line[3]),true, false);
                    }
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
                                FileOutput.writeToFile(output_path, String.format("%s\t(%s)\n%s\nWriters: %s\nDirectors: %s\nStars: %s\n%s\n\n-----------------------------------------------------------------------------------------------------\n",theFilm.title, theFilm.getReleaseDate().substring(6, 10), theFilm.getGenre(), tem_writers, tem_directors, tem_stars, rating),true, false);
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
                                FileOutput.writeToFile(output_path, String.format("%s\t(%s)\n\nDirectors: %s\nStars: %s\n%s\n\n-----------------------------------------------------------------------------------------------------\n", theFilm.title, theFilm.getReleaseDate().substring(6, 10), tem_directors, tem_stars, rating),true, false);
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
                                FileOutput.writeToFile(output_path, String.format("%s\t(%s-%s)\n%s seasons, %s episodes\n%s\nWriters: %s\nDirectors: %s\nStars: %s\n%s\n\n-----------------------------------------------------------------------------------------------------\n", theFilm.title, theFilm.getStartDate().substring(6, 10), theFilm.getEndDate().substring(6, 10), theFilm.getSeasonNumber(), theFilm.getEpisodeNumber(), theFilm.getGenre().replace(",", ", "), tem_writers, tem_directors, tem_stars, rating),true, false);
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
                            if (user.getRates().containsKey(rate_to_be_removed)) {
                                for (Films film : filmList) {
                                    if (film.id.equals(rate_to_be_removed)) {
                                        FileOutput.writeToFile(output_path, "Your film rating was removed successfully\nFilm title: " + film.title + "\n\n-----------------------------------------------------------------------------------------------------\n", true, false);
                                    }
                                }
                                
                                Integer rate_number = Integer.parseInt(user.getRates().get(rate_to_be_removed));
                                user.getRates().remove(rate_to_be_removed);
                                rated_films_by_user.remove(user.id);
                                rated_films_by_filmId.get(rate_to_be_removed).remove(rate_number);
                            }
                            else {
                                FileOutput.writeToFile(output_path, String.format("Command Failed\nUser ID: %s\nFilm ID: %s", command_line[2], command_line[3]) + "\n\n-----------------------------------------------------------------------------------------------------\n", true, false);
                            }
                        }
                    }
                    break;
                case "EDIT":
                    String user_id2 = command_line[2];
                    boolean check_id_user2 = true;
                    boolean check_user_has_rating = false;

                    for (Person user : peopleList) {
                        if (user.id.equals(user_id2) && (user.getClass().getName().equals("User"))) {
                            check_id_user2 = false;
                            if (((User)user).getRates().containsKey(command_line[3])) {
                                check_user_has_rating = true;
                            }
                        }
                    }

                    if ((check_id_user2) || (!(check_user_has_rating))) {
                        FileOutput.writeToFile(output_path, String.format("Command failed\nUser ID: %s\nFilm ID: %s\n\n-----------------------------------------------------------------------------------------------------\n", user_id2, command_line[3]),true, false);
                    }
                    else {
                        for (Person person : peopleList) {
                            if (person instanceof User) {
                                if (person.getId().equals(command_line[2])) {
                                    ((User)person).getRates().put(command_line[3], command_line[4]);
                                }
                            }
                        }
                        String film_title = null;
                        for (Films film : filmList) {
                            if (film.id.equals(command_line[3])) {
                                film_title = film.title;
                            }
                        }
                        FileOutput.writeToFile(output_path, String.format("New ratings done successfully\nFilm title: %s\nYour rating: %s\n\n-----------------------------------------------------------------------------------------------------\n", film_title, command_line[4]),true, false);
                    }
                    break;
                
                case "LIST":
                    StringBuilder command_line_without = new StringBuilder();
                    for (int j = 0; j<command_line.length-1;j++) {
                        command_line_without.append(command_line[j] + " ");
                    }
                    command_line_without.deleteCharAt(command_line_without.length()-1);

                    //which command of "LIST"

                    //LIST USER
                    if (command_line_without.substring(0,9).equals("LIST USER")) {
                        String userId = command_line[2];
                        StringBuilder ratings = new StringBuilder();
                        User user = null;
                        for (Person s_user : peopleList) {
                            if (s_user.getClass().getName().equals("User")) {
                                if (s_user.id.equals(userId)) {
                                    user = (User) s_user; 
                                }
                            }
                        }
                        if (user == null) {
                            ratings.append("Command Failed\nUser ID: " + command_line[2] + "\n");
                        }
                        else if (user.getRates().isEmpty()) {
                            ratings.append("There is not any ratings so far\n");
                        }
                        else {
                            for (String filmid : user.getRates().keySet()) {
                                for (Films film : filmList) {
                                    if (film.id.equals(filmid)) {
                                        ratings.append(film.title + ": " + user.getRates().get(film.id) +"\n");
                                    }
                                }
                            }
                        }
                        
                        FileOutput.writeToFile(output_path, String.format("%s\n", ratings), true, false);
                    }
                    
                    //LIST FILMS BY COUNTRY
                    else if (String.valueOf(command_line_without).equals("LIST FILMS BY COUNTRY")) {
                        String country = command_line[4];
                        StringBuilder result = new StringBuilder();

                        for (Films film : filmList) {
                            if (film.country.equals(country)) {
                                result.append(String.format("Film title: %s\n%s min\nLanguage: %s\n\n", film.title, film.runtime, film.language));
                            }
                        }

                        if (result.isEmpty()) {
                            result.append("No result\n\n");
                        }

                        FileOutput.writeToFile(output_path, String.valueOf(result), true, false);
                    }
                    
                    //LIST FILM SERIES
                    else if (command_line_without.substring(0,9).equals("LIST FILM") && command_line_without.length() == 9) {
                        boolean check_tvseries = true;
                        ArrayList<FilmTvseries> tvseries = new ArrayList<>();
                        for (Films film : filmList) {
                            if (film.getClass().getName().equals("FilmTvseries")) {
                                check_tvseries = false;
                                tvseries.add((FilmTvseries) film);
                            }
                        }

                        StringBuilder result = new StringBuilder();
                        if (check_tvseries) {
                            result.append("No result\n");
                        }
                        else {
                            for (FilmTvseries film : tvseries) {
                                result.append(String.format("%s (%s-%s)\n%s seasons and %s episodes\n\n", film.title, film.getStartDate().substring(6, 10), film.getEndDate().substring(6, 10), film.getSeasonNumber(), film.getEpisodeNumber()));
                            }
                        }

                        FileOutput.writeToFile(output_path, String.valueOf(result), true, false);
                    }

                    //LIST FILMS BY RATE DEGREE
                    else if (String.valueOf(command_line_without).equals("LIST FILMS BY RATE")) {
                        String[] film_types = {"FilmFeature", "FilmShort", "FilmDocumentaries", "FilmTvseries"};
                        ArrayList<HashMap<String, String>> ratings = new ArrayList<>();
                        ratings.add(new HashMap<>());
                        ratings.add(new HashMap<>());
                        ratings.add(new HashMap<>());
                        ratings.add(new HashMap<>());

                        for (int j = 0; j<4; j++) {
                            String film_type = film_types[j];
                            double total_rates;
                            double total_rating_number = 0;
                            for (String filmId : rated_films_by_filmId.keySet()) {
                                for (Films film : filmList) {
                                    if (film.id.equals(filmId) && film.getClass().getName().equals(film_type)) {
                                        total_rating_number = rated_films_by_filmId.get(filmId).size();
                                        total_rates = 0;
                                        for (Integer num : rated_films_by_filmId.get(filmId)) {
                                            total_rates += num;
                                        }
                                        if (total_rating_number == 0) {
                                            ratings.get(j).put(filmId, "0");
                                        }
                                        else {
                                            ratings.get(j).put(filmId, String.format("%.1f", total_rates/total_rating_number));
                                        }
                                    }
                                }
                                
                            }
                        }

                        for (int j = 0; j<4; j++) {
                            StringBuilder result = new StringBuilder();
                            // HashMap'in değerlerini bir listeye dönüştür
                            List<Map.Entry<String, String>> entryList = new ArrayList<>(ratings.get(j).entrySet());

                            // String değerleri Double'a dönüştür ve sıralama yap
                            entryList.sort((e1, e2) -> {
                                // ',' yerine '.' kullanarak String'i Double'a dönüştür
                                double d1 = Double.parseDouble(e1.getValue().replace(',', '.'));
                                double d2 = Double.parseDouble(e2.getValue().replace(',', '.'));
                                return Double.compare(d2, d1); // Küçükten büyüğe sıralama
                            });

                            // Sıralanmış listeyi yeni bir LinkedHashMap'e dönüştür
                            Map<String, String> sortedMap = new LinkedHashMap<>();
                            for (Map.Entry<String, String> entry : entryList) {
                                sortedMap.put(entry.getKey(), entry.getValue());
                            }

                            if (j != 0) {
                                result.append("\n");
                            }
                            result.append(film_types[j] + ":\n");
                            
                            boolean added = true;
                            for (String film_id2 : sortedMap.keySet()) {
                                for (Films film : filmList) {
                                    if (film.id.equals(film_id2)) {
                                        if (j == 3) {
                                            result.append(String.format("%s (%s-%s) Ratings: %s/10 from %s users\n", film.title, ((FilmTvseries)film).getStartDate().substring(6, 10), ((FilmTvseries)film).getEndDate().substring(6, 10), sortedMap.get(film.id), rated_films_by_filmId.get(film.id).size()));
                                        }
                                        else {
                                            result.append(String.format("%s (%s) Ratings: %s/10 from %s users\n", film.title, film.getReleaseDate().substring(6, 10), sortedMap.get(film.id), rated_films_by_filmId.get(film.id).size()));
                                        }
                                        added = false;
                                    }
                                }
                            }
                            if (added) {
                                FileOutput.writeToFile(output_path, String.valueOf(result) + "No result", true, false);
                            }
                            else {
                                FileOutput.writeToFile(output_path, String.valueOf(result), true, false);
                            }
                        }
                        FileOutput.writeToFile(output_path, "\n", true, false);
                    }

                    //LIST ARTISTS FROM
                    else if (String.valueOf(command_line_without).equals("LIST ARTISTS FROM")) {
                        String[] artists = {"ArtistDirector", "ArtistWriter", "ArtistPerformerActor", "ArtistPerformerChildActor", "ArtistPerformerStuntPerformer"};
                        String country = command_line[3];

                        for (int j=0; j<5; j++) {
                            StringBuilder result = new StringBuilder();
                            
                            boolean added = true;
                            result.append(artists[j] + ":\n");
                            for (Person person : peopleList) {
                                if (person.getClass().getName().equals(artists[j]) && person.country.equals(country)) {
                                    if (artists[j].equals("ArtistDirector")) {
                                        result.append(String.format("%s %s %s\n", person.name, person.surname, ((ArtistDirector) person).getAgent()));
                                    }
                                    if (artists[j].equals("ArtistWriter")) {
                                        result.append(String.format("%s %s %s\n", person.name, person.surname, ((ArtistWriter) person).getWritingStyle()));
                                    }
                                    if (artists[j].equals("ArtistPerformerActor")) {
                                        result.append(String.format("%s %s %s cm\n", person.name, person.surname, ((ArtistPerformerActor) person).getHeight()));
                                    }
                                    if (artists[j].equals("ArtistPerformerChildActor")) {
                                        result.append(String.format("%s %s %s\n", person.name, person.surname, ((ArtistPerformerChildActor) person).getAge()));
                                    }
                                    if (artists[j].equals("ArtistPerformerStuntPerformer")) {
                                        result.append(String.format("%s %s %s cm\n", person.name, person.surname, ((ArtistPerformerStuntPerformer) person).getHeight()));
                                    }
                                    added = false;
                                }
                            }
                            if (added) {
                                FileOutput.writeToFile(output_path, String.valueOf(result) + "No result\n\n", true, false);
                            }
                            else {
                                FileOutput.writeToFile(output_path, String.valueOf(result) + "\n", true, false);
                            }
                        }
                    }

                    //LIST FEATUREFILMS AFTER - BEFORE
                    else if (String.valueOf(command_line_without).split(" ")[1].equals("FEATUREFILMS")) {
                        int date = Integer.parseInt(command_line[3]);
                        StringBuilder result = new StringBuilder();
                        boolean added = true;
                        for (Films film : filmList) {
                            if (film.getClass().getName().equals("FilmFeature")) {
                                if ((String.valueOf(command_line_without).split(" ")[2].equals("AFTER") && Integer.parseInt(film.getReleaseDate().substring(6)) >= date) || (String.valueOf(command_line_without).split(" ")[2].equals("BEFORE") && Integer.parseInt(film.getReleaseDate().substring(6)) < date)) {
                                    result.append(String.format("Film title: %s (%s)\n%s min\nLanguage: %s\n\n", film.title, film.getReleaseDate().substring(6), film.runtime, film.language));
                                    added = false;
                                }
                            }
                        }
                        if (added) {
                            FileOutput.writeToFile(output_path, "No result\n\n", true, false);
                        }
                        else {
                            FileOutput.writeToFile(output_path, String.valueOf(result), true, false);
                        }
                    }
                    FileOutput.writeToFile(output_path, "-----------------------------------------------------------------------------------------------------\n", true, false);
                }
            }
        }
    }