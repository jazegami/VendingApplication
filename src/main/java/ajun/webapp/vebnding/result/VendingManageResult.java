package ajun.webapp.vebnding.result;

import java.util.List;

import ajun.webapp.vebnding.object.ChangeInfo;
import ajun.webapp.vebnding.object.VendingProduct;

public class VendingManageResult {
	
	private List<VendingProduct> vendingProductList;
	
	private ChangeInfo changeInfo;

	public List<VendingProduct> getVendingProductList() {
		return vendingProductList;
	}

	public void setVendingProductList(List<VendingProduct> vendingProductList) {
		this.vendingProductList = vendingProductList;
	}

	public ChangeInfo getChangeInfo() {
		return changeInfo;
	}

	public void setChangeInfo(ChangeInfo changeInfo) {
		this.changeInfo = changeInfo;
	}

}
