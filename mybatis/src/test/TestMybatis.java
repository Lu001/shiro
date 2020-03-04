package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pojo.Category;
import pojo.Order;
import pojo.OrderItem;
import pojo.Product;

public class TestMybatis {
	public static void main(String[] args) throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new 
				SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		//添加
		/*Category c1 = new Category();
        c1.setName("新增加的Category");
		session.insert("addCategory",c1);*/
		//删除
		/*Category c2 = new Category();
        c2.setId(7);
        session.delete("deleteCategory",c2);*/
		
		//更新
		/*Category c3 = new Category();
		c3.setName("更新");
		c3.setId(1);
		session.update("updateCategory",c3);*/
		//查找
		/*Category c = session.selectOne("getCategory", 1);
		System.out.println(c.getName());*/
		//listAll(session);
		
		/*List<Category> cs = session.selectList("listCategoryByName", "2");
		for (Category c : cs) {
			System.out.println(c.getName());
		}*/
		
		/*Map<String,Object> params = new HashMap<>();
        params.put("id", 3);
        params.put("name", "cat");
         
        List<Category> cs = session.selectList("listCategoryByIdAndName",params);
        for (Category c : cs) {
            System.out.println(c.getName());
        }*/
		/*List<Category> cs = session.selectList("listCategory");
        for (Category c : cs) {
            System.out.println(c);
            List<Product> ps = c.getProducts();
            for (Product p : ps) {
                System.out.println("\t"+p);
            }
        }*/
		/*List<Product> ps = session.selectList("listProduct");
        for (Product p : ps) {
            System.out.println(p+" 对应的分类是 \t "+ p.getCategory());
        }
		*/
		
		/*List<Order> os = session.selectList("listOrder");
        for (Order o : os) {
            System.out.println(o.getCode());
            List<OrderItem> ois= o.getOrderItems();
            for (OrderItem oi : ois) {
                System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(),oi.getProduct().getPrice(),oi.getNumber());
            }
        }*/
		listProduct(session);
		System.out.println("模糊查询");
		Map<String,Object> params = new HashMap<>();
        params.put("name","a");
        List<Product> ps2 = session.selectList("listProduct",params);
        for (Product p : ps2) {
            System.out.println(p);
        }      
         
		session.commit();
        session.close();
 
	}
	private static void listProduct(SqlSession session){
		List<Product> ps = session.selectList("listProduct");
		for (Product p : ps) {
			System.out.println(p);
		}
	}
	private static void listAll(SqlSession session){
		List<Category> cs = session.selectList("listCategory");
		for (Category c : cs) {
			System.out.println(c.getName());
		}
	}
}
