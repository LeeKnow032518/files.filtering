package files.filtering.format;

public class FloatFormat extends Format{

    double min;

    double max;

    public FloatFormat(String type) {
        super(type);
        min = Double.MAX_VALUE;
        max = Double.MIN_VALUE;
    }

    public void compareAndSubstitute(String line){
        double number = Double.parseDouble(line);
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
