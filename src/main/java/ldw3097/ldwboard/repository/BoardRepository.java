package ldw3097.ldwboard.repository;

import ldw3097.ldwboard.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, String> {
}
