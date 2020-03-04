package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;

import pojo.Category;

public interface CategoryMapper {
	public List<Category> listByPage(@Param("start") int start, @Param("count")int count);
	
	@Select(" select * from category_ ")
    @Results({ 
                @Result(property = "id", column = "id"),
                @Result(property = "products", javaType = List.class, column = "id", 
                many = @Many(select = "mapper.ProductMapper.listByCategory") )
            })
	public List<Category> list(); 
	
	@Insert(" insert into category_ ( name ) values (#{name}) ") 
    public int add(Category category); 
        
    @Delete(" delete from category_ where id= #{id} ") 
    public void delete(int id); 
        
    @Select("select * from category_ where id= #{id} ") 
    public Category get(int id); 
      
    @Update("update category_ set name=#{name} where id=#{id} ") 
    public int update(Category category);  
  
    
}
