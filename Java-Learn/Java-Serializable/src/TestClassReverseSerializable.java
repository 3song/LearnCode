import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestClassReverseSerializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream=new FileInputStream(".\\temp\\user.ser");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        User user= (User) objectInputStream.readObject();
        System.out.println(user.getaDouble());
        //transient 关键字修饰的属性不会被反序列化
    }
}
