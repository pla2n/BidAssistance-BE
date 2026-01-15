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
@RequestMapping("/api") // [수정 1] 공통 시작 주소인 "/api"만 남깁니다.
public class CommentController {
    private final CommentService commentService;

    // 1. 입찰(Bid) 댓글 작성
    @PostMapping("/bids/{bidId}/comments") // [수정 2] 여기서 구체적인 주소를 적어줍니다.
    public ResponseEntity<CommentResponse> createBidComment(
            @PathVariable("bidId") int bidId,
            @RequestBody CommentCreateRequest request
    ){
        CommentResponse response = commentService.createComment(bidId, null, request);
        return ResponseEntity.ok(response);
    }

    // 2. 입찰(Bid) 댓글 조회
    @GetMapping("/bids/{bidId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByBid(
            @PathVariable("bidId") int bidId
    ){
        List<CommentResponse> comments = commentService.getCommentsByBid(bidId);
        return ResponseEntity.ok(comments);
    }

    // 3. 게시글(Board) 댓글 작성
    @PostMapping("/boards/{boardId}/comments") // [수정 3] 이제 Board 주소도 자유롭게 쓸 수 있습니다.
    public ResponseEntity<CommentResponse> createBoardComment(
            @PathVariable("boardId") int boardId,
            @RequestBody CommentCreateRequest request
    ){
        CommentResponse response = commentService.createComment(null, boardId, request);
        return ResponseEntity.ok(response);
    }

    // 4. 게시글(Board) 댓글 조회
    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByBoard(
            @PathVariable("boardId") int boardId
    ){
        List<CommentResponse> comments = commentService.getCommentsByBoard(boardId);
        return ResponseEntity.ok(comments);
    }

    // 5. 댓글 삭제 (공통)
    @DeleteMapping("/comments/{commentId}") // [수정 4] 삭제는 보통 부모 ID 없이 댓글 ID로만 합니다.
    public ResponseEntity<String> deleteComment(
            @PathVariable int commentId,
            @RequestParam("userId") int userId
    ){
        commentService.deleteComment(commentId, userId);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }
}
