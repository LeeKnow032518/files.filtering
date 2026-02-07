package files.filtering.cli;

import files.filtering.format.Format;
import files.filtering.service.FileParser;
import files.filtering.service.FileWriter;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.List;

public class CommandLineApp {
    public static void parseArgs(String[] args) {
        Options options = createOptions();

        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser clParser = new DefaultParser();

        try{
            CommandLine commandLine = clParser.parse(options, args, false);

            if(commandLine.hasOption("help")){
                formatter.printHelp("files-filtering-app", options);
                return;
            }

            Args arguments = createArgs(commandLine);
            List<Format> formats = FileParser.parseInputFiles(arguments);
            for(Format format : formats){
                if(format != null){
                    FileWriter.writeOutputFile(format, arguments);
                    if(arguments.isFullStats()){
                        format.printFullStatistic();
                    } else if(arguments.isShortStats()){
                        format.printStatistic();
                    }
                }
            }

        }catch (ParseException | IllegalArgumentException | IOException e){
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static Options createOptions(){
        Options options = new Options();
        options.addOption("o", "output", true, "Path to output files");
        options.addOption("p", "prefix", true, "Prefix for output files");

        options.addOption("a", "add", false, "Add results to existing files");
        options.addOption("s", "short", false, "Show short statistic");
        options.addOption("f", "full", false, "Show full statistic");

        options.addOption("h", "help", false, "Show options");
        return options;
    }

    private static Args createArgs(CommandLine commandLine) throws IllegalArgumentException{
        Args arguments = new Args();

        String path = commandLine.getOptionValue("o", "");
        String prefix = commandLine.getOptionValue("p", "");

        boolean add = commandLine.hasOption("a");
        boolean sStat = commandLine.hasOption("s");
        boolean fStat = commandLine.hasOption("f");

        List<String> fileNames = commandLine.getArgList();
        if(fileNames.isEmpty()){
            throw new IllegalArgumentException("Please enter input files names.");
        }

        arguments.setPath(path);
        arguments.setPrefix(prefix);
        arguments.setAdd(add);
        arguments.setShortStats(sStat);
        arguments.setFullStats(fStat);
        arguments.setInputFiles(fileNames);

        return arguments;
    }
}
