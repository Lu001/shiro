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
  
public class TestTrans {
  
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
        Category c1 = new Category();
        c1.setName("长度够短的名称");
        mapper.add(c1);
         
        Category c2 = new Category();
        c2.setName("超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称");
        mapper.add(c2);       
 
        listAll(mapper);
             
        session.commit();
        session.close();
  
    }
 
    private static void update(CategoryMapper mapper) {
        Category c= mapper.get(14);
        c.setName("修改了的Category名稱");
        mapper.update(c);
        listAll(mapper);
    }
 
    private static void get(CategoryMapper mapper) {
        Category c= mapper.get(14);
        System.out.println(c.getName());
    }
 
    private static void delete(CategoryMapper mapper) {
        mapper.delete(13);
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