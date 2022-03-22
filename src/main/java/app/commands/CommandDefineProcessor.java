package app.commands;

import static app.constant.ConstantCommand.AUTHORIZATION_COMMAND;
import static app.constant.ConstantCommand.REGISTRATION_COMMAND;

/**
 * Класс для определения поступающих на него команд
 */

public class CommandDefineProcessor {

    /**
     * Метод определяет переданные команды и вызывает соответствующую команду
     *
     * @param currentCommand принятая команда
     * @return соответствующая принятому параметру команда
     */

    public Command defineCommand(String currentCommand) {

        Command command = new EmptyCommand();

        switch (currentCommand) {
            case REGISTRATION_COMMAND:
                command = new RegistrationCommand();
                break;
            case AUTHORIZATION_COMMAND:
                command =  new AuthorizationCommand();
                break;
        }
        return command;
    }
}
