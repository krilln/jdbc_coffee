package jdbc_coffee.ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import jdbc_coffee.LogUtil;
import jdbc_coffee.dto.Sale;

public class RankListPanel extends JPanel {
	private JTable table;
	//리스트 만들기
	private List<Sale> list;
	
	public void setList(List<Sale> list) {
		this.list = list;
	}
	
	/**
	 * Create the panel.
	 * @param isSale 
	 */
	public RankListPanel() {

		initComponents();
	}
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	//tableCellAlignment, tableSetWidth 추상메서드로 하고 setAlignWidth만 조절하면 됨.
	private void setAlignWidth() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2);
		tableCellAlignment(SwingConstants.RIGHT, 3, 4, 5, 6, 7, 8, 9);
		tableSetWidth(100, 150, 200, 150, 150, 200, 150, 200, 100, 150);
		
	}
	
	//정렬
	private void tableCellAlignment(int align, int...idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		TableColumnModel tcm = table.getColumnModel();
		for(int i = 0 ; i < idx.length ; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}
	
	//폭
	private void tableSetWidth(int...width) {
		TableColumnModel tcm = table.getColumnModel();
		for(int i = 0 ; i < width.length ; i++) {
			tcm.getColumn(i).setPreferredWidth(width[i]);
		}
	}

	//loadDatas
	public void loadDatas() {
		table.setModel(new DefaultTableModel(
				getDatas(),
				getColumnNames()
			));
		
		//정렬, 폭 => 메서드 만들고 가변배열로 처리
				setAlignWidth();
	}
	
	//getDatas
	private Object[][] getDatas() {
		//2차원 배열 만들기
		Object[][] datas = new Object[list.size()+1][];
		for(int i = 0 ; i < list.size() ; i++) {
			datas[i] = getSaleRow(list.get(i));
		}
		datas[list.size()] = getTotal();	
		return datas;
	}

	private Object[] getSaleRow(Sale sale) {
	
		return new Object[] {
				sale.getDetail().getRank(), //순위
				sale.getCode().getCode(), 	//제품코드
				sale.getCode().getName(), //제품명
				String.format("%,d", sale.getPrice()), //제품단가
				String.format("%,d", sale.getSaleCnt()), //판매수량
				String.format("%,d", sale.getDetail().getSupplyprice()),	//공급가액
				String.format("%,d", sale.getDetail().getAddtax()), //부가세액
				String.format("%,d", sale.getDetail().getSaleprice()), //판매금액
				sale.getMarginRate() + "%", //마진율
				String.format("%,d", sale.getDetail().getMarginprice())	//마진액
		};
	}

	private String[] getColumnNames() {
		return new String[] {
					"순위", "제품코드", "제품명", "제품단가", "판매수량", "공급가액", "부가세액", "판매금액", "마진율", "마진액"
				};
	}
	
	//공급가액
	public Object[] getTotal() {
		LogUtil.prnLog("getTotal()");
		int totalSupplyPrice = 0;	//공급가액 합
		int totalAddTax = 0;	//부가세액 합
		int totalSalePrice = 0;	//판매금액 합
		int totalMarginPrice = 0;	//마진액 합
		for(Sale s : list) {
			totalSupplyPrice += s.getDetail().getSupplyprice();
			totalAddTax += s.getDetail().getAddtax();
			totalSalePrice += s.getDetail().getSaleprice();
			totalMarginPrice += s.getDetail().getMarginprice();
//			LogUtil.prnLog(s.toString());
		}
		LogUtil.prnLog(totalSupplyPrice + "");
		return new Object[] {"합계", "", "", "", "", String.format("%,d", totalSupplyPrice), 
							String.format("%,d", totalAddTax), String.format("%,d", totalSalePrice),"",
							String.format("%,d", totalMarginPrice)};
	}

}
