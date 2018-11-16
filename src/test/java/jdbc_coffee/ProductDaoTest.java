package jdbc_coffee;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_coffee.dao.ProductDao;
import jdbc_coffee.dao.ProductDaoImpl;
import jdbc_coffee.dto.Product;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest {
   static ProductDao dao;

   @BeforeClass
   public static void setUpBeforeClass() throws Exception {
      System.out.println();
      LogUtil.prnLog("Start ProductDaoTest");
      dao = new ProductDaoImpl();
   }

   @AfterClass
   public static void tearDownAfterClass() throws Exception {
      System.out.println();
      LogUtil.prnLog("End SaleDaoTest");
      dao = null;
   }

   @Before
   public void setUp() throws Exception {
      System.out.println();
   }

   @Test
   public void test01SelectProductByAll() {
      List<Product> lists = dao.selectProductByAll();
      LogUtil.prnLog(lists.toString());
      Assert.assertNotNull(lists);
   }

   @Test
   public void test02InsertProduct() {
      try {
    	  Product newProduct = new Product("A005", "얼그레이");
         int rowAffected = dao.insertProduct(newProduct);
         LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
         Assert.assertEquals(1, rowAffected);
      } catch (SQLException e) {
         if (e.getErrorCode() == 1062) {
            LogUtil.prnLog("해당 상품존재");
         } else {
            LogUtil.prnLog(e);
         }
      }
   }

   @Test
   public void test05DeleteProduct() {
      try {
    	  Product delProduct = new Product();
         delProduct.setCode("A005");
         int rowAffected = dao.deleteProduct(delProduct);
         LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
         Assert.assertEquals(1, rowAffected);
      } catch (SQLException e) {
         if (e.getErrorCode() == 1451) {
            LogUtil.prnLog("해당상품은 존재합니다.");
         } else {
            LogUtil.prnLog(e);
         }
      }
   }

   @Test
   public void test03UpdateProduct() {
      try {
    	 Product updateProduct = new Product("A005", "얼그레이 백작");
         int rowAffected = dao.updateProduct(updateProduct);
         LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
         Assert.assertEquals(1, rowAffected);
      } catch (SQLException e) {
         LogUtil.prnLog(e);
      }
   }

   @Test
   public void test04SelectProductByNo() {
      try {
    	  Product selProduct = new Product();
         selProduct.setCode("A005");
         Product Product = dao.selectProductByNo(selProduct);
         LogUtil.prnLog(String.format("%s - %s", Product.getClass().getSimpleName(), Product));
         Assert.assertNotNull(Product);
      } catch (SQLException e) {
         LogUtil.prnLog(e);
      }
   }
}