package files.filtering.service;

import files.filtering.cli.Args;
import files.filtering.format.Format;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

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

        BufferedWriter bw = createWriter(arguments.isAdd(), path, arguments.getPath());
            for (String l : format.getElements()) {
                bw.write(l);
                bw.newLine();
            }
        bw.close();
    }

    public static BufferedWriter createWriter(boolean toAdd, String path, String pathToFile) throws IOException {

        List<String> pathArgs = Arrays.stream(path.split("/")).toList();

        Path filePath = Path.of(path);

        if (toAdd) {
            if (!Files.exists(filePath)) {
                throw new FileNotFoundException("couldn't find file on path " + path);
            }
            return new BufferedWriter(new java.io.FileWriter(path, true));
        } else {
            try {
                return Files.newBufferedWriter(filePath,
                    StandardOpenOption.CREATE_NEW,
                    StandardOpenOption.WRITE);
            } catch (NoSuchFileException e){
                throw new NoSuchFileException("couldn't find file path " + pathToFile);
            } catch (FileAlreadyExistsException e) {
                throw new FileNotFoundException("file " + pathArgs.get(pathArgs.size() - 1) + " already exists");
            } catch (IOException e){
                throw new IOException("couldn't create file " + pathArgs.get(pathArgs.size() - 1));
            }
        }
    }
}
