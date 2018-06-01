package dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import common.SpringTestBase;
import common.constant.UserRole;
import common.util.PasswordUtil;
import model.Fridge;
import model.Item;
import model.User;
import model.DailyChange;

public class DaoImplTest extends SpringTestBase {

	@Autowired
	private UserDao userDao;
	@Autowired
	private FridgeDao fridgeDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private DailyChangeDao dailyChangeDao;
	
	
	@Test
	public void testUserDao() {
		assertTrue(userDao != null);
		User user = userDao.getUserById(1);
		if (user == null) {
			user = new User(1, "name", PasswordUtil.getEncryptedPassword("pw"), "54749110", UserRole.COMMON);
			userDao.save(user);
			user = userDao.getUserById(1);
			assertTrue(user != null);
			user = userDao.getUserByTel("54749110");
			assertTrue(user != null);
			user = userDao.getUserByUsername("name");
			assertTrue(user != null);
			assertEquals("name", user.getUsername());
			assertTrue(PasswordUtil.checkPassword("pw", user.getPassword()));
			assertEquals("54749110", user.getTel());
			assertEquals(UserRole.COMMON, user.getRole());
		}
	}

	@Test
	public void testFridgeDao() {
		assertTrue(fridgeDao != null);
		Fridge f = fridgeDao.getFridgeById(1);
		if (f == null) {
			f = new Fridge(1);
			fridgeDao.save(f);
			f = fridgeDao.getFridgeById(f.getFridgeId());
			assertTrue(f != null);
			assertEquals(1, f.getFridgeId());
		}
	}

	@Test
	public void testItemDao() {
		assertTrue(itemDao != null);
		Item i = itemDao.getItemById(1);
		if (i == null) {
			i = new Item(1, "apple", 1, 1);
			itemDao.save(i);
			Item item = itemDao.getItemById(i.getItemId());
			assertTrue(item != null);
			assertEquals("apple", item.getName());
			assertEquals(1, item.getCalories());
			assertEquals(1, item.getShelflife());
		}
	}
	
	@Test
	public void testDailyChangeDao() {
		assertTrue(dailyChangeDao != null);
		User u = new User(1, "name", PasswordUtil.getEncryptedPassword("pw"), "54749110", UserRole.COMMON);
		userDao.save(u);
		Item i = new Item(1, "apple", 1, 1);
		itemDao.save(i);
		Fridge f = new Fridge(1);
		fridgeDao.save(f);
		DailyChange dc = new DailyChange(f.getFridgeId(), i.getItemId(), u.getUserId(), 10, null);
		dailyChangeDao.save(dc);
		DailyChange testGet = dailyChangeDao.getDailyChangeById(dc.getChangeId());
		assertTrue(testGet != null);
		assertEquals(u.getUserId(), testGet.getUserId());
		assertEquals(f.getFridgeId(), testGet.getFridgeId());
		assertEquals(i.getItemId(), testGet.getItemId());
		assertEquals(10, testGet.getAmount());
		System.out.println(testGet.getTime());
	}
}
