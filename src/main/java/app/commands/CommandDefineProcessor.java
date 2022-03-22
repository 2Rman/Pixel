package app.commands;

import static app.constant.ConstantCommand.*;
import static app.constant.ConstantPage.*;

public class CommandDefineProcessor {

    public String defineCommand(String command) {

        switch (command) {
            case REGISTER_COMMAND:
                return REGISTER_PAGE;
            case LOGIN_COMMAND:
                return LOGIN_PAGE;
        }

        return MAIN_PAGE;
    }
}
