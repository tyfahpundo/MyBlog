package zw.co.afrosoft.myblog.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import zw.co.afrosoft.myblog.domain.Post;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;

    public static PostDto createPostDto(Post post){
        return new PostDto(post.getId(),post.getTitle(),post.getDescription(),post.getContent(),post.getComments()
                .stream()
                .map(CommentDto::createCommentDto)
                .collect(Collectors.toSet()));
    }
}
