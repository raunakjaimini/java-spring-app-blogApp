package org.martynas.blogapp.controller;

import org.martynas.blogapp.model.BlogUser;
import org.martynas.blogapp.model.Post;
import org.martynas.blogapp.service.BlogUserService;
import org.martynas.blogapp.service.CloudinaryService;
import org.martynas.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@SessionAttributes("post")
public class PostController {

    private final PostService postService;
    private final BlogUserService blogUserService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public PostController(PostService postService, BlogUserService blogUserService, CloudinaryService cloudinaryService) {
        this.postService = postService;
        this.blogUserService = blogUserService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model, Principal principal) {

        // Just curious  what if we get username from Principal instead of SecurityContext
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        // the end of curiosity //

//        // get username of current logged in session user
//        String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // find post by id
        Optional<Post> optionalPost = this.postService.getById(id);
        // if post exist put it in model
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            // Check if current logged in user is owner and let view template know to take according actions
            if (authUsername.equals(post.getUser().getUsername())) {
                model.addAttribute("isOwner", true);
            }
            return "post";
        } else {
            return "404";
        }
    }

    @Secured("ROLE_USER")
    @GetMapping("/createNewPost")
    public String createNewPost(Model model, Principal principal) {

        // Just curious  what if we get username from Principal instead of SecurityContext
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        // the end of curiosity //

//        // get username of current logged in session user
//        String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // find user by username
        Optional<BlogUser> optionalBlogUser = this.blogUserService.findByUsername(authUsername);
        // set user to post and put former in model
        if (optionalBlogUser.isPresent()) {
            Post post = new Post();
            post.setUser(optionalBlogUser.get());
            model.addAttribute("post", post);
            return "postForm";
        } else {
            return "error";
        }
    }

    @Secured("ROLE_USER")
    @PostMapping("/createNewPost")
    public String createNewPost(@Valid @ModelAttribute Post post,
                                BindingResult bindingResult,
                                @RequestParam("image") MultipartFile imageFile,
                                SessionStatus sessionStatus,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "postForm";
        }

        // Handle image upload via Cloudinary
        if (!imageFile.isEmpty()) {
            String imageUrl = cloudinaryService.uploadImage(imageFile);
            post.setImageUrl(imageUrl);
        }

        // Save the post
        this.postService.save(post);

        // Add success message to redirect attributes
        redirectAttributes.addFlashAttribute("successMessage", "Post added successfully!");

        sessionStatus.setComplete();
        return "redirect:/post/" + post.getId();
    }

    @Secured("ROLE_USER")
    @GetMapping("editPost/{id}")
    public String editPost(@PathVariable Long id, Model model, Principal principal) {
        // Just curious  what if we get username from Principal instead of SecurityContext
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        // the end of curiosity //

//        // get username of current logged in session user
//        String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Post> optionalPost = this.postService.getById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (authUsername.equals(post.getUser().getUsername())) {
                model.addAttribute("post", post);
                System.err.println("EDIT post: " + post); // for testing debugging purposes
                return "postForm";
            } else {
                System.err.println("Current User has no permissions to edit anything on post by id: " + id); // for testing debugging purposes
                return "403";
            }
        } else {
            System.err.println("Could not find a post by id: " + id); // for testing debugging purposes
            return "error";
        }
    }

    @Secured("ROLE_USER")
    @PostMapping("editPost/{id}")
    public String editPost(@PathVariable Long id,
                           @Valid @ModelAttribute Post post,
                           BindingResult bindingResult,
                           @RequestParam("image") MultipartFile imageFile,
                           SessionStatus sessionStatus,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "postForm";
        }

        // Handle image upload via Cloudinary
        if (!imageFile.isEmpty()) {
            String imageUrl = cloudinaryService.uploadImage(imageFile);
            post.setImageUrl(imageUrl);
        }

        // Save the updated post
        this.postService.save(post);

        // Add success message to redirect attributes
        redirectAttributes.addFlashAttribute("successMessage", "Post updated successfully!");

        sessionStatus.setComplete();
        return "redirect:/post/" + post.getId();
    }

    @Secured("ROLE_USER")
    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable Long id, Principal principal) {

        // Just curious  what if we get username from Principal instead of SecurityContext
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        // the end of curiosity //

//        // get username of current logged in session user
//        String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // find post by id
        Optional<Post> optionalPost = this.postService.getById(id);
        // Check if current logged in user is an owner and so has the right for modifications to happen
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            // Check if current logged in user is owner
            if (authUsername.equals(post.getUser().getUsername())) {
                // if so then it is safe to remove post from database
                this.postService.delete(post);
                System.err.println("DELETED post: " + post); // for testing debugging purposes
                return "redirect:/";
            } else {
                System.err.println("Current User has no permissions to edit anything on post by id: " + id); // for testing debugging purposes
                return "403";
            }
        } else {
            System.err.println("Could not find a post by id: " + id); // for testing debugging purposes
            return "error";
        }
    }

}
