package jdbc_coffee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jdbc_coffee.ConnectionProvide;
import jdbc_coffee.dto.Product;




public class ProductDaoImpl implements ProductDao {
	Logger LOG = LogManager.getLogger();

	@Override
	public List<Product> selectProductByAll() {
		List<Product> list = new ArrayList<>();
		String sql = "select code, name from product";
		try (Connection conn = ConnectionProvide.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			;
			LOG.debug(pstmt);
			while (rs.next()) {
				list.add(getProduct(rs));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return list;

	}

	private Product getProduct(ResultSet rs) throws SQLException {
		String code = rs.getString("code");
		String name = rs.getString("name");
		return new Product(code, name);
	}

	@Override
	public int insertProduct(Product Product) throws SQLException {
		String sql = "insert into product values(?,?)";
		int res = 0;

		try (Connection conn = ConnectionProvide.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setString(1, Product.getCode());
			pstmt.setString(2, Product.getName());
			LOG.debug(pstmt);
			res = pstmt.executeUpdate();
		}

		return res;
	}


	@Override
	public int deleteProduct(Product Product) throws SQLException {
		String sql = "delete from Product where code=?";
		int res = 0;
		try (Connection conn = ConnectionProvide.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, Product.getCode());
			LOG.debug(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int updateProduct(Product Product) throws SQLException {
		String sql = "update Product set name=? where code=?";
		int res = 0;
		try (Connection conn = ConnectionProvide.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, Product.getName());
			pstmt.setString(2, Product.getCode());
			LOG.debug(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public Product selectProductByNo(Product Product) throws SQLException {
		Product selProduct = null;
		String sql = "select code,name from Product where code = ?";

		try (Connection conn = ConnectionProvide.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, Product.getCode());
			LOG.debug(pstmt);

			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					selProduct = getProduct(rs);
				}
			}

		}
		return selProduct;
	}

}
