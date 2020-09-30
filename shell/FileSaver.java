package shell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {

    public void save(String currentDirectory, String content) throws IOException {
        try {
            File file = createFile(currentDirectory);
            addContentToFile(file, content);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private File createFile(String currentDirectory) throws IOException {
        String path = currentDirectory;
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new IOException("Cannot create file");
        }
        return file;
    }

    private void addContentToFile(File file, String content) throws IOException {
        FileWriter writter = new FileWriter(file);
        writter.write(content);
        writter.close();
    }
}
