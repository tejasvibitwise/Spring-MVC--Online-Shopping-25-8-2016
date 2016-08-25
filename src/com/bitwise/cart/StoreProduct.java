package com.bitwise.cart;

import java.util.ArrayList;
import java.util.List;

public class StoreProduct {
	List<ProductDetails> products=new ArrayList<ProductDetails>();

	public List<ProductDetails> getProducts() {
		return products;
	}

//	public void setProducts(List<ProductDetails> products) {
//		this.products = products;
//	}

	public void setProducts(List<ProductDetails> products2) {
		this.products = products2;
		
	}
}
