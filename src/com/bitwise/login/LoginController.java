package com.bitwise.login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitwise.cart.CartItems;
import com.bitwise.cart.CountProduct;
import com.bitwise.cart.ProductDetails;
import com.bitwise.cart.ProductList;
import com.bitwise.cart.StoreProduct;
import com.bitwise.login.LoginBean;
import com.bitwise.login.LoginValidator;


@Controller
// @RequestMapping("/helloworld")
public class LoginController {

	@Autowired
	LoginBean loginbean;

	@Autowired
	LoginValidator loginvalidator;

	// ProductDetails p1=new ProductDetails();
	@Autowired
	ProductList productList;
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String init(Model model, HttpServletRequest req) {
		
		// model.addAttribute("message","Please enter following details");
		model.addAttribute("LoginBean", new LoginBean());
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(Model model,@ModelAttribute("LoginBean") LoginBean loginBean,
			BindingResult result,HttpServletRequest request, HttpServletResponse response) {

		loginvalidator.validate(loginBean, result);

		if (result.hasErrors())
			return "login";
		else {
			String user=loginBean.getUserName();
			model.addAttribute("message", "Online shopping");
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			session.setAttribute("cart",new CartItems());
			// model.addAttribute("ProductBean",new ProductBean());
			return "redirect:/success";
			
		}
	}
	
	@Autowired 
	CountProduct countProduct;
	
	@Autowired
	CartItems cartItems;
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model,HttpServletRequest request) {
		
		HttpSession session=request.getSession(false);
		System.out.println("in logout");
	
		
		//cartItems=(CartItems) session.getAttribute("cart2");
		
		
		
		
		//List<ProductDetails> products=cartItems.getProducts();
		List<ProductDetails> products=(List<ProductDetails>) session.getAttribute("cart2");
		List<ProductDetails> products1=productList.getProducts();
		//cartItems.clearQuantity();
		for (ProductDetails product : products) {
			product.setQuantity(0);
			System.out.println("in clear quant");
		}
		
//		for(ProductDetails product:products)
//		{
//			product.setStock(product.getStock()+product.getTotalQuantity());
//			System.out.println("stock"+product.getStock());
//			System.out.println("quant"+ product.getTotalQuantity());
//		}
//		
		for(ProductDetails product:products)
		{
			product.setStock(product.getStock()+product.getTotalQuantity());
			System.out.println("stock"+product.getStock());
			System.out.println("quant"+ product.getTotalQuantity());
		}
		
		
		for (ProductDetails product : products) {
			product.setTotalQuantity(0);
			System.out.println("in clear quant");
		}
		
		countProduct.setCount(0);
		
		//cartItems.clearTotalQuantity();
		session.setAttribute("cart", new CartItems());
		session.invalidate();
		
		model.addAttribute("LoginBean", new LoginBean());
		return "login";
	
		}

	
}