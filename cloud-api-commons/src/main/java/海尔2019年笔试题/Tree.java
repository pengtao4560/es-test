package 海尔2019年笔试题;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*/
 19. 给定一个Foo的列表，请编写一个方法，将Foo加工为树形结构(即填满children属性)
  class Foo {
  Long id;
  Long parentId;
  List<Foo> children;
  }
  public Foo buildFoo(List<Foo> fooList) {
  Foo root=new Foo();

  return root;
  }
      /*/

@Data
class Foo {
    Integer id;
    Integer parentId;
    List<Foo> children;

    public Foo buildFoo(List<Foo> fooList) {
        Foo root = new Foo();

        return root;
    }

    public Foo() {
    }

    public Foo(Integer id, Integer parentId, List<Foo> children) {
        this.id = id;
        this.parentId = parentId;
        this.children = children;
    }
}
public class Tree {
    // 最顶级的文件夹目录，parentId = 0
    List<Foo> FooList = Arrays.asList(new Foo(1,  0, null),
            new Foo(2,  1, null),
            new Foo(3,  1, null),
            new Foo(4,  2, null),
            new Foo(5,  2, null),
            new Foo(6, 0, null),
            new Foo(7,  0, null)
    );

    public List<Foo> generateFoo(List<Foo> FooList) {
        List<Foo> rootFoo = new ArrayList<>();

        for (Foo Foo : FooList) {
            // 第一步 筛选出最顶级的父节点
            if (0 == Foo.getParentId()) {
                rootFoo.add(Foo);
            }
            // 第二步 筛选出该父节点下的所有子节点列表
            for (Foo node : FooList) {
                if (node.getParentId().equals(Foo.getId())) {
                    if (CollectionUtils.isEmpty(Foo.getChildren())) {
                        Foo.setChildren(new ArrayList<>());
                    }
                    Foo.getChildren().add(node);
                }
            }
        }
        return rootFoo;
    }

    /**
     * 递归方法转换成树形结构
     * @param FooList
     * @return
     */
    public static List<Foo> recursionMethod(List<Foo> FooList) {
        List<Foo> Foos = new ArrayList<>();
        for (Foo Foo : FooList) {
            // 找出父节点
            if (0 == Foo.getParentId()) {
                // 调用递归方法填充子节点列表
                Foos.add(findChildren(Foo, FooList));
            }
        }
        return Foos;
    }
    /**
     * 递归方法
     * @param Foo 父节点对象
     * @param FooList 所有的List
     * @return
     */
    public static Foo findChildren(Foo Foo, List<Foo> FooList) {
        for (Foo node : FooList) {
            if (Foo.getId().equals(node.getParentId())) {
                if (Foo.getChildren() == null) {
                    Foo.setChildren(new ArrayList<>());
                }
                // 递归 调用自身
                Foo.getChildren().add(findChildren(node, FooList));
            }
        }
        return Foo;
    }

}
