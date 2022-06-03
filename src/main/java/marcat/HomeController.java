package marcat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.board.dto.BoardDTO;
import marcat.board.service.BoardService;
import marcat.board.vo.Board;
import marcat.goods.dto.GoodsDTO;
import marcat.goods.service.GoodsService;
import marcat.goods.vo.Goods;
import marcat.managerpage.dto.AdViewDTO;
import marcat.managerpage.service.ManagerPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final GoodsService goodsService;
    private final BoardService boardService;
    private final ManagerPageService managerPageService;

    @GetMapping
    public String main(Model model, Principal principal) {
        String userId = "";
        if (principal != null) {
            userId = principal.getName();
        }
        List<GoodsDTO> sellingGoodsList = new ArrayList<>();

        List<Goods> goods = goodsService.latestSixGoods(userId);
        if (!goods.isEmpty()) {
            for (Goods g : goods) {
                GoodsDTO goodsDTO = new GoodsDTO().getGoodsDTO(g);
                sellingGoodsList.add(goodsDTO);
            }
        }

        List<BoardDTO> boardList = new ArrayList<>();
        List<Board> boards = boardService.latestFourBoard(userId);
        if (!boards.isEmpty()) {
            for (Board b : boards) {
                BoardDTO boardDTO = new BoardDTO().getBoard(b);
                boardList.add(boardDTO);
            }
        }

        List<AdViewDTO> adList = managerPageService.adView();

        model.addAttribute("sellingGoodsList", sellingGoodsList);
        model.addAttribute("boardList", boardList);
        model.addAttribute("adList", adList);

        return "home";
    }

    @GetMapping("/favicon.ico")
    public void favicon(HttpServletResponse response) {
        try {
            response.sendRedirect("/resources/favicon.ico");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/apple-touch-icon.png")
    public void appleTouchIcon(HttpServletResponse response) {
        try {
            response.sendRedirect("/resources/img/apple-touch-icon.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/apple-touch-icon-precomposed.png")
    public void appleTouchIconPrecomposed(HttpServletResponse response) {
        try {
            response.sendRedirect("/resources/img/apple-touch-icon-precomposed.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
