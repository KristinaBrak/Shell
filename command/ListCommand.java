package command;

import java.io.File;
import java.util.List;

import command.Command;
import shell.Directory;

public class ListCommand implements Command {
    private final String PATH = "~/Desktop/";
    private File file;
    private Directory directory;

    public ListCommand(Directory directory) {
        this.file = new File(PATH);
        this.directory = directory;
    }

    public void list() {
        File[] pathNames = file.listFiles();

        if (pathNames.length != 0) {
            for (File pathName : pathNames) {
                System.out.println(pathName.toString());
            }
        } else {
            System.out.println("No files found");
        }
    }

    @Override
    public void start(List<String> arguments) {
        // TODO Auto-generated method stub

    }

}
