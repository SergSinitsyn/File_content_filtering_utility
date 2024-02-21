import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.List;

public class Arguments {
    @Parameter(names = {"-o"}, description = "Path for output files")
    private String path;

    @Parameter(names = {"-p"}, description = "Prefix for output files")
    private String prefix;

    @Parameter(names = {"-a"}, description = "Add option")
    private boolean addOption;

    @Parameter(names = {"-s"}, description = "Short statistics")
    private boolean shortStatistics;

    @Parameter(names = {"-f"}, description = "Full statistics")
    private boolean fullStatistics;

    @Parameter(description = "Input files")
    private List<String> files;


    public String getPath() {
        return path;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isAddOption() {
        return addOption;
    }

    public boolean isShortStatistics() {
        return shortStatistics;
    }

    public boolean isFullStatistics() {
        return fullStatistics;
    }

    public List<String> getFiles() {
        return files;
    }
}
