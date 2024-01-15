package com.socialmedia.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.socialmedia.model.Post;
import com.socialmedia.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Value("${jsonplaceholder.api.posts}")
	private String postsApi;
	
	public List<Post> getAllPosts() {
        ResponseEntity<Post[]> responseEntity = new RestTemplate().getForEntity(postsApi, Post[].class);
        Post[] posts = responseEntity.getBody();

        postRepository.saveAll(Arrays.asList(posts));

        return Arrays.asList(posts);
    }
	
	public List<Post> fetchAndSavePosts() {
        ResponseEntity<Post[]> responseEntity = new RestTemplate().getForEntity(postsApi, Post[].class);
        Post[] posts = responseEntity.getBody();

        postRepository.saveAll(Arrays.asList(posts));

        return Arrays.asList(posts);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseGet(() -> {
            String postApiUrl = postsApi + "/" + id;
            ResponseEntity<Post> responseEntity = new RestTemplate().getForEntity(postApiUrl, Post.class);
            Post post = responseEntity.getBody();

            if (post != null) {
                postRepository.save(post);
            }

            return post;
        });
    }

    public Post createPost(Post post) {
        // You may want to implement validation and additional logic here
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post updatedPost) {
        return postRepository.findById(id).map(post -> {
            post.setTitle(updatedPost.getTitle());
            post.setBody(updatedPost.getBody());
            // Update other fields as needed
            return postRepository.save(post);
        }).orElse(null);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

}
