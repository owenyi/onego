package com.encore.backend.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.encore.backend.repository.board.BoardRepository;
import com.encore.backend.vo.Board;
import com.encore.backend.vo.Comment;
import com.encore.backend.vo.Content;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private BoardRepository repo;

    @Autowired
    public BoardService(BoardRepository repo) {
        this.repo = repo;
    }

    public List<Board> selectBoard(Map<String, Object> parameters) {
        String boardId = (String) parameters.get("boardId");
        Page<Board> page = null;
        if (boardId != null) {
            page = repo.findById(boardId, PageRequest.of(0, 1));
        } else {
            int pageNumber = Integer.parseInt((String) parameters.get("pageNumber")) - 1;
            String title = (String) parameters.get("boardTitle");
            String nickName = (String) parameters.get("nickName");
            String tag = (String) parameters.get("tag");
            if (title != null) {
                page = repo.findByTitleLikeOrderByModDatetimeDesc(title, PageRequest.of(pageNumber, 5));
            } else if (nickName != null) {
                page = repo.findByNickNameLikeOrderByModDatetimeDesc(nickName, PageRequest.of(pageNumber, 5));
            } else if (tag != null) {
                page = repo.findByTagsLikeOrderByModDatetimeDesc(tag, PageRequest.of(pageNumber, 5));
            } else {
                page = repo.findAllByOrderByModDatetimeDesc(PageRequest.of(pageNumber, 5));
            }
        }
        return page.getContent();
    }

    public Long getBoardsCount(Map<String, Object> parameters) {
        Long result = -1L;
        String title = (String) parameters.get("boardTitle");
        String nickName = (String) parameters.get("nickName");
        String tag = (String) parameters.get("tag");
        System.out.println(title);
        if (title != null && nickName != null && tag != null) {
            result = repo.countByTitleLikeOrNickNameOrTagsIn(title, nickName, tag);
        } else if (title != null) {
            result = repo.countByTitleLike(title);
        } else if (nickName != null) {
            result = repo.countByNickNameLike(nickName);
        } else if (tag != null) {
            result = repo.countByTagsIn(tag);
        } else {
            result = repo.count();
        }
        return result;
    }

    public boolean insertBoard(Board board) {
        board.setModDatetime(Calendar.getInstance().getTime());
        if (repo.insert(board) == null)
            return false;
        return true;
    }

    public boolean updateBoard(Map<String, Object> parameters) {
        return repo.updateBoard((String) parameters.get("boardId"), (List<Content>) parameters.get("contents"));
    }

    public boolean deleteBoard(String boardId) {
        try {
            repo.deleteById(boardId);
            return repo.existsById(boardId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Comment> getComment(Map<String, Object> parameters) {
        int pageNumber = Integer.parseInt((String) parameters.get("pageNumber"));
        int start = (pageNumber - 1) * 10;
        int end = pageNumber * 10;
        List<Board> board = repo.findCommentsById((String) parameters.get("boardId"), start, end);
        List<Comment> result = new ArrayList<Comment>();
        for (Comment c : board.get(0).getComments()) {
            result.add(c);
        }
        return result;
    }

    public boolean insertComment(Map<String, Object> parameters) {
        String boardId = (String) parameters.get("boardId");
        Calendar cal = Calendar.getInstance();
        Comment comment = new Comment(new ObjectId().toString(), (String) parameters.get("userEmail"),
                (String) parameters.get("nickName"), (String) parameters.get("comment"), cal.getTime());
        return repo.insertComment(boardId, comment);
    }

    public boolean removeComment(Map<String, Object> parameters) {
        String boardId = (String) parameters.get("boardId");
        String commentId = (String) parameters.get("commentId");
        return repo.removeComment(boardId, commentId);
    }

    public boolean updateComment(Map<String, Object> parameters) {
        String boardId = (String) parameters.get("boardId");
        String commentId = (String) parameters.get("commentId");
        String comment = (String) parameters.get("comment");
        String nickName = (String) parameters.get("nickName");
        String userId = (String) parameters.get("userEmail");
        Date modDatetime = new Date();
        Comment commentData = new Comment(commentId, userId, nickName, comment, modDatetime);
        return repo.updateComment(boardId, commentData);
    }
}
