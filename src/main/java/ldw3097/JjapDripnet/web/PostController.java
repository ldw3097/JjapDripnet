package ldw3097.JjapDripnet.web;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ldw3097.JjapDripnet.domain.Comment;
import ldw3097.JjapDripnet.domain.Post;
import ldw3097.JjapDripnet.domain.User;
import ldw3097.JjapDripnet.repository.BoardRepository;
import ldw3097.JjapDripnet.repository.CommentRepository;
import ldw3097.JjapDripnet.repository.PostRepository;
import ldw3097.JjapDripnet.service.PostService;
import ldw3097.JjapDripnet.web.form.CommentForm;
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
    private final CommentRepository commentRepository;

    @GetMapping("/{postId}")
    public String getPost(@PathVariable Long postId, Model model){
        Post targetPost = postRepository.findOne(postId);
        model.addAttribute("targetPost", targetPost);

        CommentForm commentForm = new CommentForm();
        model.addAttribute("commentForm", commentForm);
        log.info("create time: {}", targetPost.getCreateTime());
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
        if(bindingResult.hasErrors()){
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

    @PostMapping("/{postId}/addComment")
    public String addComment(@Valid @ModelAttribute CommentForm commentForm,
                             BindingResult bindingResult, @PathVariable Long postId,
                             @SessionAttribute(name=SessionConst.LOGIN_USER, required = false) User user, Model model){
        if(bindingResult.hasErrors()){
            Post targetPost = postRepository.findOne(postId);
            model.addAttribute("targetPost", targetPost);
            return "post";
        }
        if(user == null){
            log.info("user is null");
            bindingResult.rejectValue("commentBody", "notLoggedIn", "로그인 해주세요.");
            Post targetPost = postRepository.findOne(postId);
            model.addAttribute("targetPost", targetPost);
            return "post";
        }

        Comment comment = new Comment();
        comment.setBody(commentForm.getCommentBody());
        comment.setPost(postRepository.findOne(postId));
        comment.setCreateTime(LocalDateTime.now());
        comment.setUser(user);
        commentRepository.save(comment);
        return "redirect:/post/%d".formatted(postId);
    }




}
