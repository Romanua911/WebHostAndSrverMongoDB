package WebHost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		
		repository.deleteAll();
		
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));
		
		Customer p = new Customer("Joe", "Lin");
		Customer p2 = new Customer("Joe2", "Lin2");
		
		
		repository.insert(p);
		repository.insert(p2);
		
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}

		System.out.println("-------------------------------");
//		 fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		p=repository.findByFirstName("Alice");
		System.out.println(p);

		System.out.println("-------------------------------");
		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}

		System.out.println("-------------------------------");
		
		

		System.out.println("Update Joe2 Lin2 to Joe3 Lin3");
		
		p=repository.findByFirstName("Joe2");
		p.firstName="Joe3";
		p.lastName="Lin3";
		repository.save(p);	
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println("-------------------------------");
		
		System.out.println("DELETE Alice");
		System.out.println("-------------------------------");
		repository.delete(repository.findByFirstName("Alice"));
		
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}

		System.out.println("-------------------------------");
	}
}