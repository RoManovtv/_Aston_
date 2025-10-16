public class MyArrayDataException extends Exception {
    public MyArrayDataException(int row, int col) {
        super("Неверные данные в ячейке [" + row + "][" + col + "]");
    }
}