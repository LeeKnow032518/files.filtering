package files.filtering.format;

/**
 * Класс, расширяющий класс {@link files.filtering.format.Format}
 * <p> Работает с целыми числами.</p>
 * <p> Содержит следующие методы:
 *  <ul>
 *      <li> Перегрузка метода {@link files.filtering.format.Format#compareAndSubstitute(String)}</li>
 *      <li> Перегрузка метода {@link Format#printFullStatistic()}</li>
 *  </ul>
 * </p>
 */
public class IntegerFormat extends Format{

    /**
     * Минимальный элемент
     */
    long min;

    /**
     * Максимальный элемент
     */
    long max;

    /**
     * Конструктор
     * @param type информация о типе элементов, которые будут храниться
     */
    public IntegerFormat(String type) {
        super(type);
        min = Long.MAX_VALUE;
        max = Long.MIN_VALUE;
    }

    /**
     * Метод сравнивает новый элемент с минимальным и максимальным и заменяет на него,
     * если он меньше или больше имеющихся соответственно.
     * @param line элемент, с которым будет сравниваться информация.
     */
    public void compareAndSubstitute(String line){
        long number = Long.parseLong(line);
        if(number < this.min){
            min = number;
        }

        if(number > this.max){
            max = number;
        }
    }

    /**
     * Метод для вывода полной статистики для данного формата.
     * <p>{@link Format#printStatistic()}
     * <p> Минимальный элемент + максимальный элемент + сумма элементов + среднее значение.
     */
    public void printFullStatistic(){
        printStatistic();
        long sum = elements.stream().map(Long::parseLong).reduce(Long::sum).get();
        double avg = (double) sum / elements.size();
        System.out.println("Min = " + min + ", max = " + max +
            ", sum = " + sum + ", avg = " + avg);
    }
}
