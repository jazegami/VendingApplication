package ajun.webapp.vebnding.object;

public class ChangeInfo {
	
	private int tenCoin = 0;
	
	private int fiftyCoin = 0;
	
	private int oneHundredCoin = 0;
	
	private int fiveHundredCoin = 0;
	
	private int oneThousandBill = 0;
	
	public void addTenCoin(int count) {
		tenCoin = tenCoin + count;
	}
	
	public void addFiftyCoin(int count) {
		fiftyCoin = fiftyCoin + count;
	}
	
	public void addOneHundredCoin(int count) {
		oneHundredCoin = oneHundredCoin + count;
	}
	
	public void addFiveHundredCoin(int count) {
		fiveHundredCoin = fiveHundredCoin + count;
	}
	
	public void addOneThousandBill(int count) {
		oneThousandBill = oneThousandBill + count;
	}

	public int getOneThousandBill() {
		return oneThousandBill;
	}

	public void setOneThousandBill(int oneThousandBill) {
		this.oneThousandBill = oneThousandBill;
	}

	public int getTenCoin() {
		return tenCoin;
	}

	public void setTenCoin(int tenCoin) {
		this.tenCoin = tenCoin;
	}

	public int getFiftyCoin() {
		return fiftyCoin;
	}

	public void setFiftyCoin(int fiftyCoin) {
		this.fiftyCoin = fiftyCoin;
	}

	public int getOneHundredCoin() {
		return oneHundredCoin;
	}

	public void setOneHundredCoin(int oneHundredCoin) {
		this.oneHundredCoin = oneHundredCoin;
	}

	public int getFiveHundredCoin() {
		return fiveHundredCoin;
	}

	public void setFiveHundredCoin(int fiveHundredCoin) {
		this.fiveHundredCoin = fiveHundredCoin;
	}

}
