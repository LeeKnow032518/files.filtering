package files.filtering.format;

/**
 * Класс, расширяющий класс {@link files.filtering.format.Format}
 * <p> Работает с числами с плавающей запятой.</p>
 * <p> Содержит следующие методы:
 *  <ul>
 *      <li> Перегрузка метода {@link files.filtering.format.Format#compareAndSubstitute(String)}</li>
 *      <li> Перегрузка метода {@link Format#printFullStatistic()}</li>
 *  </ul>
 * </p>
 */
public class FloatFormat extends Format{

    /**
     * Минимальный элемент
     */
    double min;

    /**
     * Максимальный элемент
     */
    double max;

    /**
     * Конструктор
     * @param type информация о типе элементов, которые будут храниться
     */
    public FloatFormat(String type) {
        super(type);
        min = Double.MAX_VALUE;
        max = Double.MIN_VALUE;
    }

    /**
     * Метод сравнивает новый элемент с минимальным и максимальным и заменяет на него,
     * если он меньше или больше имеющихся соответственно.
     * @param line элемент, с которым будет сравниваться информация.
     */
    public void compareAndSubstitute(String line){
        double number = Double.parseDouble(line);
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
        double sum = elements.stream().map(Double::parseDouble).reduce(Double::sum).get();
        double avg = sum / elements.size();
        System.out.println("Min = " + min + ", max = " + max +
            ", sum = " + sum + ", avg = " + avg);
    }
}
