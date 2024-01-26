package ldw3097.ldwboard.web;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ldw3097.ldwboard.domain.Board;
import ldw3097.ldwboard.domain.Comment;
import ldw3097.ldwboard.domain.Post;
import ldw3097.ldwboard.domain.User;
import ldw3097.ldwboard.repository.BoardRepository;
import ldw3097.ldwboard.repository.CommentRepository;
import ldw3097.ldwboard.repository.PostRepository;
import ldw3097.ldwboard.service.CommentService;
import ldw3097.ldwboard.service.PostService;
import ldw3097.ldwboard.web.form.CommentForm;
import ldw3097.ldwboard.web.form.PostingForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Enumeration;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("post")
@Slf4j
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @GetMapping("/{postId}")
    public String getPost(@PathVariable Long postId, Model model,
                          @SessionAttribute(name=SessionConst.LOGIN_USER, required = false)User user){
        Post post = postRepository.findById(postId).orElseThrow();
        model.addAttribute("targetPost", post);
        model.addAttribute("commentForm", new CommentForm());
        if(user != null && post.getWriter().getId().equals(user.getId()) ){
            model.addAttribute("isWriter", true);
        }
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
                          @SessionAttribute(name=SessionConst.LOGIN_USER)User user,
                          RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "newPost";
        }
        Board board = boardRepository.findById(boardId).orElseThrow();
        postService.savePost(postingForm.getTitle(), postingForm.getBody(), board, user);
        redirectAttributes.addAttribute("boardId", boardId);
        return "redirect:/board/{boardId}";
    }


    @PostMapping("/{postId}/addComment")
    public String addComment(@Valid @ModelAttribute CommentForm commentForm,
                             BindingResult bindingResult, @PathVariable Long postId,
                             @SessionAttribute(name=SessionConst.LOGIN_USER, required = false) User user, Model model){
        Post targetPost = postRepository.findById(postId).orElseThrow();
        if(bindingResult.hasErrors() || user == null){
            if(user == null){
                bindingResult.rejectValue("commentBody", "notLoggedIn", "로그인 해주세요.");
            }
            model.addAttribute("targetPost", targetPost);
            return "post";
        }
        commentService.saveComment(commentForm.getCommentBody(), targetPost, user);
        return "redirect:/post/{postId}";
    }

    @GetMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId,
                             @SessionAttribute(name=SessionConst.LOGIN_USER) User user,
                             RedirectAttributes redirectAttributes){
        Post post = postRepository.findById(postId).orElseThrow();
        if(post.getWriter().getId().equals(user.getId()) ){
            postService.deletePost(post);
        }

        redirectAttributes.addAttribute("boardId", post.getBoard().getId());
        return "redirect:/board/{boardId}";
    }

    @GetMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, Model model){
        Post post = postRepository.findById(postId).orElseThrow();
        PostingForm postingForm = new PostingForm();
        postingForm.setTitle(post.getTitle());
        postingForm.setBody(post.getBody());
        model.addAttribute("postingForm", postingForm);
        model.addAttribute("boardId", post.getBoard().getId());
        return "editPost";
    }

    @PostMapping("/{postId}/edit")
    public String doEditPost(@PathVariable Long postId, @Valid @ModelAttribute PostingForm postingForm, BindingResult bindingResult,
                             @SessionAttribute(name=SessionConst.LOGIN_USER) User user,
                             RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "editPost";}
        Post post = postRepository.findById(postId).orElseThrow();
        if(post.getWriter().getId().equals(user.getId())){
            postService.update(post, postingForm.getTitle(), postingForm.getBody());
        }
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/post/{postId}";
    }

    @GetMapping("/editComment")
    public String editComment(@RequestParam Long commentId,
                              @Validated @ModelAttribute CommentForm commentForm, BindingResult bindingResult,
                              @SessionAttribute(name=SessionConst.LOGIN_USER) User user,
                              RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "redirect:/";
        }
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if (comment.getWriter().getId().equals(user.getId()) ){
            comment.setBody(commentForm.getCommentBody());
        }
        redirectAttributes.addAttribute("postId", comment.getPost().getId());
        return "redirect:/post/{postId}";
    }

    @GetMapping("/deleteComment")
    public String deleteComment(@RequestParam Long commentId, @SessionAttribute(name=SessionConst.LOGIN_USER) User user,
                                RedirectAttributes redirectAttributes){
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if(comment.getWriter().getId().equals(user.getId())){
            commentService.deleteComment(comment);
        }
        redirectAttributes.addAttribute("postId", comment.getPost().getId());
        return "redirect:/post/{postId}";
    }



}
