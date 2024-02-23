import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {

    private String filename;
    private boolean appendOption;
    private boolean isStartWriting = false;

    FileCreator(String name, String prefix, String path, boolean appendOption) {
        this.appendOption = appendOption;

        filename = name;
        if (prefix != null) {
            filename = prefix + name;
        }
        if (path != null) {
            filename = path + "/" + filename;
        }
    }

    public void addData(String data) {
        if (!isStartWriting) {
            createFile();
            isStartWriting = true;
        }
        try (FileWriter fileWriter = new FileWriter(filename, true)) {
            fileWriter.write(data + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Failed to write to the file: " + e.getMessage());
        }
    }

    private void createFile() {
        try (FileWriter fileWriter = new FileWriter(filename, appendOption)) {

        } catch (IOException e) {
            System.err.println("Failed to write to the file: " + e.getMessage());
        }
    }


}
