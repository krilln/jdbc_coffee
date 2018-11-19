package jdbc_coffee.service;

import java.sql.SQLException;
import java.util.List;

import jdbc_coffee.dao.SaleDao;
import jdbc_coffee.dao.SaleDaoImpl;
import jdbc_coffee.dto.Sale;



public class OutputService {
	private SaleDao saleDao;

	public OutputService() {
		saleDao = new SaleDaoImpl();
	}
	
	public List<Sale> outputOrder(boolean isSale) throws SQLException {
		return saleDao.selectSaleRank(isSale);
	}
}
