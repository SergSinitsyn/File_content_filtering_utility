import com.beust.jcommander.JCommander;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Program {
    private static Arguments arguments = new Arguments();
    private static NumberStatistic integerNumberStatistic;
    private static NumberStatistic realNumberStatistic;
    private static StringsStatistic stringsStatistic;


    public static void main(String[] args) {
        JCommander.newBuilder().addObject(arguments).build().parse(args);

        if (arguments.getFiles().size() < 1) {
            throw new RuntimeException("Incorrect number of files");
        }

        if (arguments.isFullStatistics()) {
            integerNumberStatistic = new NumberStatistic(StatisticType.FULL);
            realNumberStatistic = new NumberStatistic(StatisticType.FULL);
            stringsStatistic = new StringsStatistic(StatisticType.FULL);
        } else {
            integerNumberStatistic = new NumberStatistic(StatisticType.SHORT);
            realNumberStatistic = new NumberStatistic(StatisticType.SHORT);
            stringsStatistic = new StringsStatistic(StatisticType.SHORT);
        }

        for (String path : arguments.getFiles()) {
            readFile(path);
        }

        System.out.println("Integer numbers statistics:" + System.lineSeparator()
                + integerNumberStatistic.toString());
        System.out.println("Real numbers statistics:" + System.lineSeparator()
                + realNumberStatistic.toString());
        System.out.println("Strings statistics:" + System.lineSeparator()
                + stringsStatistic.toString());

    }

    public static void readFile(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                identifyDataType(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void identifyDataType(String str) {
        if (str.matches("-?\\d+")) {
            integerNumberStatistic.add(str);
            //
        } else if (str.matches("-?\\d+([.]\\d+)?((E|e)([-+])?\\d+)?")) {
            realNumberStatistic.add(str);
            //
        } else {
            // TODO separate string?
            stringsStatistic.add(str);
        }
    }

}
