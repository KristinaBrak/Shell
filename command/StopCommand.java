package command;

import java.util.List;

public class StopCommand implements Command {

    @Override
    public void start(List<String> arguments) {
        stopProgram();
    }

    private void stopProgram() {
        System.exit(0);
    }
}
