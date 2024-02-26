package org.serg_sinitsyn.file_content_filtering_utility;

import com.beust.jcommander.JCommander;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.serg_sinitsyn.file_content_filtering_utility.statistics.StatisticsType;

public class Program {

    public static void main(String[] args) throws Exception {
        Arguments arguments = parseArguments(args);
        if (!validateArguments(arguments)) {
            return;
        }

        if (arguments.getPath() != null) {
            createDirectory(arguments.getPath());
        }

        FileProcessor fileProcessor = createFileProcessor(arguments);
        processFiles(arguments, fileProcessor);

        fileProcessor.closeFiles();
        fileProcessor.printStatistics();

        if (arguments.getPath() != null) {
            deleteDirectory(arguments.getPath());
        }
    }

    private static Arguments parseArguments(String[] args) {
        Arguments arguments = new Arguments();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);
        return arguments;
    }

    private static boolean validateArguments(Arguments arguments) {
        if (arguments.getFiles() == null || arguments.getFiles().isEmpty()) {
            System.err.println("No input files");
            return false;
        }
        return true;
    }

    private static FileProcessor createFileProcessor(Arguments arguments) {
        StatisticsType statisticsType =
                arguments.isFullStatistics() ? StatisticsType.FULL : StatisticsType.SHORT;
        return new FileProcessor(statisticsType, arguments.isAppendOption(),
                arguments.getPrefix(), arguments.getPath());
    }

    private static void processFiles(Arguments arguments, FileProcessor fileProcessor) throws Exception {
        for (String path : arguments.getFiles()) {
            fileProcessor.processFile(path);
        }
    }

    private static void createDirectory(String path) {
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            System.err.println("Failed to create directory");
        }
    }

    private static void deleteDirectory(String path) {
        try {
            Path directoryPath = Paths.get(path);
            if (Files.isDirectory(directoryPath) &&
                    !Files.newDirectoryStream(directoryPath).iterator().hasNext()) {
                Files.delete(directoryPath);
            }
        } catch (IOException e) {
            System.err.println("Failed to delete empty directory");
        }
    }
}