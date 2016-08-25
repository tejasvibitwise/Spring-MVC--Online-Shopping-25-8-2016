package com.bitwise.cart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitwise.exceptions.OutOfStockException;

@Controller
public class ProductController {

@Autowired
CountProduct countProduct;
	
@Autowired
ProductList productList;

@Autowired
CartItems cartItems;



	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public ModelAndView save() {
		
		
		return new ModelAndView("success", "productList", productList);
		
		}
	
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public ModelAndView addProduct(Model model,HttpServletRequest request,@RequestParam String id) {
	
		ProductDetails product=productList.findProduct(id);
		if(product.stock>=1)
			countProduct.incrementCount();
		
		int c=countProduct.getCount();
		
		HttpSession session= request.getSession();
		
		cartItems=(CartItems) session.getAttribute("cart");
		
		cartItems.additem(product);
		
		session.setAttribute("cart", cartItems);
		session.setAttribute("cart2", cartItems.getProducts());
		model.addAttribute("count", c);
		
		return new ModelAndView("success", "productList", productList);
	
		}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView viewProduct(Model model,HttpServletRequest request) {
	
		HttpSession session=request.getSession(false);
		cartItems=(CartItems) session.getAttribute("cart");
		int c=countProduct.getCount();
		model.addAttribute("count", c);
		return new ModelAndView("viewcart", "cartItems", cartItems);
	
	}

	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView removeProduct(Model model,HttpServletRequest request,@RequestParam String id) {
	
		HttpSession session=request.getSession(false);
		cartItems=(CartItems) session.getAttribute("cart");
		ProductDetails product=productList.findProduct(id);
		cartItems.removeProduct(id);
		
		if(product.stock>=1)
			countProduct.decrementCount();
		
		int c=countProduct.getCount();
		
		session.setAttribute("cart", cartItems);
		session.setAttribute("cart2", cartItems.getProducts());
		model.addAttribute("count", c);
		return new ModelAndView("viewcart", "cartItems", cartItems);
	
		}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView displayProduct(Model model) {
		int c=countProduct.getCount();
		
		model.addAttribute("count", c);
		return new ModelAndView("success", "productList", productList);
	
		}
	@RequestMapping(value = "/CartListEmpty", method = RequestMethod.GET)
	public ModelAndView dispProduct(Model model,HttpServletRequest request) {
		int c=countProduct.getCount();
		HttpSession session=request.getSession(false);
		cartItems=(CartItems) session.getAttribute("cart");
		session.setAttribute("cart2", cartItems.getProducts());
		System.out.println("cart before refresh"+session.getAttribute("cart2"));
		List<ProductDetails> products=cartItems.getProducts();

		for (ProductDetails product : products) {
			product.setQuantity(0);
			System.out.println("in clear quant");
		}
		
		session.setAttribute("cart", new CartItems());
		System.out.println("cart after refresh"+session.getAttribute("cart2"));
		System.out.println("cart after refresh"+session.getAttribute("cart"));
		//cartItems.clearQuantity();
		
		model.addAttribute("count", c);
		return new ModelAndView("success", "productList", productList);
	
		}
	
	@RequestMapping(value = "/placeorder", method = RequestMethod.GET)
	public ModelAndView placeOrder(Model model,HttpServletRequest request) {
	
		HttpSession session=request.getSession(false);
		cartItems=(CartItems) session.getAttribute("cart");
		
			countProduct.setCount(0);	
			
		model.addAttribute("total",cartItems.placeOrder());
//		session.setAttribute("cart", new CartItems());
		
		//scartItems.clearQuantity();
		
		return new ModelAndView("placeOrder", "cartItems", cartItems);
		}
	
	@ExceptionHandler(OutOfStockException.class)
	public ModelAndView handleCustomException(OutOfStockException except) {

	      return new ModelAndView("ExceptionPage", "Msg", except.getMessage());

	}

}
