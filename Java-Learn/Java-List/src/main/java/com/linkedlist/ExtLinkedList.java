package com.linkedlist;

/**
 * @Author 陈磊
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
public class ExtLinkedList<E> {
    //LinkedList 大小
    private int size = 0;
    //第一个元素 （头节点，为了定位查询开始位置）
    public Node first;
    //最后一个元素 （尾节点，为了定位添加开始位置）
    public Node last;
    public class Node {
        public Object value;
        public Node next;
        public Node prev;

        Node() {}
    }
    //在最后位置追加元素
    public void add(E e){
        Node node=new Node();
        //给节点赋值
        node.value=e;
        if(first==null){ //第一个节点都没有说明 list为空时
            //给第一个元素赋值node节点赋值
            first=node;
            //第一个添加的元素的头和尾都为自己
        }else{
            //添加第二个或以上数据
            node.prev=last; //因为默认添加元素是往最后一个位置追加
            last.next=node; //未赋值之前 last=node; last依然是前一个元素
        }
        last=node;
        size++;
    }
    //按下标位置插入元素
    public void add(int index,Node node){
        Node inNode=getNode(index);
        inNode.next=node.next;
        inNode.prev=node.prev;

        size++;
    }

    public Object get(int index){
        return getNode(index).value;
    }
    public Node getNode(int index){
        //验证下标index
        checkElementIndex(index);
        Node node=null;
        if (first!=null){
            node=first;
            for (int i = 0; i < index; i++) {
                node=node.next;
            }
        }
        return node;
    }

    public void remove(int index){
        checkElementIndex(index);
        Node node=getNode(index);
        Node oldPrev = node.prev;
        Node oldNext = node.next;
        if (node.prev==null){
            //说明删除的是第一个元素
            first=node.next;
        }else{
            oldPrev.next=oldNext;
            node.prev=null;//让垃圾回收机制回收对象
        }
        if (node.next==null){
            //说明删除的是最后一个元素
            last=oldPrev.prev;
        }else{
            oldNext.prev=oldPrev;
            node.next=null;//让垃圾回收机制回收对象
        }
        node.value=null;//让垃圾回收机制回收对象
        /*if (node!=null){
            node.prev=node.next;
        }*/
        size--;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("下标越界");
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public int getSize(){
        return size;
    }
}
