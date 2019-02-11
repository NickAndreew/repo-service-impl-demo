package ciklum.task;

import ciklum.task.impl.IUserImpl;
import ciklum.task.impl.IUserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskImplementationApplication implements CommandLineRunner {

	@Autowired
	IUserServiceImpl service;

	public static void main(String[] args) {
		SpringApplication.run(TaskImplementationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.save(new IUserImpl("1", "1", "One"));
		service.save(new IUserImpl("2", "2", "Two"));
		service.save(new IUserImpl("3", "2", "Three"));

		service.delete("2");
		service.findAll();
		service.findById("1");
		System.out.println(service.findAll().toString());
	}
}

