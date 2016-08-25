package com.bitwise.cart;

import java.util.ArrayList;
import java.util.List;

import com.bitwise.exceptions.OutOfStockException;

public class TempCart {
	List<ProductDetails> products=new ArrayList<ProductDetails>();

	public List<ProductDetails> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDetails> products) {
		this.products = products;
	}
	
	public void additem(ProductDetails product)
	{
		
		if(products.contains(product) )
		{
			if(product.getStock()>0)
			{
			product.setQuantity(product.getQuantity()+1);
			product.setTotalQuantity(product.getTotalQuantity()+1);
			product.setStock(product.getStock()-1);
			}
			else
			{
				throw new OutOfStockException("Product out of Stock");
			}
		}
		else
		{
			if(product.getStock()!=0)
			{
			products.add(product);
			product.setQuantity(product.getQuantity()+1);
			product.setTotalQuantity(product.getTotalQuantity()+1);
				product.setStock(product.getStock()-1);
			}
		}
	}

	public void removeProduct(String id) {
		int pid=0;
		for (ProductDetails product : products) {
			if(product.getProduct_Name().equals(id))
			{
				if(product.getQuantity()>1)
				{
					product.setQuantity(product.getQuantity()-1);
					product.setTotalQuantity(product.getTotalQuantity()-1);
					product.setStock(product.getStock()+1);
					break;
				}
				else
				{
					pid=products.indexOf(product);
					products.remove(pid);
					product.setStock(product.getStock()+1);
					product.setQuantity(product.getQuantity()-1);
					product.setTotalQuantity(product.getTotalQuantity()-1);
					break;
				}
				
				
			}		
		}
		
		
	}

	
	
	public int placeOrder() {
		
		int totalPrice=0;
		
		for (ProductDetails product : products) {
			totalPrice=totalPrice+(product.getPrice()*product.getQuantity());
			
		}
		
		return totalPrice;
		}
	
	public void clearQuantity()
	{
		for (ProductDetails product : products) {
			product.setQuantity(0);
			
		}
		
	}
		public void clearTotalQuantity()
		{
			for (ProductDetails product : products) {
				product.setTotalQuantity(0);
			
		}
	}
}
