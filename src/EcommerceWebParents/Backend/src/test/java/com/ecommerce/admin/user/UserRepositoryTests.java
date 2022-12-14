package com.ecommerce.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.print.Pageable;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import com.ecommerce.common.entity.Role;
import com.ecommerce.common.entity.User;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	//Create Method and run using JUnit
	@Test
	public void testCreateUser() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User izz1 = new User("izz@gmail1.com", "name2022", "Izz", "Iczz");
		User izz2 = new User("izz@gmail2.com", "name2022", "Izz", "Iczz");
		User izz3 = new User("izz@gmail3.com", "name2022", "Izz", "Iczz");
		User izz4 = new User("izz@gmail4.com", "name2022", "Izz", "Iczz");
		User izz5 = new User("izz@gmail5.com", "name2022", "Izz", "Iczz");
		User izz6 = new User("izz@gmail6.com", "name2022", "Izz", "Iczz");
		
		izz1.addRole(roleAdmin);
		izz2.addRole(roleAdmin);
		izz3.addRole(roleAdmin);
		izz4.addRole(roleAdmin);
		izz5.addRole(roleAdmin);
		izz6.addRole(roleAdmin);
		
		User savedUser1 = repo.save(izz1);
		User savedUser2 = repo.save(izz2);
		User savedUser3 = repo.save(izz3);
		User savedUser4 = repo.save(izz4);
		User savedUser5 = repo.save(izz5);
		User savedUser6 = repo.save(izz6);
		
		assertThat(savedUser1.getId()).isGreaterThan(0);
		assertThat(savedUser2.getId()).isGreaterThan(0);
		assertThat(savedUser3.getId()).isGreaterThan(0);
		assertThat(savedUser4.getId()).isGreaterThan(0);
		assertThat(savedUser5.getId()).isGreaterThan(0);
		assertThat(savedUser5.getId()).isGreaterThan(0);
		
	}

	//Create Method and run using JUnit
	@Test
	public void testCreateNewUserWithTwoRoles() {
		User izz = new User("izz@gmail7.com", "izz2022", "izz", "iczz");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		izz.addRole(roleEditor);
		izz.addRole(roleAssistant);
		
		User savedUser = repo.save(izz);
		assertThat(savedUser.getId()).isGreaterThan(0);
		
	}
	
	//FindAll Method and run using JUnit
	@Test
	public void testListAllUsers() {
		Iterable<User>listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	
	//FindById Method and run using JUnit
	@Test
	public void testGetUserById() {
		User izzUser = repo.findById(4).get();
		System.out.println(izzUser);
		assertThat(izzUser).isNotNull();
	}
	
	
	//Update Details Method and run using JUnit
	@Test
	public void testUpdateUserDetails() {
		User izzUser = repo.findById(4).get();
		izzUser.setEnabled(true);
		izzUser.setEmail("izz@outlook.com");
		
		repo.save(izzUser);
	}
	
	//Update Roles Method and run using JUnit
	@Test
	public void testUpdateUserRoles() {
		User izzUser = repo.findById(1).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(1);
		
		izzUser.getRoles().remove(roleEditor);
		izzUser.addRole(roleSalesperson);
		
	}
	

	//Delete 
	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		repo.deleteById(userId);
	}
	
	//Get user by email
	@Test
	public void testGetUserByEmail() {
		String email = "izz@gmail1.com";
		User user = repo.getUserByEmail(email);
		assertThat(user).isNotNull();
	}
	
	//Test Count By Id
	@Test
	public void testCountById() {
		Integer id = 1;
		Long counById = repo.countById(id);
		assertThat(counById).isNotNull().isGreaterThan(0);
	}
	
	 
	//Disable
	@Test
	public void testDisableUser() {
		Integer id = 66;
		repo.updateEnabledStatus(id, false);
	}
	
	//Enabled
	@Test
	public void tesEnableUser() {
		Integer id = 67;
		repo.updateEnabledStatus(id, true);
	}
	
	//Pagination
	@Test 
	public void testListFirstPage() {
		int pageNumber = 0;
		int pageSize = 6;
		
		PageRequest pageable = PageRequest.of(pageNumber, pageSize);
		Page<User>page = repo.findAll(pageable);
		List<User>listUsers = page.getContent();
		listUsers.forEach(user -> System.out.println(user));
		assertThat(listUsers.size()).isEqualTo(pageSize);
	}
	
	//Search Users
	@Test
	public void testSearchUsers() {
		String keyword = "Izz";
		int pageNumber = 0;
		int pageSize = 4;
		
		PageRequest pageable = PageRequest.of(pageNumber, pageSize);
		Page<User>page = repo.findAll(keyword, pageable);
		List<User>listUsers = page.getContent();
		listUsers.forEach(user -> System.out.println(user));
		assertThat(listUsers.size()).isGreaterThan(0);
	}
}
