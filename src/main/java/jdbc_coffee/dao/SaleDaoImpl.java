package jdbc_coffee.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jdbc_coffee.ConnectionProvide;
import jdbc_coffee.LogUtil;
import jdbc_coffee.dto.Product;
import jdbc_coffee.dto.Sale;
import jdbc_coffee.dto.SaleDetail;




public class SaleDaoImpl implements SaleDao {
	Logger LOG = LogManager.getLogger();

	@Override
	public List<Sale> selectSaleByAll() {
		List<Sale> list = new ArrayList<>();
		String sql = "select no, code, price, saleCnt, marginRate from sale";
		try (Connection conn = ConnectionProvide.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			;
			LOG.debug(pstmt);
			while (rs.next()) {
				list.add(getSale(rs));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return list;

	}

	private Sale getSale(ResultSet rs) throws SQLException {
		int no = rs.getInt("no");
		Product code = new Product(rs.getString("code"));
		int price = rs.getInt("price");
		int saleCnt = rs.getInt("saleCnt");
		int marginRate = rs.getInt("marginRate");
		return new Sale(no, code, price, saleCnt, marginRate);
	}

	@Override
	public int insertSale(Sale Sale) throws SQLException {
		String sql = "insert into sale values(?,?,?,?,?)";
		int res = 0;

		try (Connection conn = ConnectionProvide.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, Sale.getNo());
			pstmt.setString(2, Sale.getCode().getCode());
			pstmt.setInt(3, Sale.getPrice());
			pstmt.setInt(4, Sale.getSaleCnt());
			pstmt.setInt(5, Sale.getMarginRate());
			LOG.debug(pstmt);
			res = pstmt.executeUpdate();
		}

		return res;
	}


	@Override
	public int deleteSale(Sale Sale) throws SQLException {
		String sql = "delete from sale where code=?";
		int res = 0;
		try (Connection conn = ConnectionProvide.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, Sale.getCode().getCode());
			LOG.debug(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int updateSale(Sale Sale) throws SQLException {
		String sql = "update sale set no=?,price=?,saleCnt=?,marginRate=? where code=?";
		int res = 0;
		try (Connection conn = ConnectionProvide.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, Sale.getNo());
			pstmt.setInt(2, Sale.getPrice());
			pstmt.setInt(3, Sale.getSaleCnt());
			pstmt.setInt(4, Sale.getMarginRate());
			pstmt.setString(5, Sale.getCode().getCode());
			LOG.debug(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public Sale selectSaleByNo(Sale Sale) throws SQLException {
		Sale selSale = null;
		String sql = "select no, code, price, saleCnt, marginRate from sale where code = ?";

		try (Connection conn = ConnectionProvide.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, Sale.getCode().getCode());
			LOG.debug(pstmt);

			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					selSale = getSale(rs);
				}
			}

		}
		return selSale; 
	}

	/*@Override
	public List<Sale> saleRankList() {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<Sale> selectSaleRank(boolean isSale) throws SQLException {
		
		LogUtil.prnLog("selectSaleRank");
		List<Sale> list = new ArrayList<>();
		String sql = "{call rank_salePrice(?)}";
		try(Connection conn = ConnectionProvide.getConnection();
				CallableStatement cs = conn.prepareCall(sql);){
			cs.setBoolean(1, isSale);
			LogUtil.prnLog(cs.toString());
			try(ResultSet rs = cs.executeQuery()){
				while(rs.next()) {
					list.add(getSaleDetail(rs));
				}
				
			}

		}
		LogUtil.prnLog("selectSaleRank" + list.size());
		return list;

}

	private Sale getSaleDetail(ResultSet rs) throws SQLException {
		int no = rs.getInt("no");
//		String code = rs.getString("code");
		Product product = new Product(rs.getString("code"), rs.getString("name"));
		int price = rs.getInt("price");
		int saleCnt = rs.getInt("saleCnt");
		int marginRate = rs.getInt("marginRate");
		int supplyprice = rs.getInt("supply");
		int addtax = rs.getInt("addtax");
		int saleprice = rs.getInt("saleprice");
		int marginprice = rs.getInt("marginprice");
		int rank = rs.getInt("순위");
		
		SaleDetail detail = new SaleDetail(supplyprice, addtax, saleprice, marginprice, rank);
		Sale sale = new Sale(no, product, price, saleCnt, marginRate, detail);
		LogUtil.prnLog(sale.toString());
		return sale;
	}
	}
