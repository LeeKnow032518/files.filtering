package files.filtering.format;

public class StringFormat extends Format{

    int min;

    int max;

    public StringFormat(String type) {
        super(type);
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
    }

    public void compareAndSubstitute(String line){
        if(line.length() < this.min){
            min = line.length();
        }

        if(line.length() > this.max){
            max = line.length();
        }
    }

    public void printFullStatistic(){
        printStatistic();
        System.out.println("Min = " + min + ", max = " + max);
    }
}
