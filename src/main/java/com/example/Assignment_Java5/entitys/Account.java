package com.example.Assignment_Java5.entitys;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name="accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",nullable = false)
	private Integer id;


	@NotBlank(message = "Không được để trống")
	@Column(name="username")
	private String username;

	@NotEmpty(message = "Không được để trống")
	@Size(min=3, max = 15)
	@Column(name="password")
	private String password;

	@NotBlank(message = "Không được để trống")
	@Column(name="fullname")
	private String fullname;

	@Email(message = "Sai định dạng email")
	@NotBlank(message = "Không được để trống")
	@Column(name="email")
	private String email;

	@Column(name="activated")
	private int activated;

	@Column(name="admin")
	private int admin;
}
