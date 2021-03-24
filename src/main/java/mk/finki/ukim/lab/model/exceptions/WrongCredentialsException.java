package mk.finki.ukim.lab.model.exceptions;

public class WrongCredentialsException extends  RuntimeException {
    public WrongCredentialsException(){
        super("Invalid user credentials");
    }
}
