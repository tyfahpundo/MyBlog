package zw.co.afrosoft.myblog.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import zw.co.afrosoft.myblog.domain.Comment;

@Data
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;

    public static CommentDto createCommentDto(Comment comment){
        return new CommentDto(comment.getId(), comment.getName(), comment.getEmail(), comment.getBody());
    }
}
