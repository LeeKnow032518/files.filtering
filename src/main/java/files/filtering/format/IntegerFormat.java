package files.filtering.format;

public class IntegerFormat extends Format{

    long min;

    long max;

    public IntegerFormat(String type) {
        super(type);
        min = Long.MAX_VALUE;
        max = Long.MIN_VALUE;
    }

    public void compareAndSubstitute(String line){
        long number = Long.parseLong(line);
        if(number < this.min){
            min = number;
        }

        if(number > this.max){
            max = number;
        }
    }

    public void printFullStatistic(){
        printStatistic();
        System.out.println("Min = " + min + ", max = " + max);
    }
}
