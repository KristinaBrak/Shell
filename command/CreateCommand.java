package command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import shell.Directory;

public class CreateCommand implements Command {
    private Directory directory;
    private final String CONTENT_SYMBOL = "\"";

    public CreateCommand(Directory directory) {
        this.directory = directory;
    }

    @Override
    public void start(List<String> arguments) throws NoSuchElementException {
        String fileName = arguments.get(0);
        arguments.remove(0);
        String content = getContent(arguments);
        try {
            File file = createFile(fileName);
            addContentToFile(file, content);
        } catch (IOException e) {
            throw new NoSuchElementException("Path is incorrect");
        }
    }

    private File createFile(String fileName) throws IOException {
        String path = directory.get() + File.separator + fileName;
        File file = new File(path);

        file.createNewFile();
        return file;
    }

    private void addContentToFile(File file, String content) throws IOException {
        FileWriter writter = new FileWriter(file);
        writter.write(content);
        writter.close();
    }

    private String getContent(List<String> arguments) throws NoSuchElementException {
        int lastPosition = arguments.size() - 1;
        if (!isContent(arguments.get(0), arguments.get(lastPosition))) {
            throw new NoSuchElementException("No content specified");
        }

        String firstWord = arguments.get(0).split(CONTENT_SYMBOL)[1];
        String lastWord = arguments.get(lastPosition).split(CONTENT_SYMBOL)[0];
        arguments.remove(lastPosition);
        arguments.remove(0);

        String argumentSeperator = " ";
        String content = firstWord + argumentSeperator;
        content += arguments.stream().collect(Collectors.joining(argumentSeperator));
        content += argumentSeperator + lastWord;
        return content;
    }

    private boolean isContent(String first, String last) {
        if (isContainingContentSymbols(first) && isContainingContentSymbols(last)) {
            return true;
        }
        return false;
    }

    private boolean isContainingContentSymbols(String content) {
        return content.contains(CONTENT_SYMBOL);
    }
}
