package command;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

import shell.Directory;

public class ListCommand implements Command {
    private Directory directory;

    public ListCommand(Directory directory) {
        this.directory = directory;
    }

    @Override
    public void start(List<String> arguments) {
        list();
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
        String tabs = "\t\t";
        if (file.isDirectory()) {
            return file.getName() + tabs + "directory";
        } else {
            long fileSize = file.getUsableSpace() / (long) 1000000;
            return file.getName() + tabs + String.valueOf(fileSize);
        }

    }

}
