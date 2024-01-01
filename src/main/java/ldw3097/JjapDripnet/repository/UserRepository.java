package ldw3097.JjapDripnet.repository;

import jakarta.persistence.EntityManager;
import ldw3097.JjapDripnet.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    @Transactional
    public void save(User user){

        em.persist(user);

    }

    public User findOne(String id){
        return em.find(User.class, id);
    }



}
