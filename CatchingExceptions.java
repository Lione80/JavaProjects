// Доп класс работы с исключениями
class CatchingExceptions extends Exception{
    // Метод вывода нужного сообщения
    CatchingExceptions(String description) {
        super(description);
    }
}