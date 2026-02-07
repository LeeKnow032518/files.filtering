package files.filtering.format;

/**
 * Класс, расширяющий класс {@link files.filtering.format.Format}
 * <p> Работает со строками.</p>
 * <p> Содержит следующие методы:
 *  <ul>
 *      <li> Перегрузка метода {@link files.filtering.format.Format#compareAndSubstitute(String)}</li>
 *      <li> Перегрузка метода {@link Format#printFullStatistic()}</li>
 *  </ul>
 * </p>
 */
public class StringFormat extends Format{

    /**
     * Длина минимальной строки.
     */
    int min;

    /**
     * Длина максимальной строки.
     */
    int max;

    /**
     * Конструктор
     * @param type информация о типе элементов, которые будут храниться
     */
    public StringFormat(String type) {
        super(type);
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
    }

    /**
     * Метод сравнивает длину нового элемента с минимальным и максимальным и заменяет на него,
     * если она меньше или больше имеющихся соответственно.
     * @param line элемент, с длиной которого будет сравниваться информация.
     */
    public void compareAndSubstitute(String line){
        if(line.length() < this.min){
            min = line.length();
        }

        if(line.length() > this.max){
            max = line.length();
        }
    }

    /**
     * Метод для вывода полной статистики для данного формата.
     * <p>{@link Format#printStatistic()}
     * <p> Длина самой короткой строки + длина самой длинной строки.
     */
    public void printFullStatistic(){
        printStatistic();
        System.out.println("Min = " + min + ", max = " + max);
    }
}
