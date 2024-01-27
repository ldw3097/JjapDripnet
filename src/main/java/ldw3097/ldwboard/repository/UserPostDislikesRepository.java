package ldw3097.ldwboard.repository;

import ldw3097.ldwboard.domain.UserPostDislikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostDislikesRepository extends JpaRepository<UserPostDislikes, Long> {

    public boolean existsByUserIdAndPostId(String userId, Long postId);

    public void deleteByUserIdAndPostId(String userId, Long postId);
}
