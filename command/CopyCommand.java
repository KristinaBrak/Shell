
package command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import shell.Directory;

public class CopyCommand implements Command {
    private Directory directory;
    private FileSaver fileSaver;

    public CopyCommand(Directory directory) {
        this.directory = directory;
        fileSaver = new FileSaver();
    }

    @Override
    public void start(List<String> arguments) throws NoSuchElementException {
        if (arguments.size() != 2) {
            throw new NoSuchElementException("Wrong number of argument ");
        }
        String copyToFile = arguments.get(1);
        String currentDirectory = directory.get() + File.separator + copyToFile;
        String content = copyFile(arguments.get(0));
        // System.out.println(content);
        try {
            fileSaver.save(currentDirectory, content);
        } catch (IOException e) {
            throw new NoSuchElementException();
        }
    }

    private String copyFile(String originalFileName) {
        File copyFromFile = new File(directory.get() + File.separator + originalFileName);
        String content = "";
        try {
            Scanner scan = new Scanner(copyFromFile);

            while (scan.hasNextLine()) {
                content = scan.nextLine();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            throw new NoSuchElementException("File" + originalFileName + "does not exist");
        }
        return content;
    }

}
