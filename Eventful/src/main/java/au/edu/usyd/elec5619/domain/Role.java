package au.edu.usyd.elec5619.domain;

import lombok.Data;

import javax.persistence.*;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Data
@Entity
@EnableAutoConfiguration
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private int id;
	@Column(name = "role")
	private String role;
	
	// getters
	public String getRole() {
		return this.role;
	}
	
	// setters
	public void setRole(String role) {
		this.role = role;
	}
}