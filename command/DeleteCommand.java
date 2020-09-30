package command;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import shell.Directory;

public class DeleteCommand implements Command {
    private Directory directory;
    private final int NUMBER_OF_ARGUMENTS = 1;

    public DeleteCommand(Directory directory) {
        this.directory = directory;
    }

    @Override
    public void start(List<String> arguments) throws NoSuchElementException {
        if (arguments.size() != NUMBER_OF_ARGUMENTS) {
            throw new NoSuchElementException("Incorrect argument number");
        }
        String fileName = arguments.get(0);
        String currentDirectory = directory.get();
        String directoryToBeDeleted = currentDirectory + File.separator + fileName;
        delete(directoryToBeDeleted);
    }

    private void delete(String directoryToBeDeleted) throws NoSuchElementException {
        File dir = new File(directoryToBeDeleted);
        File[] allContents = dir.listFiles();
        if (allContents != null) {
            Stream.of(allContents).forEach(file -> delete(file.getAbsolutePath()));
            dir.delete();
        }
        throw new NoSuchElementException("No such file/directory");
    }

}
