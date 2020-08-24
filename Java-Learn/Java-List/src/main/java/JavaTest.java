import com.linkedlist.ExtLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class JavaTest {

    //1.JavaArrayList底层实现通过Object[] elementData数组 源码
    //2.怎么保证集合中存放无限大小的数据
    //3. >> 表示位移
    public static void main(String[] args) {
        /*Object[] objects={1,2,3};
        //copy 原来的数组 扩容至newLength 大小 并返回新的数组
        Object[] objects1 = Arrays.copyOf(objects, 30);
        //src 参数 srcPos 原数组 dest 起始位置 destPos 目标数组 length 目标起始位置
        //System.arraycopy();*/
        ExtLinkedList<String> extLinkedList=new ExtLinkedList();
        extLinkedList.add("a");
        extLinkedList.add("b");
        extLinkedList.add("c");
        extLinkedList.add("d");
        extLinkedList.add("e");
        extLinkedList.remove(1);
        for (int i = 0; i <extLinkedList.getSize() ; i++) {
            System.out.println(extLinkedList.get(i));
        }

    }
}
