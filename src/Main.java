// https://youtu.be/vaQcT0E7WXQ & https://youtu.be/K9pXcomi-WY for help on importing the Jackson files to the project.
// you need the Jackson files to use 'ObjectMapper' and 'JsonProcessingException'.
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//V/ These aren't nessicary as I just used them to write the 'footballPlayer' Json data to 'outfile.json'.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //V/ This is the key function for turning Java objects into Json Formatted data.
        ObjectMapper mapper = new ObjectMapper();
        FootballPlayer footballPlayer = new FootballPlayer("Joe", 10);
        String jsonString = null;
        try {

            //V/ This is how Jackson turns objects, (in this case 'footballPlayer') into Serialized data aka, Json Formatting.
            jsonString = mapper.writeValueAsString(footballPlayer);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println(footballPlayer.toString());
        System.out.println(jsonString);
        System.out.println("This is what Json Formatted data looks like for the object 'footballPlayer'.");

        //V/ I broke down the old FileWriter Class that we did a while ago here: https://github.com/PhilipMatthewD/Java-File-IO-Persistance-PoC.git and put the 'footballPlayer' data there.
        File file = new File("outfile.json");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(mapper.writeValueAsString(footballPlayer));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Technically the video did this differently, but let's say Joe also plays Basketball and Baseball too, but we get the data redundantly from the 'outfile.json' file.

        Scanner myReader = null;
        BasketballPlayer basketballPlayer;
        try {
            myReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
            jsonString = myReader.nextLine();
        try {
            //V/ the '.readValue' has a lot of acceptable data types idk about but for this example it helps me turn 'jsonString' which is just '{"name":"Joe","number":10}' into data the java class can accept.
            basketballPlayer = mapper.readValue(jsonString, BasketballPlayer.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println(basketballPlayer.toString());

        // This is the way the video did it, this is less redundant. all that work above for nothing smh.

        BaseballPlayer baseballPlayer;
        try {
            baseballPlayer = mapper.readValue(new File("outfile.json"), BaseballPlayer.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(baseballPlayer.toString());

        // Let's say Joe's jersey number is actually his placement, we can use JsonAlias to let this work even though number != placement, 10th is low, but I can make it work.

        TennisPlayer tennisPlayer;
        try {
            tennisPlayer = mapper.readValue(new File("outfile.json"), TennisPlayer.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(tennisPlayer.toString());

    }
}

// Jackson doesn't work without public getters or setters or public variables
// I put some 'toString()' methods in each class to show a better demonstration of the classes and their data.

class FootballPlayer {
    public String name;
    public int number;

    public FootballPlayer(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "FootballPlayer { " +
                "name = '" + name + '\'' +
                ", number = " + number +
                " }";
    }
}

class BasketballPlayer {
    public String name;
    public int number;

    // I might be wrong but since the 'BasketballPlayer' class is taking Json data it cannot have a regular constructor as Json data types are different from Java's.

    @Override
    public String toString() {
        return "BasketballPlayer { " +
                "name = '" + name + '\'' +
                ", number = " + number +
                " }";
    }
}

class BaseballPlayer {
    public String name;
    public int number;

    // Same thing with the 'BasketballPlayer' class.

    @Override
    public String toString() {
        return "BaseballPlayer { " +
                "name = '" + name + '\'' +
                ", number = " + number +
                " }";
    }
}

class TennisPlayer{
//V/ Since 'name' is the same for both Json and Java we don't need this but for 'number' and 'placement' we do, there can also more than one 'JsonAlias', as long as its seperated from the first one with a comma it's fine.
    @JsonAlias({ "name"})
    public String name;
    @JsonAlias({ "number"})
    public int placement;

    // You got the picture by now.

    @Override
    public String toString() {
        return "TennisPlayer { " +
                "name = '" + name + '\'' +
                ", placement = " + placement +
                " }";
    }
}
