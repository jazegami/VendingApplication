package ajun.webapp.vebnding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ajun.webapp.vebnding.entity.TChangeManage;
import ajun.webapp.vebnding.entity.TVendingProduct;
import ajun.webapp.vebnding.form.BuyProductForm;
import ajun.webapp.vebnding.form.UpdateChangeCountForm;
import ajun.webapp.vebnding.form.UpdateProductCountForm;
import ajun.webapp.vebnding.object.ChangeInfo;
import ajun.webapp.vebnding.object.VendingProduct;
import ajun.webapp.vebnding.result.BuyProductResult;
import ajun.webapp.vebnding.service.VendingService;

@Controller
@RequestMapping("/")
public class VendingController {
	
	@Autowired
	VendingService vendingService;
	
	// 初期表示の受付処理
	@RequestMapping(method = RequestMethod.GET)
    public String displayVending(Model model) {
		// 自販機の今の商品、状態を取得する処理
		List<VendingProduct> productList = vendingService.getProductList();
		model.addAttribute("productList", productList);
		//VendingProduct product = vendingService.getProductInfo();
		//model.addAttribute("productList", product);
		model.addAttribute("buyProductForm", new BuyProductForm());
		model.addAttribute("result", new BuyProductResult());
		
		/*
		BuyProductForm buyProductForm = new BuyProductForm();
		buyProductForm.setVendingProductList(productList);
        model.addAttribute("buyProductForm", buyProductForm);
        */
        return "top";
    }
	
	// 購入時の受付処理
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String buyVending(@ModelAttribute BuyProductForm pruductForm, Model model) {
		BuyProductResult result = new BuyProductResult();
		// 商品情報取得処理
		TVendingProduct product = vendingService.getProduct(pruductForm.getProductId());
		
		if (product.getProductCount() == 0) {
			result.setResult(false);
			result.setResultMessage("対象の商品は売り切れです。");
			
			// 自販機の今の商品、状態を取得する処理
			List<VendingProduct> productList = vendingService.getProductList();
	        model.addAttribute("productList",productList);
	        model.addAttribute("buyProductForm", new BuyProductForm());
	        model.addAttribute("result", result);
	        return "top";
		}
		
		// 金額の確認処理
		boolean checkPay = vendingService.checkPayment(pruductForm.getSumPayment(), product.getProductPrice());
		if (!checkPay) {
			result.setResult(false);
			result.setResultMessage("投入金額が不足しています。");
			
			result.setReturnChange(pruductForm.getChangeInfo());
			
			// 自販機の今の商品、状態を取得する処理
			List<VendingProduct> productList = vendingService.getProductList();
	        model.addAttribute("productList",productList);
	        model.addAttribute("buyProductForm", new BuyProductForm());
	        model.addAttribute("result", result);
	        return "top";
		}
		
		//　購入処理
		vendingService.buyProduct(product, pruductForm, result);
		
		// 自販機の今の商品、状態を取得する処理
		List<VendingProduct> productList = vendingService.getProductList();
        model.addAttribute("productList",productList);
		//VendingProduct products = vendingService.getProductInfo();
		//model.addAttribute("productList", products);
        model.addAttribute("buyProductForm", new BuyProductForm());
        model.addAttribute("result", result);
        return "top";
    }
	
	// 管理画面
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String displayManage(Model model) {
		// 自販機の今の商品、状態を取得する処理
		List<VendingProduct> productList = vendingService.getProductList();
		TChangeManage changeInfo = vendingService.getChangeInfo();
		model.addAttribute("productList", productList);
		model.addAttribute("updateChangeCountForm", new UpdateChangeCountForm());
		model.addAttribute("updateProductCountForm", new UpdateProductCountForm());
		model.addAttribute("changeInfo", changeInfo);
		
        return "manage";
    }
	
	@RequestMapping(value = "/updateProductCount", method = RequestMethod.POST)
    public String updateChangeCount(@ModelAttribute UpdateProductCountForm form, Model model) {
		// 更新
		vendingService.updateProductCount(form);
		
		// 更新後、再取得
		List<VendingProduct> productList = vendingService.getProductList();
		TChangeManage changeInfo = vendingService.getChangeInfo();
		model.addAttribute("productList", productList);
		model.addAttribute("updateChangeCountForm", new UpdateChangeCountForm());
		model.addAttribute("updateProductCountForm", new UpdateProductCountForm());
		model.addAttribute("changeInfo", changeInfo);
		
        return "manage";
    }
	
	@RequestMapping(value = "/updateChange", method = RequestMethod.POST)
    public String updateProductCount(@ModelAttribute UpdateChangeCountForm form, Model model) {
		// 更新
		vendingService.updateChangeInfo(form);
		
		// 更新後、再取得
		List<VendingProduct> productList = vendingService.getProductList();
		TChangeManage changeInfo = vendingService.getChangeInfo();
		model.addAttribute("productList", productList);
		model.addAttribute("updateChangeCountForm", new UpdateChangeCountForm());
		model.addAttribute("updateProductCountForm", new UpdateProductCountForm());
		model.addAttribute("changeInfo", changeInfo);
		
        return "manage";
    }

}
