package javase.memory;
import java.util.ArrayList;
import java.util.List;
//JVM参数
//1)最大堆-Xmx20m
//2)最小堆-Xms20m
//3)年轻代-Xmn8m
//4)输出GC详细信息-XX:+PrintGCDetails
//4)输出GC时间戳-XX:+PrintGCTimeStamps
//-Xmx5m -Xms5m
//-XX:+PrintGCDetails
//-XX:+PrintGCTimeStamps
public class TestHeapMemory01 {
	public static void main(String[] args) {
//	  Integer array[]=new Integer[Integer.MAX_VALUE];
		List<byte[]> list=new ArrayList<>();

		list.add(new byte[1024*1024]);//1M 1兆 1Mb = 1024 K  1K =1024 字节

		list.add(new byte[1024*1024]);
		list.add(new byte[1024*1024]);
		list.add(new byte[1024*1024]);
		list.add(new byte[1024*1024]);
	}
}
