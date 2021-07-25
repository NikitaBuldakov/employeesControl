package CustomException;

public class ExceptionHandler extends Exception{

    public ExceptionHandler(){}

    public ExceptionHandler(String message){
        super(message);
        super.printStackTrace();
    }

    public ExceptionHandler(String message, Throwable cause) {
        super (message, cause);
        super.printStackTrace();
    }
}