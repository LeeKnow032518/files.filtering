package files.filtering.service;

import files.filtering.cli.Args;
import files.filtering.format.FloatFormat;
import files.filtering.format.Format;
import files.filtering.format.IntegerFormat;
import files.filtering.format.StringFormat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Класс для парсинга строк из входных файлов.
 * <p>Содержит следующие методы:
 *     <ul>
 *         <li> Основной метод для парсинга входных файлов,</li>
 *         <li> Метод для проверки соответствия строки целому числу,</li>
 *         <li> Метод для проверки соответствия строки числу с плавающей точкой.</li>
 *     </ul>
 * </p>
 */
public class FileParser {

    /**
     * Метод для парсинга входных данных.
     * <p>
     *     Метод открывает поочередно все файлы, написанные в командной строке при запуске приложения,
     *     читает все строки и распределяет их по типам данных: целые с плавающей точкой и строки.
     * </p>
     * @param args Описывает аргументы переданные в командной строке при запуске
     * @return Возвращает списки элементов каждого типа {@link files.filtering.format.Format}
     * @throws IOException при неудачной попытке открыть какой-то из входных файлов
     */
    public static List<Format> parseInputFiles(Args args) throws IOException{
        Format integers = new IntegerFormat("integers");
        Format floats = new FloatFormat("floats");
        Format strings = new StringFormat("strings");

        for(String fileName : args.getInputFiles()) {
            try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (isInteger(line)) {
                        integers.updateElements(line);

                        integers.compareAndSubstitute(line);
                    } else if (isFloat(line)) {
                        floats.updateElements(line);

                        floats.compareAndSubstitute(line);
                    } else {
                        strings.updateElements(line);

                        strings.compareAndSubstitute(line);
                    }
                }
            } catch (IOException e){
                throw new FileNotFoundException("couldn't find file " + fileName);
            }
        }

        return List.of(integers, floats, strings);
    }

    /**
     *  Метод для проверки строки на соответствие целому число с помощью регулярного выражения.
     * @param str строка на проверку
     * @return {@code true} - если строка является целыми числом; {@code false} - иначе.
     */
    private static boolean isInteger(String str) {
        return str != null && str.matches("-?\\d+");
    }

    /**
     *  Метод для проверки строки на соответствие числу с плавающей запятой с помощью попытки парсинга в double.
     * @param str строка на проверку
     * @return {@code true} - если строка является целыми числом с плавающей точкой; {@code false} - иначе.
     */
    private static boolean isFloat(String str){
        if (str == null) return false;
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
