package files.filtering.service;

import files.filtering.cli.Args;
import files.filtering.format.Format;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

/**
 * Класс для записи результатов парсинга по типам в выходные файлы.
 * <p>Содержит следующие методы:
 *      <ul>
 *          <li> Метод для записи в файлы</li>
 *          <li> Метод для создания или открытия выходных файлов в зависимости от опций командной строки.</li>
 *      </ul>
 * </p>
 */
public class FileWriter {

    /**
     * Метод для записи элементов конкретного типа в соответствующий файл.
     * <p> Метод формирует путь, по которому создается или открывается файл типа элементов,
     * описанного в поле {@link Format#getType()}, записывает в файл все элементы.</p>
     * @param format содержит список элементов для записи, и тип записываемых элементов для формирования названия файла.
     * @param arguments содержит информацию о пути к файлу, префиксе для названия файла и режиме перезаписи.
     * @throws IOException возникает при проблемах с открытием/созданием выходного файла.
     */
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

    /**
     * Метод для создания и открытия выходных файлов для записи результатов работы программы.
     * <p> При режиме перезаписи проверяет наличие требуемого файла, в случае неудачи выбрасывает {@code FileNotFoundException}</p>.
     *     Если режим отключен - пытается создать файл.
     *     <ul>
     *         <li> В случае некорректного пути, выбрасывает {@code NoSuchFileException},</li>
     *         <li> В случае, когда уже существует файл с аналогичным называнием, выбрасывает {@code FileNotFoundException},</li>
     *     </ul>
     * @param toAdd Режим добавления данных в существующие файлы.
     *  {@code true} - режим включен,
     *  {@code false}- режим отключен
     * @param path Путь до файла для записи.
     * @param pathToFile Путь до каталога, в котором будут лежать выходные файлы
     * @return Возвращает экземпляр класса для записи в файл
     * @throws IOException в случае непредвиденной ошибки в работе с файлом, или более конкретные, описанные выше.
     */
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
