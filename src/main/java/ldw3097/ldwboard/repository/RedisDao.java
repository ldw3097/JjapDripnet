package ldw3097.ldwboard.repository;

import ldw3097.ldwboard.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisDao {
    private final RedisTemplate<String, Integer> redisTemplate;
    private final PostRepository postRepository;

    public void incrementViewCount(String postId){
        String redisKey = "post:views:"+postId;
        redisTemplate.opsForValue().increment(redisKey);

    }

//    public Integer getViewCount(Long postId){
//        String redisKey = "post:views:"+postId;
//        Integer viewCnt;
//
//        if (redisTemplate.hasKey(redisKey)){
//            viewCnt = redisTemplate.opsForValue().get(redisKey);
//        }else{
//            viewCnt = postRepository.findById(postId).orElseThrow().getViewCount();
//
//        }
//    }


}
