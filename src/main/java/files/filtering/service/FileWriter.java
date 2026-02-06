package files.filtering.service;

import files.filtering.cli.Args;
import files.filtering.format.Format;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileWriter {

    public static void writeOutputFile(Format format, Args arguments) throws IOException {
        StringBuilder sb = new StringBuilder();
        if(!arguments.getPath().isEmpty()) {
            sb.append(arguments.getPath()).append("/");
        }
        if(!arguments.getPrefix().isEmpty()){
            sb.append(arguments.getPrefix()).append("-");
        }
        sb.append(format.getType()).append(".txt");

        String path = sb.toString();

        System.out.println(path);

        BufferedWriter bw = createWriter(path, arguments.isAdd());
        for(String l : format.getElements()){
           bw.write(l);
            bw.newLine();
        }
        bw.close();
    }

    public static BufferedWriter createWriter(String path, boolean addMode) throws IOException {
        Path filePath = Path.of(path);

        if (addMode) {
            if (!Files.exists(filePath)) {
                throw new FileNotFoundException();
            }
            return Files.newBufferedWriter(filePath, StandardOpenOption.WRITE);
        } else {
            return Files.newBufferedWriter(filePath,
                StandardOpenOption.CREATE_NEW,
                StandardOpenOption.WRITE);
        }
    }
}
