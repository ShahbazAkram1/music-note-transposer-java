## Note Transposition

The Note Transposition program is a console application written in Java that transposes a collection of musical notes up or down by a specified number of semitones. It reads musical notes from an input JSON file, applies transposition, and outputs the transposed notes to a new JSON file.

### Usage

1. **Input File**: Place your input JSON file containing the collection of notes in the specified format.

2. **Compile the Program**: Navigate to the root directory of the project and compile the Java source files using the following command:

    ```bash
    javac com/acme/musictransposition/*.java
    ```

3. **Run the Program**: Execute the `NoteTransposition` class with the following command:

    ```bash
    java com.acme.musictransposition.NoteTransposition
    ```

4. **Check the Output File**: The transposed collection of notes will be written to the specified output JSON file located in the `json` folder within the project directory.
