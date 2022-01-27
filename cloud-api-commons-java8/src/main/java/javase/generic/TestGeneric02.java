package javase.generic;
/**任务接口对象*/
interface Task<Param,Result>{//泛型接口
	Result execute(Param arg1);
}
/**将字符串转换为整数*/
class IntegerConvertTask
      implements Task<String,Integer>{
	@Override
	public Integer execute(String arg1) {
		return Integer.valueOf(arg1);
	}
}
public class TestGeneric02 {
    public static void main(String[] args) {
    	IntegerConvertTask task=new IntegerConvertTask();
    	Integer result=task.execute("100");
    	System.out.println(result);
	}
}



