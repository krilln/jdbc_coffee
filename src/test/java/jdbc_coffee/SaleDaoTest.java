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

import jdbc_coffee.dao.SaleDao;
import jdbc_coffee.dao.SaleDaoImpl;
import jdbc_coffee.dto.Sale;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SaleDaoTest {
   static SaleDao dao;

   @BeforeClass
   public static void setUpBeforeClass() throws Exception {
      System.out.println();
      LogUtil.prnLog("Start SaleDaoTest");
      dao = new SaleDaoImpl(); 
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
   public void test01SelectSaleByAll() {
      List<Sale> lists = dao.selectSaleByAll();
      LogUtil.prnLog(lists.toString());
      Assert.assertNotNull(lists);
   }

   @Test
   public void test02InsertSale() {
      try {
         Sale newSale = new Sale(5, "A003", 5000, 155, 20);
         int rowAffected = dao.insertSale(newSale);
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
   public void test05DeleteSale() {
      try {
         Sale delSale = new Sale();
         delSale.setCode("A003");
         int rowAffected = dao.deleteSale(delSale);
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
   public void test03UpdateSale() {
      try {
         Sale updateSale = new Sale(6, "A003", 200, 25, 5800);
         int rowAffected = dao.updateSale(updateSale);
         LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
         Assert.assertEquals(1, rowAffected);
      } catch (SQLException e) {
         LogUtil.prnLog(e);
      }
   }

   @Test
   public void test04SelectSaleByNo() {
      try {
         Sale selSale = new Sale();
         selSale.setCode("A003");
         Sale Sale = dao.selectSaleByNo(selSale);
         LogUtil.prnLog(String.format("%s - %s", Sale.getClass().getSimpleName(), Sale));
         Assert.assertNotNull(Sale);
      } catch (SQLException e) {
         LogUtil.prnLog(e);
      }
   }
}