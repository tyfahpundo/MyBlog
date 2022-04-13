package zw.co.afrosoft.myblog.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import zw.co.afrosoft.myblog.domain.Post;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PostDto {
    private Long id;
    @NotEmpty
    @Size(min = 2,message = "title should have at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

    public static PostDto createPostDto(Post post){
        return new PostDto(post.getId(),post.getTitle(),post.getDescription(),post.getContent(),post.getComments()
                .stream()
                .map(CommentDto::createCommentDto)
                .collect(Collectors.toSet()));
    }
}
