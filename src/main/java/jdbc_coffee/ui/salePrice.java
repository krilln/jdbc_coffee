package jdbc_coffee.ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class salePrice extends JPanel {
	private JTable table;
	private JTable table_1;

	/**
	 * Create the panel.
	 */
	public salePrice() {
		
		initComponents();
	}
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
					"순위", "제품코드", "제품명", "제품단가", "판매수량", "공급가액", "부가세액", "부가세액", "판매금액", "마진액"
			}
		));
		add(table);
		
		JLabel lblNewLabel = new JLabel("판매금액순위");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);
		
		table_1 = new JTable();
		table_1.setCellSelectionEnabled(true);
		table_1.setToolTipText("");
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		add(table_1, BorderLayout.SOUTH);
	}

}
