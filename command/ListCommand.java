package command;

import java.io.File;
import java.util.List;

import command.Command;

public class ListCommand implements Command {
    private final String PATH = "~/Desktop/";
    private File file;

    public ListCommand() {
        this.file = new File(PATH);
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
