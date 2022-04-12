package zw.co.afrosoft.myblog.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import zw.co.afrosoft.myblog.domain.Post;

@Data
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;

    public static PostDto createPostDto(Post post){
        return new PostDto(post.getId(),post.getTitle(),post.getDescription(),post.getContent());
    }
}
