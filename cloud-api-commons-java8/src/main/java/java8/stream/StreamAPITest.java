package java8.stream;


import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * StreamAPI 学习： 用户信息业务逻辑类
 * 版权声明：本文为CSDN博主「pan_junbiao」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：
 * See  <a>https://blog.csdn.net/pan_junbiao/article/details/105913518</a>
 */
public class StreamAPITest {

    public static void main(String[] args) {
        List<Integer> integerList = List.of(1, 2, 3);
        for (Integer integer : integerList) {

        }
    }

    /**
     * 构建用户列表
     */
    public static final List<Employee> USER_LIST = StreamAPITest.getEmployeeList();


    /**
     * 使用 groupingBy() 多级分组
     * groupingBy 可以接受一个第二参数实现多级分组。
     */
    @Test
    public void multGroupingByTest() {

        //根据部门和性别对用户列表进行分组
        Map<String, Map<String, List<Employee>>> userMap = USER_LIST.stream()
                .collect(Collectors.groupingBy(Employee::getDept, Collectors.groupingBy(Employee::getGender)));

        //遍历分组后的结果
        userMap.forEach((key1, map) -> {
            System.out.println(key1 + "：");
            map.forEach((key2, user) -> {
                System.out.println(key2 + "：");
                user.forEach(System.out::println);
            });
            System.out.println("--------------------------------------------------------------------------");
        });
    }

    /**
     * 使用 groupingBy() 分组
     */
    @Test
    public void groupingByTest() {

        //根据部门对用户列表进行分组
        Map<String, List<Employee>> userMap = USER_LIST.stream().collect(Collectors.groupingBy(Employee::getDept));

        //遍历分组后的结果
        userMap.forEach((key, value) -> {
            System.out.println(key + "：");
            value.forEach(System.out::println);
            System.out.println("--------------------------------------------------------------------------");
        });
    }

    /**
     * 使用 sorted() 排序
     */
    @Test
    public void sortedTest() {

        //根据年龄排序（升序）
        //List user2List = userList.stream().sorted((u1, u2) -> u1.getAge() - u2.getAge()).collect(Collectors.toList());
        //推荐：
        List userList2 = USER_LIST.stream().sorted(Comparator.comparingInt(Employee::getAge)).collect(Collectors.toList());
        //降序：userList = userList.stream().sorted(Comparator.comparingInt(User::getAge).reversed()).collect(Collectors.toList());

        //遍历用户列表
        userList2.forEach(System.out::println);
    }

    /**
     * BigDecimal类型的统计
     */
    @Test
    public void bigDecimalTest() {

        //最高薪资
        BigDecimal maxSalary = USER_LIST.stream().map(Employee::getSalary).max((x1, x2) -> x1.compareTo(x2)).get();

        //最低薪资
        BigDecimal minSalary = USER_LIST.stream().map(Employee::getSalary).min((x1, x2) -> x1.compareTo(x2)).get();

        //薪资总和
        BigDecimal sumSalary = USER_LIST.stream().map(Employee::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);

        //平均薪资
        BigDecimal avgSalary = USER_LIST.stream().map(Employee::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(USER_LIST.size()), 2, BigDecimal.ROUND_HALF_UP);

        //打印统计结果
        System.out.println("最高薪资：" + maxSalary + "元");
        System.out.println("最低薪资：" + minSalary + "元");
        System.out.println("薪资总和：" + sumSalary + "元");
        System.out.println("平均薪资：" + avgSalary + "元");
    }

    /**
     * 使用 summarizingInt 统计
     * 使用 IntSummaryStatistics 统计：最大值、最小值、总和、平均值、总数。
     */
    @Test
    public void summarizingIntTest() {

        //获取IntSummaryStatistics对象
        IntSummaryStatistics ageStatistics = USER_LIST.stream().collect(Collectors.summarizingInt(Employee::getAge));

        //统计：最大值、最小值、总和、平均值、总数
        System.out.println("最大年龄：" + ageStatistics.getMax());
        System.out.println("最小年龄：" + ageStatistics.getMin());
        System.out.println("年龄总和：" + ageStatistics.getSum());
        System.out.println("平均年龄：" + ageStatistics.getAverage());
        System.out.println("员工总数：" + ageStatistics.getCount());
    }


