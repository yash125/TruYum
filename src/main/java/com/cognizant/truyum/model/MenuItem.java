package com.cognizant.truyum.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name="menu_item")
public class MenuItem {
	@NotNull(message = "Id cannot by null")
	@Id
    private long id;

	@NotNull(message = "Name cannot by null")
	@NotEmpty(message = "Name cannot be empty")
	@Column
	private String name;

	@NotNull(message = "Price cannot be null")
	@Column
	private float price;

	@NotNull(message = "Active cannot be null")
	@Column
	private boolean active;

	@NotNull(message = "Date of launch cannot be null")
	@Column
	private Date dateOfLaunch;

	@NotNull(message = "Category cannot be null")
	@Column
	private String category;

	@NotNull(message = "Free delivery cannot be null")
	@Column
	private boolean freeDelivery;
}
