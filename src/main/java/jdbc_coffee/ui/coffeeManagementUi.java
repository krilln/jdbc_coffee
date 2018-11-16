package jdbc_coffee.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import jdbc_coffee.dto.Product;
import jdbc_coffee.dto.Sale;
import jdbc_coffee.service.SaleInputService;


public class coffeeManagementUi extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfCode;
	private JTextField tfName;
	private JTextField tfPrice;
	private JTextField tfCnt;
	private JTextField tfMarginRate;
	private JButton btnInsert;
	private JButton btnPrint1;
	private JButton btnPrint2;
	private SaleInputService service;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					coffeeManagementUi frame = new coffeeManagementUi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public coffeeManagementUi() {
		service = new SaleInputService();
		initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 10));
		
		JPanel pContent = new JPanel();
		contentPane.add(pContent, BorderLayout.CENTER);
		pContent.setLayout(new GridLayout(0, 4, 10, 10));
		
		JLabel lblCode = new JLabel("제품코드 : ");
		lblCode.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblCode);
		
		tfCode = new JTextField();
		pContent.add(tfCode);
		tfCode.setColumns(10);
		
		JLabel lblName = new JLabel("제품명 : ");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblName);
		
		tfName = new JTextField();
		pContent.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblPrice = new JLabel("제품단가 : ");
		lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblPrice);
		
		tfPrice = new JTextField();
		pContent.add(tfPrice);
		tfPrice.setColumns(10);
		
		JLabel lblBlank1 = new JLabel("");
		pContent.add(lblBlank1);
		
		JLabel lblBlank2 = new JLabel("");
		pContent.add(lblBlank2);
		
		JLabel lblCnt = new JLabel("판매수량 : ");
		lblCnt.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblCnt);
		
		tfCnt = new JTextField();
		pContent.add(tfCnt);
		tfCnt.setColumns(10);
		
		JLabel lblBlank3 = new JLabel("");
		pContent.add(lblBlank3);
		
		JLabel lblBlank4 = new JLabel("");
		pContent.add(lblBlank4);
		
		JLabel lblMarginRate = new JLabel("마 진 율 : ");
		lblMarginRate.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblMarginRate);
		
		tfMarginRate = new JTextField();
		pContent.add(tfMarginRate);
		tfMarginRate.setColumns(10);
		
		JPanel pbtnWrap = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pbtnWrap.getLayout();
		flowLayout.setHgap(20);
		contentPane.add(pbtnWrap, BorderLayout.SOUTH);
		
		btnInsert = new JButton("입력");
		btnInsert.addActionListener(this);
		pbtnWrap.add(btnInsert);
		
		btnPrint1 = new JButton("출력1");
		btnPrint1.addActionListener(this);
		pbtnWrap.add(btnPrint1);
		
		btnPrint2 = new JButton("출력2");
		btnPrint2.addActionListener(this);
		pbtnWrap.add(btnPrint2);
	}
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPrint2) {
			do_btnPrint2_actionPerformed(e);
		}
		if (e.getSource() == btnPrint1) {
			do_btnPrint1_actionPerformed(e);
		}
		if (e.getSource() == btnInsert) {
			do_btnInsert_actionPerformed(e);
		}
	}
	protected void do_btnInsert_actionPerformed(ActionEvent e) {
		Sale sale = getSale();
		int res = 0;
		System.out.println(service);
		try {
			res = service.registerSale(sale);
			/*service.searchProduct(sale);*/
			if(res == 1) {
				JOptionPane.showMessageDialog(null, "추가");
			}
			clearTf();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "잘 생각해보세요");
			e1.printStackTrace();
		}
		
	}
	private Sale getSale() {
		String code = tfCode.getText().trim();
		int price = Integer.parseInt(tfPrice.getText().trim());
		int saleCnt = Integer.parseInt(tfCnt.getText().trim());
		int marginRate = Integer.parseInt(tfMarginRate.getText().trim());
		return new Sale(0, new Product(code), price, saleCnt, marginRate);
	}

	private void clearTf() {
		tfCode.setText("");
		tfName.setText("");
		tfPrice.setText("");
		tfCnt.setText("");
		tfMarginRate.setText("");
		
	}
	

	protected void do_btnPrint1_actionPerformed(ActionEvent e) {
		
	}
	protected void do_btnPrint2_actionPerformed(ActionEvent e) {
		
	}
}
