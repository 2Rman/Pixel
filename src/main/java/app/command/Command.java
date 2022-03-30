package app.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Абстрактный метод...создан для того, чтобы все команды реализовывали один и тот же метод, но каждая по-своему.
 * По сути мы все команды приводим к одному и тому же типу, чтобы легче было реализовывать выполнение этих команд.
 * Создает слой абстракции для защиты кода.
 */
public interface Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
