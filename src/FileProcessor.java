import statistics.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FileProcessor {
    private static final Pattern INTEGER_PATTERN =
            Pattern.compile("-?\\d+");
    private static final Pattern REAL_NUMBER_PATTERN =
            Pattern.compile("-?\\d+(.\\d+)?([Ee][-+]?\\d+)?");
    private Map<DataType, FileCreator> fileCreators = new HashMap<>();
    private Map<DataType, Statistics> statistics = new HashMap<>();

    FileProcessor(StatisticsType statisticsType, boolean appendOption, String prefix, String path) {
        statistics.put(DataType.INTEGER_NUMBER, new IntegerNumbersStatistics(statisticsType));
        statistics.put(DataType.REAL_NUMBER, new RealNumbersStatistics(statisticsType));
        statistics.put(DataType.STRING, new StringsStatistics(statisticsType));
        for (DataType dataType : statistics.keySet()) {
            fileCreators.put(dataType, new FileCreator(dataType.toString() + ".txt",
                    prefix, path, appendOption));
        }
    }


    public void processFile(String path) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                DataType dataType = identifyDataType(line);
                statistics.get(dataType).addData(line);
                fileCreators.get(dataType).addData(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + path);
        } catch (IOException e) {
            System.err.println("Problem with reading file: " + path);
        }
    }

    public void printStatistics() {
        for (Statistics statistics : this.statistics.values()) {
            System.out.println(statistics.toString());
        }
    }

    public void closeFiles() {
        for (FileCreator fileCreator : fileCreators.values()) {
            fileCreator.closeFile();
        }
    }

    private DataType identifyDataType(String string) {
        if (INTEGER_PATTERN.matcher(string).matches()) {
            return DataType.INTEGER_NUMBER;
        } else if (REAL_NUMBER_PATTERN.matcher(string).matches()) {
            return DataType.REAL_NUMBER;
        } else {
            return DataType.STRING;
        }
    }

}
