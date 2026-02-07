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

public class FileParser {

    public static List<Format> parseInputFiles(Args args) throws IOException{
        Format integers = new IntegerFormat("integers");
        Format floats = new FloatFormat("floats");
        Format strings = new StringFormat("strings");

        for(String fileName : args.getInputFiles()) {
            try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (isInteger(line)) {
                        integers.incrementNum();
                        integers.updateElements(line);

                        integers.compareAndSubstitute(line);
                    } else if (isFloat(line)) {
                        floats.incrementNum();
                        floats.updateElements(line);

                        floats.compareAndSubstitute(line);
                    } else {
                        strings.incrementNum();
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

    private static boolean isInteger(String str) {
        return str != null && str.matches("-?\\d+");
    }

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
