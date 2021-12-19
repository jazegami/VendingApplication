package ajun.webapp.vebnding.form;

import ajun.webapp.vebnding.object.ChangeInfo;
import ajun.webapp.vebnding.object.VendingProduct;

public class BuyProductForm {
	
	private Long productId;

	private ChangeInfo changeInfo;
	
	private int sumPayment;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public ChangeInfo getChangeInfo() {
		return changeInfo;
	}

	public void setChangeInfo(ChangeInfo changeInfo) {
		this.changeInfo = changeInfo;
	}

	public int getSumPayment() {
		return sumPayment;
	}

	public void setSumPayment(int sumPayment) {
		this.sumPayment = sumPayment;
	}
}
