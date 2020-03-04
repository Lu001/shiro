package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.Category;
import pojo.Product;
import service.ProductService;
/**
 * IOC控制反转
 * 1通过Spring创建对象 依赖注入直接使用拿到对象的属性
 * 2如何实现？通过配置文件找到类的位置，去创建类对象 读取配置文件的对象name属性值
 * 注解方式 
 * @author Administrator
 *
 */
public class TestSpring {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"applicationContext.xml"});
		//IOC DI
		/*Category c = (Category)context.getBean("c");
		System.out.println(c.getName());
		Product p = (Product) context.getBean("p");
		System.out.println(p.getGory().getName());*/
		//AOP
		ProductService s = (ProductService)context.getBean("s");
		s.doSomeService();
	}
}
