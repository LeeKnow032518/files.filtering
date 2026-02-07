package files.filtering.cli;

import files.filtering.format.Format;
import files.filtering.service.FileParser;
import files.filtering.service.FileWriter;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.List;

/**
 * Основной класс для обработки аргументов командной строки.
 * <p> Предоставляет следующие методы:
 * <ul>
 *  <li> Обработка аргументов командной строки </li>
 *  <li> Добавление возможных опций для входных аргументов</li>
 *  <li> Парсинг набора строк в специальный класс</li>
 * </p>
 * </ul>
 */
public class CommandLineApp {
    /**
     * Метод для парсинга аргументов командной строки.
     * <p>
     *     Получает на вход массив строк <code>String[] args</code>,
     *     парсит аргументы и перезаписывает их в экземпляр класса {@link files.filtering.cli.Args}
     *     <ul>
     *         <li> При наличии опции <code>help</code> выводит все возможные опции в командную строку</li>
     *     </ul>
     * </p>
     * @param args Аргументы командной строки
     */
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
                if(!format.getElements().isEmpty()){
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

    /**
     * Метод для добавления возможных опций.
     *
     * <ul>
     *     <li> <b>-o (--output)</b> - Задает путь до выходных файлов,</li>
     *     <li> <b>-p (--prefix)</b> - Задаёт префикс, который необходимо приписать ко всем выходным файлам,</li>
     *     <li> <b>-a (--add)</b> - Включает режим добавления результатов в уже существующий файл,</li>
     *     <li> <b>-s (--short)</b> - Опция для вывода краткой статистики по обработанной информации,</li>
     *     <li> <b>-f (--full)</b> - Опция для вывода полной статистики по обработанной информации,</li>
     *     <li> <b>-h (--help)</b> - Опция для вывода всех доступных опций.</li>
     * </ul>
     * @return Возвращает экземпляр класса {@link org.apache.commons.cli.Options}
     */
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

    /**
     * Метод, который обрабатывает аргументы и маппит их на класс {@link files.filtering.cli.Args}.
     *
     * @param commandLine аргументы после парсинга с помощью {@link org.apache.commons.cli.CommandLineParser}
     * @return Возвращает элемент класса {@link files.filtering.cli.Args}
     * @throws IllegalArgumentException если в списке аргументов нет названий входных файлов
     */
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
