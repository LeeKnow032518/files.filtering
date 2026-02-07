package files.filtering.format;

import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактный класс для обработки статистики по разным типам элементов.
 * <p> Содержит следующие методы:
 *     <ul>
 *         <li> Обновление списка элементов</li>
 *         <li> Получение списка всех элементов данного формата</li>
 *         <li> Вывод краткой статистики</li>
 *         <li> Получение типа хранимых элементов</li>
 *         <li> Метод для сравнения и замены уже имеющейся информации на новый элемент (абстрактный)</li>
 *         <li> Метод для вывода полной статистики для данного формата (абстрактный)</li>
 *     </ul>
 * </p>
 */
public abstract class Format {

    /**
     * Тип хранимых элементов
     */
    String type;

    /**
     * Список элементов данного формата
     */
    List<String> elements;

    /**
     * Конструктор
     * @param type информация о типе элементов, которые будут храниться
     */
    public Format(String type){
        this.type = type;
        elements = new ArrayList<>();
    }

    /**
     * Метод для добавления нового элемента.
     * @param newElem добавляемый элемент.
     */
    public void updateElements(String newElem) {
        this.elements.add(newElem);
    }

    /**
     * @return возвращает список элементов.
     */
    public List<String> getElements(){
        return this.elements;
    }

    /**
     * Вывод в командную строку краткой информации.
     * <p> Тип + количество элементов.</p>
     */
    public void printStatistic(){
        System.out.println(type + ": " + elements.size());
    }

    /**
     * @return Возвращает тип хранимых элементов.
     */
    public String getType() {
        return type;
    }

    /**
     * Абстрактный метод для сравнения и замены уже имеющейся информации на новый элемент.
     * @param line элемент, с которым будет сравниваться информация.
     */
    public abstract void compareAndSubstitute(String line);

    /**
     * Абстрактный метод для вывода в командную строку полной статистики.
     */
    public abstract void printFullStatistic();
}
