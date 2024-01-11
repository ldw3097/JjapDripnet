package ldw3097.ldwboard.web;

import ldw3097.ldwboard.domain.Post;
import ldw3097.ldwboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {
    private final PostService postService;

    @GetMapping({"/{boardId}/{pageNum}"})
    public String board(@PathVariable String boardId, @PathVariable Integer pageNum, Model model){
        List<Post> posts = postService.postPage(boardId, pageNum);
        model.addAttribute("posts", posts);
        List<Integer> nextPageNum = getPageNum(boardId, pageNum);
        model.addAttribute("nextPageNums", nextPageNum);
        return "board";
    }

    private List<Integer> getPageNum(String boardId, int pageNum) {
        int totalPage = postService.getTotalPageNum(boardId);
        int startPageNum = pageNum - 4;
        if(startPageNum < 1) startPageNum = 1;
        int endPageNum = startPageNum + 8;
        if(endPageNum > totalPage) endPageNum = totalPage;
        List<Integer> nextPageNum = new ArrayList<>();
        for(int i = startPageNum; i <= endPageNum; i++){
            nextPageNum.add(i);
        }
        return nextPageNum;
    }
}
