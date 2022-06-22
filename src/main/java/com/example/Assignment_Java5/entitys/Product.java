package com.example.Assignment_Java5.entitys;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name="products")
@Data
@Component
public class Product {
	@Id
	@Column(name="id",nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name")
	private String name;
	
	@Column(name="image")
	private String image;

	@Column(name="price")
	private Double price;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="available")
	private int available;

	@Column(name="size")
	private String size;

	@Column(name="color")
	private String color;

	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
}
