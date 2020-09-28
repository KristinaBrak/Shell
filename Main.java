
import shell.Shell;
import shell.ShellImp;

public class Main {
    public static void main(String[] args) {
        String currentDirectory = "~/Desktop/";
        Shell shell = new ShellImp(currentDirectory);
        shell.run();

    }
}