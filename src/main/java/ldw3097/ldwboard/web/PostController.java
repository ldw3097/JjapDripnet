package ldw3097.ldwboard.web;

import jakarta.validation.Valid;
import ldw3097.ldwboard.domain.Post;
import ldw3097.ldwboard.domain.User;
import ldw3097.ldwboard.repository.BoardRepository;
import ldw3097.ldwboard.repository.PostRepository;
import ldw3097.ldwboard.service.CommentService;
import ldw3097.ldwboard.service.PostService;
import ldw3097.ldwboard.web.form.CommentForm;
import ldw3097.ldwboard.web.form.PostingForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("post")
@Slf4j
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final CommentService commentService;

    @GetMapping("/{postId}")
    public String getPost(@PathVariable Long postId, Model model){
        model.addAttribute("targetPost", postRepository.findOne(postId));
        model.addAttribute("commentForm", new CommentForm());
        return "post";
    }

    @GetMapping("/addnew/{boardId}")
    public String addPost(@PathVariable String boardId, Model model){
        model.addAttribute("postingForm", new PostingForm());
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
        postService.savePost(postingForm, boardRepository.findBoard(boardId), user);
        return "redirect:/board/%s/1".formatted(boardId);
    }


    @PostMapping("/{postId}/addComment")
    public String addComment(@Valid @ModelAttribute CommentForm commentForm,
                             BindingResult bindingResult, @PathVariable Long postId,
                             @SessionAttribute(name=SessionConst.LOGIN_USER, required = false) User user, Model model){
        if(bindingResult.hasErrors() || user == null){
            if(user == null){
                bindingResult.rejectValue("commentBody", "notLoggedIn", "로그인 해주세요.");
            }
            Post targetPost = postRepository.findOne(postId);
            model.addAttribute("targetPost", targetPost);
            return "post";
        }
        commentService.saveComment(commentForm, postRepository.findOne(postId), user);
        return "redirect:/post/%d".formatted(postId);
    }




}
