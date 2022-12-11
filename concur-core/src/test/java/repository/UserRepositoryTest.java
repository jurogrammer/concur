package repository;

import concur.core.entity.User;
import concur.core.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void test() {
        User hello = userRepository.save(new User("hello", 10));
        entityManager.flush();
        User user = entityManager.find(User.class, hello.getId());
        System.out.println("user = " + user);
    }
}