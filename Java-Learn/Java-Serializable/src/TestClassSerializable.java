import java.io.*;

public class TestClassSerializable{
    public static void main(String[] args) throws IOException {
        User user=new User();
        user.setAge(1);
        user.setaDouble(123);
        File file=new File(".\\temp");
        if(!file.exists()){
            file.mkdirs();
        }
        FileOutputStream fileOutputStream=new FileOutputStream(file+"\\user.ser");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(user);
        objectOutputStream.close();
        fileOutputStream.close();
    }

}
