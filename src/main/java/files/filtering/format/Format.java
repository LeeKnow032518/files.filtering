package files.filtering.format;

import java.util.ArrayList;
import java.util.List;

public abstract class Format {

    String type;

    int number;

    List<String> elements;

    public Format(String type){
        this.type = type;
        number = 0;
        elements = new ArrayList<>();
    }

    public void updateElements(String newElem) {
        this.elements.add(newElem);
    }

    public void incrementNum(){
        this.number ++;
    }

    public List<String> getElements(){
        return this.elements;
    }

    public void printStatistic(){
        System.out.println(type + ": " + number);
    }

    public String getType() {
        return type;
    }

    public abstract void compareAndSubstitute(String line);

    public abstract void printFullStatistic();
}
