package zw.co.afrosoft.myblog.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import zw.co.afrosoft.myblog.domain.Comment;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CommentDto {
    private Long id;
    @NotEmpty(message = "Name should not be null or empty")
    private String name;
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;
    @NotEmpty
    @Size(min = 10,message = "Comment body must be at least 10 characters")
    private String body;

    public static CommentDto createCommentDto(Comment comment){
        return new CommentDto(comment.getId(), comment.getName(), comment.getEmail(), comment.getBody());
    }
}
