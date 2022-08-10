package 海尔2022年笔试题;

/**
 * 草：这道题考的是  构造方法无返回值
 * 以下 4个Definition的构造函数定义中，哪个会产生错误？
 * 答案：// Definition4   因为虽然没有编译错误，但是它不是一个构造函数，只是一个普通的方法
 */
class testCreator {
    int questCount;


    public testCreator(int nextQuest) {
        nextQuest = questCount + 1;
    } // Definition1

    testCreator(testCreator t) {
        questCount = t.questCount + 1;
    } // Definition2

    public testCreator(float nextQuest) {
        questCount = (int) nextQuest;
    } // Definition3

    void TestCreator(int nextQuest) {
        questCount = nextQuest;
    } // Definition4

    public static void main(String[] args) {
        new testCreator(1);
        new testCreator(1f);
        new testCreator(new testCreator(1));

    }

}


