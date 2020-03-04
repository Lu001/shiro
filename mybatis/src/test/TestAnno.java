package test;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
 
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
 
import mapper.CategoryMapper;
import pojo.Category;
import pojo.Product;
   
public class TestAnno {
   
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);
  
//        add(mapper);
//        delete(mapper);
//        get(mapper);
//        update(mapper);
        
        //listAll1(mapper);
        //annotationWay(session); 
        session.commit();
        session.close();
   
    }
   /* private static void annotationWay(SqlSession session) {
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);
         
        List<Category>  cs =mapper.listByPage(0, 5);
        for (Category c : cs) {
            System.out.println(c);
        }
    }*/
    
    private static void listAll1(CategoryMapper mapper) {
        List<Category> cs = mapper.list();
        for (Category c : cs) {
            System.out.println(c.getName());
            List<Product> ps = c.getProducts();
            for (Product p : ps) {
                System.out.println("\t"+p.getName());
            }
        }
    }
    private static void update(CategoryMapper mapper) {
        Category c= mapper.get(8);
        c.setName("修改了的Category名稱");
        mapper.update(c);
        listAll(mapper);
    }
  
    private static void get(CategoryMapper mapper) {
        Category c= mapper.get(8);
        System.out.println(c.getName());
    }
  
    private static void delete(CategoryMapper mapper) {
        mapper.delete(2);
        listAll(mapper);
    }
  
    private static void add(CategoryMapper mapper) {
        Category c = new Category();
        c.setName("新增加的Category");
        mapper.add(c);
        listAll(mapper);
    }
   
    private static void listAll(CategoryMapper mapper) {
        List<Category> cs = mapper.list();
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }
}