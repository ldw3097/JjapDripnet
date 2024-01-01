package ldw3097.JjapDripnet.web;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ldw3097.JjapDripnet.domain.Post;
import ldw3097.JjapDripnet.domain.User;
import ldw3097.JjapDripnet.repository.BoardRepository;
import ldw3097.JjapDripnet.repository.PostRepository;
import ldw3097.JjapDripnet.service.PostService;
import ldw3097.JjapDripnet.web.form.PostingForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("post")
@Slf4j
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    @GetMapping("/{postId}")
    public String getPost(@PathVariable Long postId, Model model){
        Post targetPost = postRepository.findOne(postId);
        model.addAttribute("targetPost", targetPost);
        return "post";
    }

    @GetMapping("/addnew/{boardId}")
    public String addPost(@PathVariable String boardId, Model model){
        PostingForm postingForm = new PostingForm();
        model.addAttribute("postingForm", postingForm);
        model.addAttribute("boardId", boardId);
        return "newPost";
    }

    @PostMapping("/addnew/{boardId}")
    public String addPost(@Valid @ModelAttribute PostingForm postingForm,
                          BindingResult bindingResult, @PathVariable String boardId,
                          @SessionAttribute(name=SessionConst.LOGIN_USER)User user){
        log.info("posting form: {}", postingForm);
        if(bindingResult.hasErrors()){
            log.info("binding result has error");
            return "newPost";
        }
        Post post = new Post();
        post.setTitle(postingForm.getTitle());
        post.setUser(user);
        post.setCreateTime(LocalDateTime.now());
        post.setBody(postingForm.getBody());
        post.setBoard(boardRepository.findBoard(boardId));
        postRepository.save(post);
        return "redirect:/board/%s/1".formatted(boardId);
    }


}
