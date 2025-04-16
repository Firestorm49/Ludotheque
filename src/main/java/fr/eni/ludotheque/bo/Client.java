package fr.eni.ludotheque.bo;


import jakarta.persistence.*; 

 

import lombok.*; 

 

@NoArgsConstructor 

@AllArgsConstructor 

@Getter 

@Setter 

@ToString 

@Builder 

@Entity 

@Table(name="Client") 

public class Client { 


@Id 

@GeneratedValue(strategy = GenerationType.IDENTITY) 

@Column(name = "NO_CLIENT") 

private Integer id; 

 

@Column(name = "LAST_NAME",nullable = false, length = 90) 

private String nom; 

 

@Column(name = "FIRST_NAME",nullable = false, length = 150) 

private String prenom; 

 

@Column(nullable = false, unique = true, length = 255) 

private String email; 



@Column(name="CELL_NUMBER", length = 12) 

private String numTel; 

} 
