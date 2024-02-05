package ldw3097.ldwboard.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import ldw3097.ldwboard.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ldw3097.ldwboard.domain.QPost.post;
import static ldw3097.ldwboard.domain.QUser.user;

@Repository
public class ComplexPostRepository {
    private final JPAQueryFactory queryFactory;

    public ComplexPostRepository(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Page<Post> searchPost(String boardId, PostSearchKey searchKey, String searchVal, Pageable pageable) {
        List<Post> queryResult = queryFactory
                .selectFrom(post)
                .join(post.writer, user)
                .where(post.board.id.eq(boardId),
                       searchCriteria(searchKey, searchVal) )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.id.desc())
                .fetch();

        JPAQuery<Long> queryCount = queryFactory
                .select(post.count())
                .from(post)
                .join(post.writer, user)
                .where(post.board.id.eq(boardId),searchCriteria(searchKey, searchVal));

        return PageableExecutionUtils.getPage(queryResult, pageable, queryCount::fetchOne);

    }

    private BooleanExpression searchCriteria(PostSearchKey searchKey, String searchVal){
        return switch (searchKey) {
            case TITLE -> post.title.contains(searchVal);
            case BODY -> post.body.contains(searchVal);
            case WRITER -> post.writer.id.contains(searchVal);
            case TITLEBODY -> post.title.contains(searchVal).or(post.body.contains(searchVal));
        };
    }


}
