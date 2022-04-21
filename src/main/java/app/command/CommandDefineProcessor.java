package app.command;

import org.apache.log4j.Logger;

import static app.constant.ConstantCommand.*;

/**
 * Класс для определения поступающих на него команд
 */
public class CommandDefineProcessor {

    Logger logger = Logger.getLogger(CommandDefineProcessor.class);

    /**
     * Метод определяет переданные команды и создает экземпляр соответствующей команды
     *
     * @param currentCommand принятая команда
     * @return соответствующий принятому параметру экземпляр команды (по умолчанию выполняется команда EMPTY в случае
     * если команда пришла пустой
     */
    public Command defineCommand(String currentCommand) {

        logger.info("Try to define got command");

        Command command;

        switch (currentCommand) {
            case REGISTRATION_COMMAND:
                command = new RegistrationCommand();
                break;
            case AUTHORIZATION_COMMAND:
                command = new AuthorizationCommand();
                break;
            case REGISTER_COMMAND:
                command = new RegisterCommand();
                break;
            case LOG_IN_COMMAND:
                command = new LogInCommand();
                break;
            default:
                command = new EmptyCommand();
                throw new IllegalStateException("Unexpected value: " + currentCommand);

        }
        return command;
    }
}
