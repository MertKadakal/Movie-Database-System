In this experiment, you are supposed to develop a simple Movie Database System similar
to IMDB. You are responsible for using inheritance mechanism and access modifiers in Java
programming language. The system will process several data input files and will generate
results of commands which will be read from a command input file. All input files will be
error free only syntactically. The requirements and rules for the system are given below:

    • The system includes information about people and films.
    
    • Each Person has name, surname, country and a unique id. A person in the system could
    be either Artist or User.

    • Each User has a unique id, name, surname and country.
    
    • There are three kinds of Artist: Performer, Director and Writer. Each Director has a
    unique id, name, surname, country and agent where he/she works. Each Writer has a
    unique id, name, surname, country and writing style/type.
    
    • There are also three types of Performers which are Actor, ChildActor and StuntPerformer. Each Actor has a unique id, name, surname, country and height. Each ChildActor has a unique id, name, surname and country. Each StuntPerformer has a unique id,
    name, surname and country and height.
    
    • There are four types of films in the system: Feature Film, Short Film, Documentary
    and TV Series. Each film (Feature Film, Short Film, Documentary and TV Series) has
    a rating score which calculated from users’ average rating scores for that film.
    
    • A unique film id, film title, language, runtime, country, directors of a film and cast
    (actors and actresses of a movie) are common in all film types.

    • Feature Films have a release date, budget, writers of movie and film genre in addition
    to the common data.
    
    • Documentaries have only a release date in addition to the common film data.
    
    • TV Series have start date and end date of series, number of seasons, number of episodes,
    genre of series and writers in addition to the common film data.
    
    • A film may have more than one directors, writers, performers and genres in this system.
    A comma will be used to separate these data.
    
    • A Short Film has a release date, writers and genre in addition to the common data. A
    Short Film’ runtime should be less ( or equal) than 40 min.

Here's the UML class diagram of the project:

![uml_diagram](https://github.com/MertKadakal/Movie-Database-System/blob/master/UML.png)
