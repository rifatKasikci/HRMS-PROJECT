package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.abstracts.User;

public interface UserDao extends JpaRepository<User, Integer>{

	List<User> findAllByEmail(String email);
}
