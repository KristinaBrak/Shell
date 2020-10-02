
package command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import shell.Directory;

public class CopyCommand implements Command {
    private Directory directory;

    public CopyCommand(Directory directory) {
        this.directory = directory;
    }

    @Override
    public void start(List<String> arguments) throws NoSuchElementException {
        if (arguments.size() != 2) {
            throw new NoSuchElementException("Incorrect number of arguments");
        }
        copy(arguments);

    }

    private void copy(List<String> arguments) throws NoSuchElementException {
        File fileFrom = new File(directory.get() + File.separator + arguments.get(0));
        File fileTo = new File(directory.get() + File.separator + arguments.get(1));

        copy(fileFrom, fileTo);

    }

    private void copy(File source, File destination) {
        copyFile(source, destination);
        File[] files = source.listFiles();
        if (files != null) {
            Stream.of(files).forEach(file -> {
                copy(file, new File(destination + File.separator + file.getName()));
            });
        }

    }

    private void copyFile(File fileFrom, File fileTo) {

        try {
            System.out.printf("from: %s to: %s\n", fileFrom.getPath(), fileTo.getPath());
            Files.copy(fileFrom.toPath(), fileTo.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new NoSuchElementException("Cannot copy " + fileFrom.getName() + " to " + fileTo.getName());
        }
    }

}
