package com.neosoft.demo.student.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "student_master")
@Getter @Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min = 5, max = 10, message="first name should be between 5 - 10 characters.")
	@Column(name = "first_name")
	private String firstName;
	
	@Min(value = 5, message = "Please insert at least 5 characters")
	@Column(name = "last_name")
	private String lastName;
	
	@Pattern(regexp=".+@.+\\..+", message="Enter valid email-Id!")
	@Column(name = "email")
	private String email;
	
	@NotNull(message="Please fill contact No")
    @Length(min=10, max=10, message="Contact No should be 10 charactes")
	@Column(name = "mobile_no")
	private String mobileNo;
	
	@OneToMany(targetEntity=Project.class,cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private Set<Project> projects;
}
