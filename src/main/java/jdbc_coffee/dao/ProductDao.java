package jdbc_coffee.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc_coffee.dto.Product;
import jdbc_coffee.dto.Sale;

public interface ProductDao {
	List<Product> selectProductByAll();
	int insertProduct(Product Product) throws SQLException;
	
	int deleteProduct(Product Product) throws SQLException;
	int updateProduct(Product Product) throws SQLException;
	Product selectProductByNo(Product Product) throws SQLException;
}
