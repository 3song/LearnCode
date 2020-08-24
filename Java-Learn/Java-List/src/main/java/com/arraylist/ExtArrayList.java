package com.arraylist;

import java.util.Arrays;
import java.util.IllegalFormatException;

public class ExtArrayList {
    Object[] elementData; // ArrayList 底层采用数组存放
    private static final int DEFAULT_CAPACITY = 10; //默认数组容量为10
    private int size; //实际ArrayList大小
    public ExtArrayList(int initialCapacity) throws IllegalFormatException {
        if (initialCapacity > 0) {
            //对ExtArrayList 初始化容量
            this.elementData = new Object[initialCapacity];
        }else if (initialCapacity==0){
            this.elementData= new Object[]{};
        }else {
            throw new IllegalArgumentException("集合初始容量不能小于零"+initialCapacity);
        }
    }
    //默认容量为10
    public ExtArrayList(){
        this(DEFAULT_CAPACITY);
    }
    private void ensureExplicitCapacity(int minCapacity) {
        //1.判断实际存放数据容量是否大于 DEFAULT_CAPACITY 则需要扩容
        if (size == minCapacity){  //表示容量已满
            //           int newCapacity=2*size;//扩容量是两倍
//            Object[] newObjects=new Object[newCapacity];
//            for (int i = 0; i < elementData.length; i++) {
//                //把老数组的值复制到新数组中去
//                newObjects[i]=elementData[i];
//            }
            // 之前ArrayList的容量大小 为2
            int oldCapacity = elementData.length; //也就是当前数组实际容量
            // 新的容量大小
            int newCapacity = oldCapacity + (oldCapacity >> 1); // (2+2/2)=3
            if (newCapacity - minCapacity < 0)
                newCapacity = minCapacity; //保证最小容量和minCapacity 一致 一定比1大
            Object[] newObjects = Arrays.copyOf(elementData, newCapacity); //使用方法扩容
            this.elementData=newObjects;
        }
    }
    public void add(int index,Object o){
        //判断数组是否需要扩容
        ensureExplicitCapacity(size+1);
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        //2.使用下标赋值
        elementData[index] = o;
        size++;
    }
    //ArrayList添加方法
    public void add(Object o){
        ensureExplicitCapacity(size+1);
        //2.使用下标赋值
        elementData[size++] = o;
    }
    //使用下标获取数组元素
    public Object get(int index){
        //判断 index 防止数组越界
        rangeCheck(index);
        return elementData[index];
    }
    public void rangeCheck(int index){
        if (index >= size)
            throw new IndexOutOfBoundsException("获取第"+(index+1)+"下标时候越界");
    }

    //使用下标获取数组元素
    public Object remove(int index){
        //判断 index 防止数组越界
        rangeCheck(index);
        //1.首先使用下标查询值是否存在
        Object o = get(index);
        // 计算出删除元素的所有后方元素的长度
        int numMoved=size-index-1;
        System.arraycopy(elementData, index+1, elementData, index,
                numMoved);
        elementData[--size] = null; // 将最后一个元素置为空
        return o;
    }

    //通过对象删除元素 有相同元素只删第一个
    public boolean remove(Object o) {
        for (int i = 0; i <elementData.length ; i++) {
            Object value = elementData[i];
            if (value.equals(o)){
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int getSize(){
        return elementData.length;
    }
}
