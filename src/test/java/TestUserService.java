import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import com.ers.dao.UserDao;
import com.ers.enums.UserRole;
import com.ers.dao.RoleDao;
import com.ers.models.User;
import com.ers.models.UserRoles;
import com.ers.services.UserService;
import com.ers.exceptions.InvalidCredentialsException;
import com.ers.exceptions.UserDoesNotExistException;

public class TestUserService {
	
	@InjectMocks
	public UserService uServ;
	
	@Mock
	public UserDao uDao;
	public RoleDao rDao;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testValidLogin() {
		UserRoles e = new UserRoles(0,UserRole.EMPLOYEE);
		
		User u1 = new User(1,"Test","User","testuser","test@email.com","testpass",e);
		User not = new User(0,"Test","User","testuser","test@email.com","testpass",e);
		
		when(uDao.getUserByUsername(anyString())).thenReturn(u1);
		
		User loggedIn = uServ.signIn("testuser", "testpass");
		
		assertEquals(u1.getId(),loggedIn.getId());
	}
	
	@Test(expected  = UserDoesNotExistException.class)
	public void testInvalidUsername() {
		UserRoles e = new UserRoles(0,UserRole.EMPLOYEE);
		
		User u1 = new User(1,"test", "user", "testuser", "test@email.com", "testpass",e);
		User not = new User(0, "test", "user","testuser", "test@mail.com", "testpass",e);
		
		when(uDao.getUserByUsername(anyString())).thenReturn(not);
		
		User loggedIn = uServ.signIn("testuser", "testpass");
	}
	
	@Test(expected = InvalidCredentialsException.class)
	public void testInvalidPassword() {
		UserRoles e = new UserRoles(0,UserRole.EMPLOYEE);
		
		User u1 = new User(1,"test", "user", "testuser", "test@email.com", "testpass",e);
		User not = new User(1, "test", "user","testuser", "test@mail.com", "wrongpass",e);
		
		when(uDao.getUserByUsername(anyString())).thenReturn(not);
		
		User loggedIn = uServ.signIn("testuser", "testpass");
	}
	
	//@Test
	//public void testSignup() {
	//	
	//}
	
	@Test
	public void testGetUserByUsername() {
		UserRoles e = new UserRoles(0,UserRole.EMPLOYEE);
		
		User u1 = new User(1,"test", "user", "testuser", "test@email.com", "testpass",e);
		User not = new User(1, "test", "user","testuser", "test@mail.com", "wrongpass",e);
		
		assertEquals(uServ.getUserByUsername("testuser"), uServ.getUserById(1));
	}
	

}
