package com.oracle.minimalism.dto;

import lombok.Data;

@Data
public class ProductImg {
	private int product_number;
	private int file_number;
	private String stored_filename;
	private String stored_thumbnail;
}
