package zw.co.afrosoft.myblog.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.myblog.domain.Post;
import zw.co.afrosoft.myblog.dtos.PostDto;
import zw.co.afrosoft.myblog.exception.ResourceNotFoundException;
import zw.co.afrosoft.myblog.repository.PostRepository;
import zw.co.afrosoft.myblog.service.PostService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        postRepository.save(post);
        return PostDto.createPostDto(post);
    }

    @Override
    public Page<PostDto> getAllPosts(Pageable pageable) {
        List<PostDto> postList = postRepository.findAll(pageable).stream()
                .map(PostDto::createPostDto)
                .collect(Collectors.toList());
        return new PageImpl<>(postList, pageable,postList.size());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return PostDto.createPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post postUpdate = postRepository.save(post);
        return PostDto.createPostDto(postUpdate);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
    }

}
