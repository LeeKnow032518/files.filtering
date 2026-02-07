package files.filtering.cli;

import java.util.List;

public class Args {
    private String path;
    private String prefix;
    private boolean add;
    private boolean shortStats;
    private boolean fullStats;

    private List<String> inputFiles;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isShortStats() {
        return shortStats;
    }

    public void setShortStats(boolean shortStats) {
        this.shortStats = shortStats;
    }

    public boolean isFullStats() {
        return fullStats;
    }

    public void setFullStats(boolean fullStats) {
        this.fullStats = fullStats;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }

    public void setInputFiles(List<String> inputFiles) {
        this.inputFiles = inputFiles;
    }

    @Override
    public String toString() {
        return "Args{" +
            "path='" + path + '\'' +
            ", prefix='" + prefix + '\'' +
            ", add=" + add +
            ", shortStats=" + shortStats +
            ", fullStats=" + fullStats +
            ", inputFiles=" + inputFiles +
            '}';
    }
}
