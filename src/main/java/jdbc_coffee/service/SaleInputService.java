package jdbc_coffee.service;

import java.sql.SQLException;

import jdbc_coffee.dao.ProductDao;
import jdbc_coffee.dao.ProductDaoImpl;
import jdbc_coffee.dao.SaleDao;
import jdbc_coffee.dao.SaleDaoImpl;
import jdbc_coffee.dto.Product;
import jdbc_coffee.dto.Sale;

public class SaleInputService {
	private SaleDao saleDao;
	private ProductDao productDao;
	
	public SaleInputService() {
		productDao = new ProductDaoImpl();
		saleDao = new SaleDaoImpl();
		
	}
	public Product searchProduct(Product Product) throws SQLException {
		return productDao.selectProductByNo(Product);
	}
	
	public int registerSale(Sale Sale) throws SQLException {
		return saleDao.insertSale(Sale);
	}
}
