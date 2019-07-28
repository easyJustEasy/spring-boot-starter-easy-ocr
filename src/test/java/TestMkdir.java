import java.io.File;

public class TestMkdir {
    public static void main(String[] args) {
        File tmp = new File("C:\\ttmp\\interviewer");
        if(!tmp.exists()){
            tmp.mkdirs();
        }
    }
}
