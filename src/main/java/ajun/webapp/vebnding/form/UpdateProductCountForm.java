package ajun.webapp.vebnding.form;

import java.util.List;

import ajun.webapp.vebnding.object.VendingProduct;

public class UpdateProductCountForm {
	
	private List<VendingProduct> vendingProduct;

	public List<VendingProduct> getVendingProduct() {
		return vendingProduct;
	}

	public void setVendingProduct(List<VendingProduct> vendingProduct) {
		this.vendingProduct = vendingProduct;
	}
}
