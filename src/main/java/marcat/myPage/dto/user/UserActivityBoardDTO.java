package marcat.myPage.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import marcat.board.vo.Board;
import marcat.board.vo.BoardImages;
import marcat.goods.vo.GoodsImages;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Getter
@Setter
@ToString
public class UserActivityBoardDTO {

        private Long boardId;
        private Long memberId;
        private String nickname;
        private String title;
        private String picture;
        private String contents;
        private int viewCount;
        private String createTime;
        private String status;
        private BoardImages boardImages;
        private List<GoodsImages> images;
        private String memberImages;





        public UserActivityBoardDTO(Board board) {
            this.boardId = board.getId();
            this.memberId = board.getMemberId();
            this.picture = board.getBoardImages().getSavedFileName();
            this.nickname = board.getNickname();
            this.title = board.getTitle();
            this.contents = board.getContents();
            this.viewCount = board.getViewCount();
            this.createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(board.getCreateTime());
            this.status = board.getStatus();
            this.boardImages = board.getBoardImages();
            this.memberImages = board.getMemberImages().getSavedFileName();

        }

    }
