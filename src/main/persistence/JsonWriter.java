package persistence;

import model.User;
import org.json.JSONObject;


import java.io.*;

// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of a user to file
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void write(User user) {
        JSONObject json = user.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void close() {
        writer.close();
    }


    // MODIFIES: this
    // EFFECTS: writes string to file
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void saveToFile(String json) {
        writer.print(json);
    }
}
