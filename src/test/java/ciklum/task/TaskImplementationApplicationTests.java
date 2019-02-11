package ciklum.task;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

import ciklum.task.impl.IUserImpl;
import ciklum.task.impl.IUserServiceImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskImplementationApplicationTests {

	@Autowired
	public IUserServiceImpl service;

	@Before
	public void setUp() {
		List<IUserImpl> users = new ArrayList<IUserImpl>();
		users.add(new IUserImpl("1", "1", "Name"));
		users.add(new IUserImpl("2", "1", "Name1"));
		users.add(new IUserImpl("3", "1", "Name2"));
		users.add(new IUserImpl("4", "4", "Name3"));
		users.forEach(u -> service.save(u));
	}

	@After
	public void breakDown(){
		service.findAll().forEach(u -> service.delete(u.getId()));
	}

	@Test
	public void testFindAll() {
		List<IUserImpl> users = new ArrayList<IUserImpl>();
		users.add(new IUserImpl("1", "1", "Name"));
		users.add(new IUserImpl("2", "1", "Name1"));
		users.add(new IUserImpl("3", "1", "Name2"));
		users.add(new IUserImpl("4", "4", "Name3"));

		assertEquals(users, service.findAll());
	}

	@Test
	public void testFindById() {
		assertEquals(Arrays.asList(new IUserImpl("4", "4", "Name3")), service.findById("4"));
	}

	@Test
	public void testSave() {
		assertEquals(new IUserImpl("5", "4", "Name4"), service.save(new IUserImpl("5", "4", "Name4")));
		assertEquals(new IUserImpl("5", "4", "Name4"),service.delete("5"));
	}

	@Test
	public void testSaveAll() {
		Set<IUserImpl> usersToSave = new HashSet<IUserImpl>();
		usersToSave.add(new IUserImpl("6", "4", "Name5"));
		usersToSave.add(new IUserImpl("7", "4", "Name6"));

		List<IUserImpl> usersInDb = new ArrayList<>();
		usersInDb.add(new IUserImpl("6", "4", "Name5"));
		usersInDb.add(new IUserImpl("7", "4", "Name6"));

		assertEquals(usersInDb, service.saveAll(usersToSave));
	}

	@Test
	public void testDelete() {
		List<IUserImpl> users = new ArrayList<IUserImpl>();
		users.add(new IUserImpl("1", "1", "Name"));
		users.add(new IUserImpl("2", "1", "Name1"));
		users.add(new IUserImpl("3", "1", "Name2"));

		assertEquals(new IUserImpl("4", "4", "Name3"), service.delete("4"));
		assertEquals(users, service.findAll());
	}

	@Test
	public void testFindAllGroupByGroupId() {
		List<IUserImpl> firstGroup = new ArrayList<>();
		firstGroup.add(new IUserImpl("1", "1", "Name"));
		firstGroup.add(new IUserImpl("2", "1", "Name1"));
		firstGroup.add(new IUserImpl("3", "1", "Name2"));

		List<IUserImpl> secondGroup = new ArrayList<>();
		secondGroup.add(new IUserImpl("4", "4", "Name3"));

		Map<String, List<IUserImpl>> groups = new HashMap<>();
		groups.put("1", firstGroup);
		groups.put("4", secondGroup);

		assertEquals(groups, service.findAllGroupByGroupId());
	}
}

