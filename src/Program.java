import Statistics.IntegerNumbersStatistics;
import Statistics.RealNumbersStatistics;
import Statistics.StatisticsType;
import Statistics.StringsStatistics;
import com.beust.jcommander.JCommander;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Program {
    private static Arguments arguments = new Arguments();
    private static IntegerNumbersStatistics integerNumbersStatistic;
    private static RealNumbersStatistics realNumbersStatistic;
    private static StringsStatistics stringsStatistics;

    public static void main(String[] args) {
        JCommander.newBuilder().addObject(arguments).build().parse(args);

        if (arguments.getFiles().size() < 1) {
            throw new RuntimeException("No input files");
        }

        StatisticsType statisticsType;
        if (arguments.isFullStatistics()) {
            statisticsType = StatisticsType.FULL;
        } else {
            statisticsType = StatisticsType.SHORT;
        }

        integerNumbersStatistic = new IntegerNumbersStatistics(statisticsType);
        realNumbersStatistic = new RealNumbersStatistics(statisticsType);
        stringsStatistics = new StringsStatistics(statisticsType);

        for (String path : arguments.getFiles()) {
            readFile(path);
        }

        String file_name_int = "integers.txt";
        String file_name_real = "floats.txt";
        String file_name_str = "strings.txt";
        if (arguments.getPrefix() != null) {
            file_name_int = arguments.getPrefix() + file_name_int;
            file_name_real = arguments.getPrefix() + file_name_real;
            file_name_str = arguments.getPrefix() + file_name_str;
        }

        if (arguments.getPath() != null) {

            File directory = new File(arguments.getPath());
            if (directory.mkdir()) {
                System.out.println("Directory created successfully.");
            } else {
                System.out.println("Failed to create directory.");
            }

            file_name_int = arguments.getPath() + file_name_int;
            file_name_real = arguments.getPath() + file_name_real;
            file_name_str = arguments.getPath() + file_name_str;
        }

        createFile(file_name_int, integerNumbersStatistic.getData(), arguments.isAddOption());
        createFile(file_name_real, realNumbersStatistic.getData(), arguments.isAddOption());
        createFile(file_name_str, stringsStatistics.getData(), arguments.isAddOption());

        System.out.println("Integer numbers statistics:" + System.lineSeparator()
                + integerNumbersStatistic.toString());
        System.out.println("Real numbers statistics:" + System.lineSeparator()
                + realNumbersStatistic.toString());
        System.out.println("Strings statistics:" + System.lineSeparator()
                + stringsStatistics.toString());

    }

    private static void readFile(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                identifyDataType(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + path);
        } catch (IOException e) {
            System.err.println("Problem with reading file: " + path);
        }
    }

    private static void identifyDataType(String str) {
        if (str.matches("-?\\d+")) {
            integerNumbersStatistic.add(str);
        } else if (str.matches("-?\\d+(.\\d+)?((E|e)([-+])?\\d+)?")) {
            realNumbersStatistic.add(str);
        } else {
            stringsStatistics.add(str);
        }
    }


    private static void createFile(String filePath, List<String> data, boolean addOption) {
        if (data.isEmpty()) {
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(filePath, addOption);
            for (String string : data) {
                fileWriter.write(string + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
