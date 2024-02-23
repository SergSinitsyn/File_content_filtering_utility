import statistics.*;
import com.beust.jcommander.JCommander;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
            try {
                Files.createDirectories(Paths.get(arguments.getPath()));
            } catch (IOException e) {
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
        System.out.println("Float numbers statistics:" + System.lineSeparator()
                + realNumbersStatistic.toString());
        System.out.println("Strings statistics:" + System.lineSeparator()
                + stringsStatistics.toString());
        System.out.close();
    }

    private static void readFile(String path) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
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
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + path);
        } catch (IOException e) {
            System.err.println("Problem with reading file: " + path);
        }
    }

    private static DataType identifyDataType(String string) {
        if (string.matches("-?\\d+")) {
            return DataType.INTEGER_NUMBER;
        } else if (string.matches("-?\\d+(.\\d+)?(([Ee])([-+])?\\d+)?")) {
            return DataType.REAL_NUMBER;
        } else {
            return DataType.STRING;
        }
    }

}
