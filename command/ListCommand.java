package command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import shell.Directory;

public class ListCommand implements Command {
    private Directory directory;
    private final int NUMBER_OF_ARGUMENTS = 0;
    private final int LINE_LENGTH = 50;

    public ListCommand(Directory directory) {
        this.directory = directory;
    }

    @Override
    public void start(List<String> arguments) {
        if (!hasCorrectNumberOfArguments(arguments)) {
            throw new NoSuchElementException("Incorrect number of arguments");
        }
        list();
    }

    private boolean hasCorrectNumberOfArguments(List<String> arguments) {
        if (arguments.size() != NUMBER_OF_ARGUMENTS) {
            return false;
        }
        return true;
    }

    private void list() {

        File dir = new File(this.directory.get());
        String[] listOfFileNames = dir.list();
        String currentPath = dir.getPath() + "/";

        getStream(listOfFileNames, currentPath).forEach(file -> System.out.println(getFormatedFileDisplay(file)));
    }

    private Stream<File> getStream(String[] listOfFileNames, String currentPath) {
        return Stream.of(listOfFileNames).map(fileName -> new File(currentPath + fileName));
    }

    private String getFormatedFileDisplay(File file) {

        String fileName = file.getName();
        String fileSize = getSize(file);
        StringBuilder sb = new StringBuilder(LINE_LENGTH);
        sb.append(fileName);
        for (int i = 0; i + fileName.length() + fileSize.length() < LINE_LENGTH; i++) {
            sb.append(".");
        }
        sb.append(fileSize);
        return sb.toString();
    }

    private String getSize(File file) {
        if (file.isDirectory()) {
            return "directory";
        }
        return String.valueOf(getFileSize(file.toPath()));
    }

    private long getFileSize(Path path) {
        long size;
        try {
            size = Files.size(path);
        } catch (IOException e) {
            size = 0;
        }
        return size;
    }

}
