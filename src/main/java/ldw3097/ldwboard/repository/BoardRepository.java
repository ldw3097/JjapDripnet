package ldw3097.ldwboard.repository;

import jakarta.persistence.EntityManager;
import ldw3097.ldwboard.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    @Transactional
    public void save (Board board){
        em.persist(board);
    }

    public Board findBoard(String name){
        return em.find(Board.class, name);

    }

    @Transactional
    public void delete(Board board){
        em.remove(board);
    }



}
