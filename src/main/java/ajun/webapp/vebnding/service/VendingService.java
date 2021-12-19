package ajun.webapp.vebnding.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ajun.webapp.vebnding.entity.TChangeManage;
import ajun.webapp.vebnding.entity.TVendingProduct;
import ajun.webapp.vebnding.form.BuyProductForm;
import ajun.webapp.vebnding.form.UpdateChangeCountForm;
import ajun.webapp.vebnding.form.UpdateProductCountForm;
import ajun.webapp.vebnding.object.ChangeInfo;
import ajun.webapp.vebnding.object.VendingProduct;
import ajun.webapp.vebnding.repository.TChangeManageRepository;
import ajun.webapp.vebnding.repository.TVendingProductRepository;
import ajun.webapp.vebnding.result.BuyProductResult;

@Service
public class VendingService {
	
	@Autowired
	TVendingProductRepository productRepository;
	
	@Autowired
	TChangeManageRepository changeRepository;
	
	// DBから商品情報全量を取得する
	public List<VendingProduct> getProductList() {
		List<TVendingProduct> getProductList = productRepository.findAll(Sort.by(Sort.Direction.ASC, "vendingProductId"));
		List<VendingProduct> productList = new ArrayList<VendingProduct>();
		for (TVendingProduct product : getProductList) {
			VendingProduct setProduct = new VendingProduct();
			setProduct.setId(product.getVendingProductId());
			setProduct.setName(product.getProductName());
			setProduct.setPrice(product.getProductPrice());
			setProduct.setStockCount(product.getProductCount());
			productList.add(setProduct);
		}
		
		return productList;
	}
	
	// 商品情報取得処理
	public TVendingProduct getProduct(Long productId) {
		TVendingProduct product = productRepository.findByVendingProductId(productId);
		return product;
	}
	
	// 商品
	public VendingProduct getProductInfo() {
		TVendingProduct product = productRepository.findByVendingProductId(1L);
		VendingProduct setProduct = new VendingProduct();
		setProduct.setId(product.getVendingProductId());
		setProduct.setName(product.getProductName());
		setProduct.setPrice(product.getProductPrice());
		setProduct.setStockCount(product.getProductCount());
		return setProduct;
	}
	
	// 商品購入処理
	public void buyProduct(TVendingProduct product, BuyProductForm pruductForm, BuyProductResult result) {
		int change = pruductForm.getSumPayment() - product.getProductPrice();
		calcChange(result, change, pruductForm.getChangeInfo());
		
		if (result.isResult()) {
			result.setResultMessage(product.getProductName() + "を購入しました。");
			int productStock = product.getProductCount();
			product.setProductCount(productStock - 1);
			productRepository.save(product);	
		}
	}
	
	// 金額確認処理
	public boolean checkPayment(int payment, int price) {
		if (payment >= price) {
			return true;
		} else {
			return false;
		}
	}
	
	// 釣り銭計算処理
	private void calcChange(BuyProductResult result, int change, ChangeInfo sumChangeInfo) {
		TChangeManage changeManage = getChangeInfo();
		ChangeInfo changeInfo = new ChangeInfo();
		
		int tenCoinCount = changeManage.getTenCoin();
		int fiftyCoinCount = changeManage.getFiftyCoin();
		int oneHundredCoinCount = changeManage.getOnehundredCoin();
		int fiveHundredCoinCount = changeManage.getFivehundredCoin();
		
		// 投入されたお金を追加
		tenCoinCount = tenCoinCount + sumChangeInfo.getTenCoin();
		fiftyCoinCount = fiftyCoinCount + sumChangeInfo.getFiftyCoin();
		oneHundredCoinCount = oneHundredCoinCount + sumChangeInfo.getOneHundredCoin();
		fiveHundredCoinCount = fiveHundredCoinCount + sumChangeInfo.getFiveHundredCoin();
		
		// 500円玉チェック
		boolean fiveHundredFlg = true;
		
		while(change >= 500 && fiveHundredFlg) {
			if (fiveHundredCoinCount > 0) {
				change = change - 500;
				fiveHundredCoinCount = fiveHundredCoinCount - 1;
				changeInfo.addFiveHundredCoin(1);
			} else {
				fiveHundredFlg = false;
			}
		}
		
		// 100円玉チェック
		boolean oneHundredFlg = true;
		
		while(change >= 100 && oneHundredFlg) {

			if (oneHundredCoinCount > 0) {
				change = change - 100;
				oneHundredCoinCount = oneHundredCoinCount - 1;
				changeInfo.addOneHundredCoin(1);
			} else {
				oneHundredFlg = false;
			}
		}
		
		// 50円玉チェック
		boolean fiftyFlg = true;
		
		while(change >= 50 && fiftyFlg) {

			if (fiftyCoinCount > 0) {
				change = change - 50;
				fiftyCoinCount = fiftyCoinCount -1;
				changeInfo.addFiftyCoin(1);
			} else {
				fiftyFlg = false;
			}
		}
		
		// 10円玉チェック
		boolean tenFlg = true;
		
		while(change >= 10 && tenFlg) {

			if (tenCoinCount > 0) {
				change = change - 10;
				tenCoinCount = tenCoinCount -1;
				changeInfo.addTenCoin(1);
			} else {
				tenFlg = false;
			}
		}
		
		if (change == 0) {
			result.setResult(true);
			result.setResultMessage("成功");
			result.setReturnChange(changeInfo);
			
			// 最終結果をセットして更新
			changeManage.setTenCoin(tenCoinCount);
			changeManage.setFiftyCoin(fiftyCoinCount);
			changeManage.setOnehundredCoin(oneHundredCoinCount);
			changeManage.setFivehundredCoin(fiveHundredCoinCount);
			changeRepository.save(changeManage);
		} else {
			result.setResult(false);
			result.setResultMessage("お釣りが不足しています。管理者に連絡してください。");
			result.setReturnChange(sumChangeInfo);
		}
	}
	
	// DBから釣り銭を取得する
	public TChangeManage getChangeInfo() {
		return changeRepository.findByChangeManageId(1L);
	}
	
	// 管理用のつり銭の更新処理
	public void updateChangeInfo(UpdateChangeCountForm form) {
		TChangeManage changeManage = new TChangeManage();
		changeManage.setChangeManageId(1L);
		changeManage.setTenCoin(form.getTenCoin());
		changeManage.setFiftyCoin(form.getFiftyCoin());
		changeManage.setOnehundredCoin(form.getOnehundredCoin());
		changeManage.setFivehundredCoin(form.getFivehundredCoin());
		changeManage.setOnethousandBill(form.getOnethousandBill());
		changeRepository.save(changeManage);
	}
	
	// 管理用の商品数更新処理
	public void updateProductCount(UpdateProductCountForm form) {
		for (VendingProduct product : form.getVendingProduct()) {
			TVendingProduct vendingProduct = productRepository.findByVendingProductId(product.getId());
			vendingProduct.setProductCount(product.getStockCount());
			productRepository.save(vendingProduct);
		}
	}

}
