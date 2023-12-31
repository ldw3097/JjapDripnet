package ldw3097.JjapDripnet.web;

import ldw3097.JjapDripnet.domain.Post;
import ldw3097.JjapDripnet.repository.PostRepository;
import ldw3097.JjapDripnet.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("post")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping("/{postId}")
    public String getPost(@PathVariable Long postId, Model model){
        Post targetPost = postRepository.findOne(postId);
        model.addAttribute("targetPost", targetPost);
        return "post";
    }


}
