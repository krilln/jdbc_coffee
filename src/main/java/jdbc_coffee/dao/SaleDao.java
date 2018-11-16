package jdbc_coffee.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc_coffee.dto.Sale;

public interface SaleDao {
	List<Sale> selectSaleByAll();
	int insertSale(Sale Sale) throws SQLException;
	
	int deleteSale(Sale Sale) throws SQLException;
	int updateSale(Sale Sale) throws SQLException;
	Sale selectSaleByNo(Sale Sale) throws SQLException;
}
