package com.example.Assignment_Java5.entitys;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="categories")
@Data
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",nullable = false)
	private Integer id;

	@Column(name="name")
	private String name;

	@Column(name="status")
	private Integer status;
}
