package ldw3097.ldwboard.repository;

import ldw3097.ldwboard.domain.Post;
import ldw3097.ldwboard.domain.User;
import ldw3097.ldwboard.domain.UserPostLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostLikesRepository extends JpaRepository<UserPostLikes, Long> {

    public boolean existsByUserAndPost(User user, Post post);

    public void deleteByUserAndPost(User user, Post post);

}
