package zw.co.afrosoft.myblog.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.myblog.dtos.PostDto;
import zw.co.afrosoft.myblog.service.PostService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/posts/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("create")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    @GetMapping("getAll")
    public ResponseEntity<Page<PostDto>> getAllPosts(@PageableDefault(size = 8) Pageable pageable){
        return new ResponseEntity<>(postService.getAllPosts(pageable), HttpStatus.OK);
    }
    @GetMapping("getById/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable Long id){
        return new ResponseEntity<>(postService.updatePost(postDto,id),HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted Successfully",HttpStatus.OK);
    }
}
