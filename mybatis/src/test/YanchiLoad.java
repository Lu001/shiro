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
   
public class YanchiLoad {
   
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);
 
        for (int i = 0; i < 100; i++) {
            Category c = new Category();
            c.setName("category name " + i);
            session.insert("addCategory", c);
        }
        
        List<Category> cs = mapper.list();
        for (Category c : cs) {
            System.out.println(c.getName());
//          List<Product> ps = c.getProducts();
//          for (Product p : ps) {
//              System.out.println("\t"+p.getName());
//          }
        }
              
        session.commit();
        session.close();
   
    }
}