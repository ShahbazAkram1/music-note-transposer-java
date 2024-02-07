package com.acme.musictransposition;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NoteTransposition {

    public static void main(String[] args) {
        System.out.println("Starting Note Transposition Program...");

        // File paths
        String inputFilePath = "src/main/java/com/acme/musictransposition/json/input.json";
        String outputFilePath = "src/main/java/com/acme/musictransposition/json/output.json";
        int semitones = -3; // Number of semitones to transpose

        try {
            // Read input notes from file
            JSONArray notes = readInputNotes(inputFilePath);

            // Transpose notes
            JSONArray transposedNotes = transposeNotes(notes, semitones);

            // Write transposed notes to file
            if (transposedNotes != null) {
                writeOutputNotes(outputFilePath, transposedNotes);
                System.out.println("Transposition completed. Output written to: " + outputFilePath);
            }
        } catch (NoSuchFileException e) {
            System.err.println("Input file not found: " + inputFilePath);
        } catch (IOException | JSONException e) {
            System.err.println("An error occurred during note transposition: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        System.out.println("Note Transposition Program Finished.");
    }

    // Read input notes from JSON file
    private static JSONArray readInputNotes(String filePath) throws IOException, JSONException {
        Path path = Paths.get(filePath);
        String jsonContent = new String(Files.readAllBytes(path));
        return new JSONArray(jsonContent);
    }

    // Transpose notes by the specified number of semitones
    private static JSONArray transposeNotes(JSONArray notes, int semitones) throws JSONException {
        if (notes == null) {
            System.err.println("Error: Unable to parse input JSON.");
            return null;
        }

        JSONArray transposedNotes = new JSONArray();

        // Iterate through input notes and transpose each note
        for (int i = 0; i < notes.length(); i++) {
            JSONArray note = notes.getJSONArray(i);
            int octave = note.getInt(0);
            int noteNumber = note.getInt(1);

            noteNumber += semitones;

            // Adjust octave and note number if out of range
            while (noteNumber < 1) {
                noteNumber += 12;
                octave--;
            }
            while (noteNumber > 12) {
                noteNumber -= 12;
                octave++;
            }

            // Check if transposed note falls within the valid keyboard range
            if (octave < -3 || octave > 5) {
                System.err.println("Error: Transposed note falls out of keyboard range.");
                return null;
            }

            // Add transposed note to the output array
            transposedNotes.put(new JSONArray().put(octave).put(noteNumber));
        }

        return transposedNotes;
    }

    // Write transposed notes to JSON file
    private static void writeOutputNotes(String filePath, JSONArray transposedNotes) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            String formattedJson = transposedNotes.toString(4); // Indent with 4 spaces
            fileWriter.write(formattedJson);
        }
    }

}
