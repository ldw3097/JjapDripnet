package ldw3097.ldwboard.repository;

import ldw3097.ldwboard.domain.UserPostLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostLikesRepository extends JpaRepository<UserPostLikes, Long> {

    public boolean existsByUserIdAndPostId(String userId, Long postId);

    public void deleteByUserIdAndPostId(String userId, Long postId);

}
