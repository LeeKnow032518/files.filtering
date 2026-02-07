package files.filtering.cli;

import java.util.List;

/**
 * Класс для хранения аргументов командной строки
 */
public class Args {
    /**
     * Путь до выходного файла.
     * <p> Дефолтное значение: ""</p>
     */
    private String path;

    /**
     * Префикс перед названием выходных файлов.
     * <p>Пример:
     *  <pre>{@code
     *      this.prefix = "before"; // "before-integers.txt", "before-floats.txt", "before.strings.txt"
     *  }
     *  </pre>
     * </p>
     * <p> Дефолтное значение: ""</p>
     */
    private String prefix;

    /**
     * Режим добавления данных в существующие файлы.
     * <p>
     *     <ul>
     *         <li>{@code true} - режим включен,</li>
     *         <li>{@code false}(<b>Дефолтное значение</b>) - режим отключен</li>
     *     </ul>
     * </p>
     */
    private boolean add;

    /**
     * Опция вывода краткой статистики.
     * <p>
     *     <b>Содержание: </b> количество элементов записанных в исходящие файлы
     * </p>
     */
    private boolean shortStats;

    /**
     * Опция вывода полной статистики.
     * <p>
     *     <b>Содержание: </b>
     *     <ol>
     *         <li> Количество элементов записанных в исходящие файлы</li>
     *         <li> Для чисел - минимальное и максимальное
     * значения, сумма и среднее</li>
     *         <li> Для строки - размер самой короткой строки и самой длинной</li>
     *     </ol>
     * </p>
     */
    private boolean fullStats;

    /**
     * Список входных файлов.
     */
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
}
