import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.MessageService;
import service.MessageService2;
import service.MessageServiceImpl2;


/**
 *
 */
public class AppMain {

    public static void main(String[] args) {
        String classPath = "classpath:application.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(classPath);

        System.out.println("context 启动成功");

        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        MessageService messageService = context.getBean(MessageService.class);
        // 这句将输出: hello ClassPathXmlApplicationContext
        System.out.println(messageService.getMessage());

        System.out.println("-----------");

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MessageServiceImpl2.class);
        MessageService2 messageService2 = annotationConfigApplicationContext.getBean(MessageService2.class);

        // 这句将输出: hello world
        System.out.println(messageService2.getMessage());
    }
}
