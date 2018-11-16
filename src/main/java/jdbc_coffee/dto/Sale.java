package jdbc_coffee.dto;

public class Sale {
	private int no;
	private Product code;
	private int price;
	private int saleCnt;
	private int marginRate;

	public Sale() {

	}

	public Sale(Product code) {
		this.code = code;
	}

	public Sale(int no, Product code, int price, int saleCnt, int marginRate) {
		this.no = no;
		this.code = code;
		this.price = price;
		this.saleCnt = saleCnt;
		this.marginRate = marginRate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Product getCode() {
		return code;
	}

	public void setCode(Product code) {
		this.code = code;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSaleCnt() {
		return saleCnt;
	}

	public void setSaleCnt(int saleCnt) {
		this.saleCnt = saleCnt;
	}

	public int getMarginRate() {
		return marginRate;
	}

	public void setMarginRate(int marginRate) {
		this.marginRate = marginRate;
	}

	@Override
	public String toString() {
		return String.format("Cafe [no=%s, code=%s, price=%s, saleCnt=%s, marginRate=%s]", no, code, price, saleCnt,
				marginRate);
	}

}
