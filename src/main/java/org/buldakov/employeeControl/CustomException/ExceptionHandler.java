package org.buldakov.employeeControl.CustomException;

public class ExceptionHandler extends Exception{

    public ExceptionHandler(){super.printStackTrace();}

    public ExceptionHandler(String message){
        super(message);
        super.printStackTrace();
    }

    public ExceptionHandler(String message, Throwable cause) {
        super (message, cause);
        super.printStackTrace();
    }
}