package command;

import java.util.List;

public class StopCommand implements Command {

    public StopCommand() {

    }

    private void stopProgram() {
        System.exit(0);
    }

    @Override
    public void start(List<String> arguments) {
        // TODO Auto-generated method stub

    }
}
