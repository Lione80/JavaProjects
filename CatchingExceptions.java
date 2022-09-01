// Доп класс работы с исключениями
public class CatchingExceptions extends Exception{
    // Метод вывода нужного сообщения
    public CatchingExceptions(String description) {
        super(description);
    }
}