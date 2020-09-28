package shell;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Directory {
    private String currentDirectory;
    Stack<String> stack = new Stack<String>();
    private final String seperator = "/";

    public Directory(String directory) {
        currentDirectory = directory;
        pushListToStack(directory);
    }

    public void add(String enteredFolder) {
        stack.push(enteredFolder);
        currentDirectory += seperator + enteredFolder;
    }

    public void remove() {
        String folderToRemove = stack.pop();
        int endIndex = currentDirectory.length() - folderToRemove.length() - 1;
        currentDirectory = currentDirectory.substring(0, endIndex);
    }

    public String get() {
        return currentDirectory;
    }

    private void pushListToStack(String directory) {
        List<String> directories = seperateDirectories(directory);
        directories.stream().forEach(dir -> stack.push(dir));
    }

    private List<String> seperateDirectories(String directory) {
        String[] folders = directory.trim().split("/");
        var directories = Stream.of(folders).map(folder -> folder.trim()).collect(Collectors.toList());
        return directories;
    }

}
