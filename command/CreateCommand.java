package command;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import shell.Directory;
import shell.FileSaver;

public class CreateCommand implements Command {
    private Directory directory;
    private final String CONTENT_SYMBOL = "\"";
    private FileSaver fileSaver;
    private final int NUMBER_OF_ARGUMENTS = 2;

    public CreateCommand(Directory directory) {
        this.directory = directory;
        this.fileSaver = new FileSaver();
    }

    @Override
    public void start(List<String> arguments) throws NoSuchElementException {
        if (!hasEnoughArguments(arguments)) {
            throw new NoSuchElementException("Incorrect number of arguments");
        }
        String fileName = arguments.remove(0);

        String content = getContent(arguments);

        String path = directory.get() + File.separator + fileName;
        try {
            fileSaver.save(path, content);
        } catch (IOException e) {
            throw new NoSuchElementException("Path is incorrect");
        }
    }

    private boolean hasEnoughArguments(List<String> arguments) {
        if (arguments.size() >= NUMBER_OF_ARGUMENTS) {
            return true;
        }
        return false;
    }

    private String getContent(List<String> arguments) throws NoSuchElementException {

        int lastPosition = arguments.size() - 1;
        if (!isContent(arguments.get(0), arguments.get(lastPosition))) {
            throw new NoSuchElementException("No content specified");
        }

        String content = arguments.stream().collect(Collectors.joining(" "));

        content = content.replace("\"", "");
        return content;
    }

    private boolean isContent(String first, String last) {
        if (isContainingContentSymbols(first) && isContainingContentSymbols(last)) {
            return true;
        }
        return false;
    }

    private boolean isContainingContentSymbols(String content) {
        return content.contains((CharSequence) CONTENT_SYMBOL);

    }
}
