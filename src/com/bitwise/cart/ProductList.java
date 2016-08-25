package com.bitwise.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.bitwise.exceptions.ItemNotFoundException;


@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ProductList {

	List<ProductDetails> products=new ArrayList<ProductDetails>();

	public List<ProductDetails> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDetails> products) {
		this.products = products;
	}
	
	
	public ProductDetails findProduct(String id){
		for (ProductDetails product : products) {
			if(product.getProduct_Name().equals(id))
			{
				return product;
			}
			
			
		}
	
		
		throw new ItemNotFoundException();
		
	}
	
	
}
