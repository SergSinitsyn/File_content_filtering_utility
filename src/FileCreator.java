import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {

    private String filename;
    private boolean appendOption;
    private boolean isWritingStarted = false;

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

    public void addData(String string) {
        if (!isWritingStarted) {
            isWritingStarted = true;
            createFile();
        }
        try (FileWriter fileWriter = new FileWriter(filename, true)) {
            fileWriter.write(string + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Failed to write to the file: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Null pointer reference: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private void createFile() {
        try (FileWriter fileWriter = new FileWriter(filename, appendOption)) {

        } catch (IOException e) {
            System.err.println("Failed to write to the file: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Null pointer reference: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }


}