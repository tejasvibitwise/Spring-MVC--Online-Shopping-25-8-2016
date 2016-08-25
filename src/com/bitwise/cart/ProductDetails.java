package com.bitwise.cart;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails {
	
		String product_Name;
		int price;
		
		int stock;
		int quantity;
		int totalQuantity;
		
		public int getTotalQuantity() {
			return totalQuantity;
		}

		public void setTotalQuantity(int totalQuantity) {
			this.totalQuantity = totalQuantity;
		}
		
		public int getQuantity() {
			return quantity;
		}


		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}


		public String getProduct_Name() {
			return product_Name;
		}


		public void setProduct_Name(String product_Name) {
			this.product_Name = product_Name;
		}


		public int getPrice() {
			return price;
		}


		public void setPrice(int price) {
			this.price = price;
		}


		public int getStock() {
			return stock;
		}


		public void setStock(int stock) {
			this.stock = stock;
		}


		

		@Override
		public String toString() {
			return "<BR>Product_Name=" + product_Name +"<BR>";
		}


}
