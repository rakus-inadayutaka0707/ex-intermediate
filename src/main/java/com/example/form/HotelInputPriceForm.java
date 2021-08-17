package com.example.form;

/**
 * 検索するホテルの金額を受け取るフォームクラス.
 * 
 * @author inada
 *
 */
public class HotelInputPriceForm {
	/** 入力金額 */
	private String price;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "HotelInputPriceForm [price=" + price + "]";
	}
}
