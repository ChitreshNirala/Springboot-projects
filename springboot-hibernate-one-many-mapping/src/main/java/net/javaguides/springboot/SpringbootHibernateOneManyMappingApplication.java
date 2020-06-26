package net.javaguides.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.javaguides.springboot.entity.Comment;
import net.javaguides.springboot.entity.Post;
import net.javaguides.springboot.repository.PostRepository;

@SpringBootApplication
public class SpringbootHibernateOneManyMappingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHibernateOneManyMappingApplication.class, args);
	}

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Comment comment1= new Comment("coment 1");
		Comment comment2= new Comment("coment 2");
		Comment comment3= new Comment("coment 3");
		
		Post post = new Post("one to many mapping", "one to many mapping using JPA hibernate ");
		post.getComments().add(comment1);
		post.getComments().add(comment2);
		post.getComments().add(comment3);
		
		this.postRepository.save(post);
	}

}