    /**
     * 使用 counting() 或 count() 对列表数据统计
     */
    @Test
    public void countTest() {

        //统计研发部的人数，使用 counting()方法进行统计
        Long departCount = USER_LIST.stream().filter(user -> user.getDept().equals("研发部")).collect(Collectors.counting());

        //统计30岁以上的人数，使用 count()方法进行统计（推荐）
        Long ageCount = USER_LIST.stream().filter(user -> user.getAge() >= 30).count();

        //统计薪资大于11500元的人数
        Long salaryCount = USER_LIST.stream().filter(user -> user.getSalary().compareTo(BigDecimal.valueOf(11500)) == 1).count();

        /*summingInt()、summingLong()、summingDouble()用于计算总和，需要一个函数参数*/
        //计算年龄总和
        int sumAge = USER_LIST.stream().collect(Collectors.summingInt(Employee::getAge));
        /** averagingInt()、averagingLong()、averagingDouble()用于计算平均值。*/
        //计算平均年龄
        double aveAge = USER_LIST.stream().collect(Collectors.averagingDouble(Employee::getAge));

        //打印结果
        System.out.println("研发部的人数：" + departCount + "人");
        System.out.println("30岁以上的人数：" + ageCount + "人");
        System.out.println("薪资大于1500元的人数：" + salaryCount + "人");
        System.out.println("年龄总和：" + sumAge);
        System.out.println("平均年龄：" + aveAge);
    }

    /** mapToInt(T -> int) 、mapToDouble(T -> double) 、mapToLong(T -> long)
     int sumVal = userList.stream().map(User::getAge).reduce(0,Integer::sum)；
     计算元素总和的方法其中暗含了装箱成本，map(User::getAge) 方法过后流变成了 Stream 类型，
     而每个 Integer 都要拆箱成一个原始类型再进行 sum 方法求和，这样大大影响了效率。
     针对这个问题 Java 8 有良心地引入了数值流 IntStream, DoubleStream, LongStream，这种流中的元素都是原始数据类型，分别是 int，double，long。
     流转换为数值流：

     mapToInt(T -> int) : return IntStream
     mapToDouble(T -> double) : return DoubleStream
     mapToLong(T -> long) : return LongStream
     【示例】使用 mapToInt() 求用户列表中年龄的最大值、最小值、总和、平均值。
     */
    /**
     * 使用 mapToInt() 方法
     */
    @Test
    public void mapToIntTest() {
        //用户列表中年龄的最大值、最小值、总和、平均值
        int maxVal = USER_LIST.stream().mapToInt(Employee::getAge).max().getAsInt();
        int minVal = USER_LIST.stream().mapToInt(Employee::getAge).min().getAsInt();
        int sumVal = USER_LIST.stream().mapToInt(Employee::getAge).sum();
        double aveVal = USER_LIST.stream().mapToInt(Employee::getAge).average().getAsDouble();

        //打印结果
        System.out.println("最大年龄：" + maxVal);
        System.out.println("最小年龄：" + minVal);
        System.out.println("年龄总和：" + sumVal);
        System.out.println("平均年龄：" + aveVal);
    }
     /*3、统计方法
     3.1 reduce((T, T) -> T) 和 reduce(T, (T, T) -> T)
    使用 reduce((T, T) -> T) 和 reduce(T, (T, T) -> T) 用于组合流中的元素，如求和，求积，求最大值等。

    【示例】使用 reduce() 求用户列表中年龄的最大值、最小值、总和。
    ————————————————**/

    /**
     * 使用 reduce() 方法
     */
    @Test
    public void reduceTest() {

        //用户列表中年龄的最大值、最小值、总和
        Optional<Integer> reduce = USER_LIST.stream().map(Employee::getAge).reduce(Integer::max);
        int maxVal = reduce.get();
        int minVal = USER_LIST.stream().map(Employee::getAge).reduce(Integer::min).get();
        int sumVal = USER_LIST.stream().map(Employee::getAge).reduce(0, Integer::sum);

        //打印结果
        System.out.println("最大年龄：" + maxVal);
        System.out.println("最小年龄：" + minVal);
        System.out.println("年龄总和：" + sumVal);
    }


    /**2、判断方法
     2.1 anyMatch(T -> boolean)
     使用 anyMatch(T -> boolean) 判断流中是否有一个元素匹配给定的 T -> boolean 条件。

     2.2 allMatch(T -> boolean)
     使用 allMatch(T -> boolean) 判断流中是否所有元素都匹配给定的 T -> boolean 条件。

     2.3 noneMatch(T -> boolean)
     使用 noneMatch(T -> boolean) 流中是否没有元素匹配给定的 T -> boolean 条件。
     */
    /**
     * 使用 anyMatch()、allMatch()、noneMatch() 进行判断
     */
    @Test
    public void matchTest() {

        //判断用户列表中是否存在名称为“小王”的数据
        boolean result1 = USER_LIST.stream().anyMatch(user -> user.getUserName().equals("小王") || user.getDept().equals("研发部"));

        //判断用户名称是否都包含“小”字段
        boolean result2 = USER_LIST.stream().allMatch(user -> user.getUserName().contains("小"));

        //判断用户名称是否存在不包含“小”字段
        boolean result3 = USER_LIST.stream().noneMatch(user -> user.getUserName().contains("小"));

        //打印结果
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }

