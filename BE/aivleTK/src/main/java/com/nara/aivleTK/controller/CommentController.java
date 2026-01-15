package com.nara.aivleTK.controller;

import com.nara.aivleTK.dto.comment.CommentCreateRequest;
import com.nara.aivleTK.dto.comment.CommentResponse;
import com.nara.aivleTK.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bids/{bidId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/bids/{bidId}/comments")
    public ResponseEntity<CommentResponse> createBidComment(
            @PathVariable("bidId") int bidId,
            @RequestBody CommentCreateRequest request
    ){
        CommentResponse response = commentService.createComment(bidId, null, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bids/{bidId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByBid(
            @PathVariable("bidId") int bidId
    ){
        List<CommentResponse> comments = commentService.getCommentsByBid(bidId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/boards/{boardId}/comments")
    public ResponseEntity<CommentResponse> createBoardComment(
            @PathVariable("boardId") int boardId,
            @RequestBody CommentCreateRequest request
    ){
        CommentResponse response = commentService.createComment(null, boardId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByBoard(
            @PathVariable("boardId") int boardId
    ){
        // 서비스에 Board용 조회 메서드 필요 (ex: findAllByBoard_Id)
        List<CommentResponse> comments = commentService.getCommentsByBoard(boardId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable int commentId,
            @RequestParam("userId") int userId
    ){
        commentService.deleteComment(commentId,userId);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }
}
