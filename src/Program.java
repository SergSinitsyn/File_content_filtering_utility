import statistics.IntegerNumbersStatistics;
import statistics.RealNumbersStatistics;
import statistics.StatisticsType;
import statistics.StringsStatistics;
import com.beust.jcommander.JCommander;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Program {
    private static IntegerNumbersStatistics integerNumbersStatistic;
    private static RealNumbersStatistics realNumbersStatistic;
    private static StringsStatistics stringsStatistics;


    private static FileCreator integersOutputFile;
    private static FileCreator floatsOutputFile;
    private static FileCreator stringsOutputFile;


    public static void main(String[] args) {
        Arguments arguments = new Arguments();
        JCommander.newBuilder().addObject(arguments).build().parse(args);

        if (arguments.getFiles().isEmpty()) {
            throw new RuntimeException("No input files");
        }

        // don't create directory if all files are empty
        if (arguments.getPath() != null) {
            File directory = new File(arguments.getPath());
            if (directory.mkdirs()) {
                System.out.println("Directory created successfully.");
            } else {
                System.err.println("Failed to create directory.");
            }
        }

        StatisticsType statisticsType = arguments.isFullStatistics()
                ? StatisticsType.FULL : StatisticsType.SHORT;
        integerNumbersStatistic = new IntegerNumbersStatistics(statisticsType);
        realNumbersStatistic = new RealNumbersStatistics(statisticsType);
        stringsStatistics = new StringsStatistics(statisticsType);

        integersOutputFile = new FileCreator("integers.txt",
                arguments.getPrefix(), arguments.getPath(), arguments.isAppendOption());
        floatsOutputFile = new FileCreator("floats.txt",
                arguments.getPrefix(), arguments.getPath(), arguments.isAppendOption());
        stringsOutputFile = new FileCreator("strings.txt",
                arguments.getPrefix(), arguments.getPath(), arguments.isAppendOption());

        for (String path : arguments.getFiles()) {
            readFile(path);
        }

        printStatistics();
    }

    private static void printStatistics() {
        System.out.println("Integer numbers statistics:" + System.lineSeparator()
                + integerNumbersStatistic.toString());
        System.out.println("Real numbers statistics:" + System.lineSeparator()
                + realNumbersStatistic.toString());
        System.out.println("Strings statistics:" + System.lineSeparator()
                + stringsStatistics.toString());
    }

    private static void readFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                switch (identifyDataType(line)) {
                    case INTEGER_NUMBER:
                        integerNumbersStatistic.addData(line);
                        integersOutputFile.addData(line);
                        break;
                    case REAL_NUMBER:
                        realNumbersStatistic.addData(line);
                        floatsOutputFile.addData(line);
                        break;
                    case STRING:
                        stringsStatistics.addData(line);
                        stringsOutputFile.addData(line);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + path);
        } catch (IOException e) {
            System.err.println("Problem with reading file: " + path);
        }
    }

    private static DataType identifyDataType(String str) {
        if (str.matches("-?\\d+")) {
            return DataType.INTEGER_NUMBER;
        } else if (str.matches("-?\\d+(.\\d+)?(([Ee])([-+])?\\d+)?")) {
            return DataType.REAL_NUMBER;
        } else {
            return DataType.STRING;
        }
    }


    private static void createFile(String filePath, List<String> data, boolean appendOption) {
        if (data.isEmpty()) {
            return;
        }
        try (FileWriter fileWriter = new FileWriter(filePath, appendOption)) {
            for (String string : data) {
                fileWriter.write(string + System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Failed to write to the file: " + e.getMessage());
        }
    }
}