    /**
     * limit(long n)方法用于返回前n条数据
     * skip(long n)方法用于跳过前n条数据
     */
    @Test
    public void limitAndSkipTest() {

        //获取用户列表，要求跳过第1条数据后的前3条数据
        List<Employee> skipList = USER_LIST.stream()
                .skip(1)
                .limit(3)
                .collect(Collectors.toList());

        //遍历用户列表
        skipList.forEach(System.out::println);
    }

    /**
     * 使用distinct()去除重复数据
     */
    @Test
    public void distinctTest() {

        //获取部门列表，并去除重复数据
        List<String> departmentList = USER_LIST.stream().map(Employee::getDept).distinct().collect(Collectors.toList());

        //遍历部门列表
        departmentList.forEach(System.out::println);
    }

    /**
     * 使用flatMap()将流中的每一个元素连接成为一个流
     */
    @Test
    public void flatMapTest() {
        //创建城市
        List<String> cityList = new ArrayList<String>();
        cityList.add("北京；上海；深圳；");
        cityList.add("广州；武汉；杭州；");

        //分隔城市列表，使用 flatMap() 将流中的每一个元素连接成为一个流。
        cityList = cityList.stream()
                .map(city -> city.split("；"))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        String[] strings = cityList.toArray(new String[cityList.size()]);
    System.out.println(strings.toString());
        System.out.println(cityList);

    }

    /**
     * 使用 map() 将流中的每一个元素 T 映射为 R（类似类型转换）。
     * 使用 flatMap() 将流中的每一个元素 T 映射为一个流，再把每一个流连接成为一个流。
     * 使用map()获取列元素
     */
    @Test
    public void mapTest() {
        //获取用户列表

        //获取用户名称列表
        List<String> nameList = USER_LIST.stream().map(Employee::getUserName).collect(Collectors.toList());
        //或者：List<String> nameList = USER_LIST.stream().map(user -> user.getName()).collect(Collectors.toList());

        //遍历名称列表
        nameList.forEach(System.out::println);

        //数组类型
        String[] nameArray = USER_LIST.stream().map(Employee::getUserName).collect(Collectors.toList()).toArray(new String[USER_LIST.size()]);
        System.out.println(Arrays.toString(nameArray));
    }

    /**
     * 使用findAny()获取第一条数据
     * 注意：findFirst() 和 findAny() 都是获取列表中的第一条数据，
     * 但是findAny()操作，返回的元素是不确定的，
     * 对于同一个列表多次调用findAny()有可能会返回不同的值。使用findAny()是为了更高效的性能。
     * 如果是数据较少，串行地情况下，一般会返回第一个结果，如果是并行（parallelStream并行流）的情况，
     * 那就不能确保是第一个。
     * 例如：使用parallelStream并行流，findAny() 返回的就不一定是第一条数据。
     */
    @Test
    public void findAnytTest() {

        //获取用户名称为 "小王" 的用户信息，如果没有找到则返回null
        Employee u = USER_LIST.stream().filter(user -> "小王".equals(user.getUserName())).findAny().orElse(null);
        Employee u2 = USER_LIST.stream().filter(user -> "小王".equals(user.getUserName())).findFirst().orElse(null);


        //打印用户信息
        System.out.println(u);
        System.out.println(u2);
    }

    /**
     * 使用filter()过滤列表信息
     */
    @Test
    public void filterTest() {

        //获取部门为“研发部”的用户列表
        List<Employee> userList = StreamAPITest.USER_LIST.stream().filter(user -> "研发部".equals(user.getDept()))
                .collect(Collectors.toList());

        //遍历用户列表
        userList.forEach(System.out::println);
    }

    /**
     * 使用forEach()遍历列表信息
     */
    @Test
    public void forEachTest() {
        //遍历用户列表
        USER_LIST.forEach(System.out::println);
        // 相当于USER_LIST.forEach(user -> {System.out.println(user);});
    }

    /**
     * 获取用户列表
     */
    public static List<Employee> getEmployeeList() {
        List<Employee> userList = new ArrayList<Employee>();
        userList.add(new Employee(1, "小王", "男", 32, "研发部", BigDecimal.valueOf(11600), Employee.Status.BUSY));
        userList.add(new Employee(2, "小王", "男", 33, "财务部", BigDecimal.valueOf(11600), Employee.Status.FREE));
        userList.add(new Employee(3, "小张", "男", 30, "财务部", BigDecimal.valueOf(11800), Employee.Status.BUSY));
        userList.add(new Employee(4, "小李", "女", 20, "人事部", BigDecimal.valueOf(11700), Employee.Status.VOCATION));
        userList.add(new Employee(5, "小吴", "男", 38, "研发部", BigDecimal.valueOf(11500), Employee.Status.BUSY));
        userList.add(new Employee(6, "小崔", "女", 25, "财务部", BigDecimal.valueOf(11200), Employee.Status.VOCATION));
        userList.add(new Employee(7, "小崔", "女", 25, "财务部", BigDecimal.valueOf(11200), Employee.Status.FREE));
        return userList;
    }
}
