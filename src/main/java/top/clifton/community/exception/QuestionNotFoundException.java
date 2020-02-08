package top.clifton.community.exception;

/**
 * @author Clifton
 * @create 2020/2/8 - 16:16
 */
public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(String message){
        super(message);
    }

}
