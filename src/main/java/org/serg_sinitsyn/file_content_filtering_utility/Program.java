package org.serg_sinitsyn.file_content_filtering_utility;

import com.beust.jcommander.JCommander;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.serg_sinitsyn.file_content_filtering_utility.statistics.StatisticsType;


public class Program {
    private static final Pattern PREFIX_PATTERN = Pattern.compile("^[^\\\\/:*?\"<>|]+$");

    public static void main(String[] args) {
        Arguments arguments = parseArguments(args);
        if (!validateArguments(arguments)) {
            return;
        }

        if (arguments.getPath() != null) {
            createDirectory(arguments.getPath());
        }

        FileProcessor fileProcessor = new FileProcessor(
                arguments.isFullStatistics() ? StatisticsType.FULL : StatisticsType.SHORT,
                arguments.isAppendOption(),
                arguments.getPrefix(),
                arguments.getPath());

        try {
            for (String path : arguments.getFiles()) {
                fileProcessor.processFile(path);
            }

            fileProcessor.closeFiles();
            fileProcessor.printStatistics();
        } catch (Exception e) {
            System.err.println("An error occurred while processing files: " + e.getMessage());
            e.printStackTrace();
        }


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

        if (!PREFIX_PATTERN.matcher(arguments.getPrefix()).matches()) {
            System.err.println("Incorrect prefix");
            return false;
        }
        return true;
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