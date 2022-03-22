package app.commands;

import static app.constant.ConstantCommand.AUTHORIZATION_COMMAND;
import static app.constant.ConstantCommand.REGISTRATION_COMMAND;

/**
 * Класс для определения поступающих на него команд
 */

public class CommandDefineProcessor {

    /**
     * Метод определяет переданные команды и создает экземпляр соответствующей команды
     *
     * @param currentCommand принятая команда
     * @return соответствующий принятому параметру экземпляр команды (по умолчанию выполняется команда EMPTY в случае
     * если команда пришла пустой
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
