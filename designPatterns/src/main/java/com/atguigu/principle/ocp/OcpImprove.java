
package com.atguigu.principle.ocp;

/**
 * Ocp 开闭原则改进
 *
 * @author pengtao
 * @createdate 2022/02/12 0012
 */
public class OcpImprove {

    public static void main(String[] args) {
        GraphicEditorPlus graphicEditor = new GraphicEditorPlus();
        graphicEditor.drawShape(new RectanglePlus());
        graphicEditor.drawShape(new CirclePlus());
        graphicEditor.drawShape(new TrianglePlus());

        graphicEditor.drawShape(new OtherGraphic());

    }
}

/**这是一个用于绘图的类 [使用方]*/
class GraphicEditorPlus {
    //接收Shape对象，然后根据type，来绘制不同的图形
    public void drawShape(ShapePlus s) {
       s.draw();
    }
}

/**Shape类，基类*/
abstract class ShapePlus {
    public abstract void draw();
}

class RectanglePlus extends ShapePlus {

    @Override
    public void draw() {
        System.out.println(" 绘制矩形 ");
    }
}

class CirclePlus extends ShapePlus {

    @Override
    public void draw() {
        System.out.println(" 绘制圆形 ");
    }
}

/**新增画三角形*/
class TrianglePlus extends ShapePlus {

    @Override
    public void draw() {
        System.out.println(" 绘制三角形 ");
    }
}
/** 新增画其他图形 */
class OtherGraphic extends ShapePlus {

    @Override
    public void draw() {
        System.out.println(" 绘制其他图形 ");
    }
}
