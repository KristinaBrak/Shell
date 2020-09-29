package command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {

    public void save(String currentDirectory, String content) throws IOException {
        try {
            File file = createFile(currentDirectory);
            addContentToFile(file, content);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private File createFile(String currentDirectory) throws IOException {
        String path = currentDirectory;
        File file = new File(path);

        file.createNewFile();
        return file;
    }

    private void addContentToFile(File file, String content) throws IOException {
        FileWriter writter = new FileWriter(file);
        writter.write(content);
        writter.close();
    }
}
