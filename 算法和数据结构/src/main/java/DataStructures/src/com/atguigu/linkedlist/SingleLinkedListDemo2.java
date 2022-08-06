package DataStructures.src.com.atguigu.linkedlist;



/**
 *
 */
class PointGardNode {
    public int id;
    public String name;
    public String nickname;
    public PointGardNode next;

    public PointGardNode(int id, String name, String nickname) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "PointGardNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

class SingleLinkedList2 {

    private PointGardNode head = new PointGardNode(0, "",  null);

    public PointGardNode getHead() {
        return head;
    }

    public void add(PointGardNode pointGardNode) {
        PointGardNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = pointGardNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void addOrder(PointGardNode pointGardNode) {
      //根据排名
        PointGardNode temp = head;
        boolean flag = false; // flag标志添加的编号是否存在，默认为false

        while (true) {

            if (temp.next == null) {
                break;
            }
            if (temp.next.id == pointGardNode.id) {
                System.out.println("exists, add fail: " + pointGardNode);
                flag = true;
                break;
            } else if (temp.next.id > pointGardNode.id) {

                break;
            }
            temp = temp.next;
        }

        if (flag) {
            System.out.printf("star exists, add fail \n", pointGardNode.id);
        } else {
            pointGardNode.next = temp.next; //  o1  o2  o3  o1.5放入 o1 和 o2
            temp.next = pointGardNode;
        }
    }
    public PointGardNode get() {
        PointGardNode temp = head;

        while (true) {
            if (temp.next == null) {
                return temp;
            }
            temp = temp.next;
        }
    }

    public void list() {
        if(head.next == null) {
            System.out.println("list is null");
            return;
        }
        PointGardNode temp = head.next;
        while (true) {
            if(temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void update(PointGardNode pointGardNode) {
        PointGardNode temp = head;
        while (true) {
            if (head.next == null) {
                System.out.println("list is null");
                return;
            }
            if (temp.id == pointGardNode.id) {
                temp.name = pointGardNode.name;
                temp.nickname = pointGardNode.nickname;
                break;
            }
            temp = temp.next;
        }
    }

    public void delete(PointGardNode pointGardNode) {
        PointGardNode temp = head;
        if (head.next == null) {
            System.out.println("list is null");
            return;
        }
        while (true) {
            if (temp.next.id == pointGardNode.id) {
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }
}
public class SingleLinkedListDemo2 {
    public static void main(String[] args) {
         PointGardNode pg1 = new PointGardNode(1, "Jordan", "Black Jesus");
         PointGardNode pg2 = new PointGardNode(2, "Kobe", "Black Mamba");
         PointGardNode pg3 = new PointGardNode(3, "Wade", "Flash");

        SingleLinkedList2 singleLInkedList = new SingleLinkedList2();
        singleLInkedList.add(pg1);
        singleLInkedList.add(pg2);
        singleLInkedList.add(pg3);

        singleLInkedList.list();

        PointGardNode pg3new = new PointGardNode(3, "Harden", "huzi");

        singleLInkedList.update(pg3new);

        singleLInkedList.list();

//        System.out.println();
//        System.out.println("delete");
//        singleLInkedList.delete(pg3);
//        singleLInkedList.delete(pg2);
//        singleLInkedList.list();
//        singleLInkedList.delete(pg1);
//        singleLInkedList.list();
//        singleLInkedList.delete(pg1);
//        singleLInkedList.list();
        System.out.println("----");
        singleLInkedList.addOrder(new PointGardNode(3, "Wade", "Flash"));
        singleLInkedList.addOrder(new PointGardNode(6, "Allen", "gentleman"));
        System.out.println("----");
        singleLInkedList.list();
        System.out.println();
        singleLInkedList.addOrder(new PointGardNode(6, "Allen", "gentleman"));
        singleLInkedList.addOrder(new PointGardNode(5, "Allen", "gentleman"));
        System.out.println("----");
        singleLInkedList.list();
    }
}
