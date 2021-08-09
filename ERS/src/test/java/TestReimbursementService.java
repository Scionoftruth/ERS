	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertTrue;
	import static org.mockito.Mockito.doReturn;

	import java.util.ArrayList;
	import java.util.List;

	import org.junit.Before;
	import org.junit.Test;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.MockitoAnnotations;
	import static org.mockito.Mockito.any;
	import static org.mockito.ArgumentMatchers.anyString;
	import static org.mockito.Mockito.doNothing;

	import com.ers.dao.ReimbursementDao;
import com.ers.dao.ReimbursementDaoDB;
import com.ers.dao.RoleDao;
	import com.ers.dao.StatusDao;
	import com.ers.dao.TypeDao;
	import com.ers.enums.RType;
	import com.ers.enums.Status;
	import com.ers.enums.UserRole;
	import com.ers.models.Reimbursement;
	import com.ers.models.ReimbursementStatus;
	import com.ers.models.ReimbursementType;
	import com.ers.models.User;
	import com.ers.models.UserRoles;
	import com.ers.services.ReimbursementService;

	public class TestReimbursementService {
			
			@InjectMocks
			public ReimbursementService rServ;

			@Mock
			public ReimbursementDaoDB riembDao;
			public StatusDao sDao;
			public TypeDao tDao;
			public RoleDao roleDao; 
			
			@Before
			public void initMocks() {
				MockitoAnnotations.initMocks(this);
			}
//			@BeforeMethod
//			public void setUp() {
//			    rDao = new ReimbursementDao();
//			}

//			 public static void setupBeforeClass() {
//			      PowerMockito.mockStatic(LoggerFactory.class);
//			      loggerMock = mock(Logger.class);
//			      when(LoggerFactory.getLogger(any(Class.class))).thenReturn(loggerMock);
		//	
//			 e = new UserRoles();
//			@BeforeClass
//			public void setUp() {
			UserRoles e = new UserRoles(0,UserRole.EMPLOYEE);
//			}

			ReimbursementStatus s = new ReimbursementStatus(3, Status.PENDING);
			ReimbursementStatus s2 = new ReimbursementStatus(3, Status.APPROVED);
			ReimbursementStatus s3 = new ReimbursementStatus(3, Status.DENIED);
			ReimbursementType t = new ReimbursementType(6, RType.LODGING);
//			UserRoles e = new UserRoles(0,UserRole.EMPLOYEE);
			User user = new User(1,"test", "user", "testuser", "test@email.com", "testpass",e );
			User not = new User(0,"Test","User","testuser","test@email.com","testpass",e);
			private Reimbursement r1 = new Reimbursement(t , 100.0d, "Date in string format", "test string",
					s , user );
			private Reimbursement r2 = new Reimbursement(t , 100.0d, "Date in string format", "test string",
					s2 , user );
			private Reimbursement r3 = new Reimbursement(t , 100.0d, "Date in string format", "test string",
					s3 , user );
			

			private ArrayList<Reimbursement> rList = new ArrayList<>();
			
			@Test
			public void testGetAllReimbursements() {
				rList.add(r1);
				rList.add(r2);
				rList.add(r3);

				System.out.println(rList.size());

				doReturn(rList).when(riembDao).getAllReimbursments();

				List<Reimbursement> ret = rServ.getAllReimbursements();
				assertEquals(3, ret.size());
			}
			
			@Test
			public void testGetReimbursementById() {

				doReturn(r1).when(riembDao).getReimbursementById(1);

				Reimbursement got = rServ.getReimbursementById(1);

				assertEquals(r1.getId(), got.getId());
			}
			
			@Test
			public void testAddReimbursement() {
				
				
				
		}
			
			@Test
			public void testGetReimbursementsByUser() {
				
				rList.add(r1);
				
				doReturn(rList).when(riembDao).getAllAcceptedReimbursmentsForUser(user);
				
				List<Reimbursement> ret = rServ.getAllReimbursementsForUser(user);
				
				assertEquals(1, ret.size());
				
				rList.add(r2);
				
				doReturn(rList).when(riembDao).getAllPendingReimbursments();
				
				List<Reimbursement> ret1 = rServ.getAllReimbursementsForUser(user);
				
				assertEquals(2, ret1.size());
				
				rList.add(r3);
				
				doReturn(rList).when(riembDao).getAllDeniedReimbursments();
				
				List<Reimbursement> ret3 = rServ.getAllReimbursementsForUser(user);
				
				assertEquals(3, ret3.size());
				
				
			}
			
			@Test
			public void testGetReimbursementsByUser2() {
				
				rList.add(r1);
				
				doReturn(rList).when(riembDao).getAllPendingReimbursmentsForUser(user);
				
				List<Reimbursement> ret = rServ.getAllReimbursementsForUser(user);
				
				assertEquals(1, ret.size());
			}
			
			@Test
			public void testGetReimbursementsByUser3() {
				
				rList.add(r1);
				
				doReturn(rList).when(riembDao).getAllDeniedReimbursmentsForUser(user);
				
				List<Reimbursement> ret = rServ.getAllReimbursementsForUser(user);
				
				assertEquals(1, ret.size());
			}
			
			@Test
			public void testGetAllPendingReimbursements() {
				
				rList.add(r1);
				
				doReturn(rList).when(riembDao).getAllPendingReimbursments();
				
				List<Reimbursement> ret = rServ.getAllPendingReimbursements();
				
				assertEquals(1, ret.size());
				
			}
			
			@Test
			public void testGetAllAcceptedReimbursements() {
				
				rList.add(r2);
				
				doReturn(rList).when(riembDao).getAllPendingReimbursments();
				
				List<Reimbursement> ret = rServ.getAllAcceptedReimbursements();
				
				assertEquals(0, ret.size());
				
			}
			
			@Test
			public void testGetAllDeniedReimbursements() {
				
				rList.add(r3);
				
				doReturn(rList).when(riembDao).getAllPendingReimbursments();
				
				List<Reimbursement> ret = rServ.getAllDeniedReimbursements();
				
				assertEquals(0, ret.size());
				
			}
			
			
			
//			@Test
//			public void testGetMe() {
//				String expected = "Me";
//				String actual = rServ.getMe();
//				assertEquals(expected,actual);
//			}
			
//			@Test
//			public void testGetNotMe() {
//				String expected = "Me";
//				String actual = rServ.getNotMe();
//				assertEquals(expected,actual);
//			}
//			@Test
//			public void testDeleteReimbursement() {
//				
//				doNothing().when(rServ.deleteReimbursement(anyString())).thenReturn(r1);
//				
//				doReturn(r1).when(riembDao)
//				
//				Reimbursement got = rServ.deleteReimbursement(1);
	//
//				assertEquals(r1.setId(1), got.setId(0));
//			}
			
	
	
}
