package ldw3097.ldwboard.web;

import ldw3097.ldwboard.domain.Post;
import ldw3097.ldwboard.dto.PostInfoDto;
import ldw3097.ldwboard.repository.PostRepository;
import ldw3097.ldwboard.repository.PostSearchKey;
import ldw3097.ldwboard.service.BoardService;
import ldw3097.ldwboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping({"/{boardId}"})
    public String board(@PathVariable String boardId, @RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam Optional<PostSearchKey> postSearchKey, @RequestParam Optional<String> postSearchVal, Model model){
        PageRequest pageRequest = PageRequest.of(pageNum-1, 10, Sort.by(Sort.Direction.DESC, "id"));

        Page<PostInfoDto> posts;
        if(postSearchKey.isPresent() && postSearchVal.isPresent()){
            posts = boardService.searchPage(boardId, postSearchKey.get(), postSearchVal.get(), pageRequest);
            model.addAttribute("postSearchKey", postSearchKey.get());
            model.addAttribute("postSearchVal", postSearchVal.get());
        }else{
            posts = boardService.getPage(boardId, pageRequest);
        }



        model.addAttribute("posts", posts);
        int start = Math.max(1, pageNum - 4);
        int end = Math.min(posts.getTotalPages(), 8+start );
        List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().toList();
        model.addAttribute("pageNumbers", pageNumbers);

        return "board";
    }

}
