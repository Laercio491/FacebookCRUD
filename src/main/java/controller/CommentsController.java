package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Comment;
import model.ModelException;
import model.Post;
import model.User;
import model.dao.CommentDAO;
import model.dao.DAOFactory;
import model.dao.PostDAO;
import model.dao.UserDAO;

@WebServlet(urlPatterns = {
    "/comments",
    "/comments_form",
    "/comments/save",
    "/comments/update",
    "/comments/delete"
})
public class CommentsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getRequestURI();
        System.out.println("URL: " + action);

        switch (action) {

            case "/facebook/comments": {
                loadComments(req);

                RequestDispatcher rd =
                        req.getRequestDispatcher("/comments.jsp");
                rd.forward(req, resp);
                break;
            }

            case "/facebook/comments_form": {
                loadUsers(req);
                loadPosts(req);

                RequestDispatcher rd =
                        req.getRequestDispatcher("/comments_form.jsp");
                rd.forward(req, resp);
                break;
            }

            case "/facebook/comments/save": {
                saveOrUpdateComment(req);

                resp.sendRedirect("/facebook/comments");
                break;
            }

            case "/facebook/comments/update": {
                loadComment(req);
                loadUsers(req);
                loadPosts(req);

                RequestDispatcher rd =
                        req.getRequestDispatcher("/comments_form.jsp");
                rd.forward(req, resp);
                break;
            }

            case "/facebook/comments/delete": {
                deleteComment(req);
                resp.sendRedirect("/facebook/comments");
                break;
            }

            default:
                String message = String.format(
                        "URL não suportada: '%s'.", action);

                System.out.println(message);
                throw new IllegalArgumentException(message);
        }
    }

    private void deleteComment(HttpServletRequest req) {
        String commentIdStr = req.getParameter("commentId");

        int commentId = Integer.parseInt(commentIdStr);
        Comment commentToBeDeleted = new Comment();
        commentToBeDeleted.setId(commentId);

        CommentDAO dao = DAOFactory.createDAO(CommentDAO.class);

        try {
            dao.delete(commentToBeDeleted);
        } catch (ModelException e) {
            e.printStackTrace();
        }
    }

    private void loadComment(HttpServletRequest req) {
        String commentIdStr = req.getParameter("commentId");
        int commentId = Integer.parseInt(commentIdStr);

        CommentDAO dao = DAOFactory.createDAO(CommentDAO.class);

        Comment commentToBeUpdated = null;

        try {
            commentToBeUpdated = dao.findById(commentId);
        } catch (ModelException e) {
            e.printStackTrace();
        }

        req.setAttribute("comment", commentToBeUpdated);
    }

    private void saveOrUpdateComment(HttpServletRequest req) {
        String commentIdStr = req.getParameter("comment_id");
        boolean isUpdate = false;

        Comment comment;

        if (commentIdStr == null || commentIdStr.isBlank()) {
            comment = new Comment();
        } else {
            comment = new Comment();
            comment.setId(Integer.parseInt(commentIdStr));
            isUpdate = true;
        }

        String content = req.getParameter("comment_content");
        String commentUser = req.getParameter("comment_user");
        String commentPost = req.getParameter("comment_post");

        int userId = Integer.parseInt(commentUser);
        int postId = Integer.parseInt(commentPost);

        UserDAO userDAO = DAOFactory.createDAO(UserDAO.class);
        PostDAO postDAO = DAOFactory.createDAO(PostDAO.class);

        User user = null;
        Post post = null;

        try {
            user = userDAO.findById(userId);
            post = postDAO.findById(postId);
        } catch (ModelException e) {
            e.printStackTrace();
        }

        comment.setContent(content);
        comment.setUser(user);
        comment.setPost(post);

        CommentDAO dao = DAOFactory.createDAO(CommentDAO.class);

        try {
            if (isUpdate) {
                dao.update(comment);
            } else {
                dao.save(comment);
            }
        } catch (ModelException e) {
            e.printStackTrace();
        }
    }

    private void loadComments(HttpServletRequest req) {
        CommentDAO dao = DAOFactory.createDAO(CommentDAO.class);

        List<Comment> comments = List.of();

        try {
            comments = dao.listAll();
        } catch (ModelException e) {
            e.printStackTrace();
        }

        req.setAttribute("comments_list", comments);
    }

    private void loadUsers(HttpServletRequest req) {
        UserDAO dao = DAOFactory.createDAO(UserDAO.class);

        List<User> users = List.of();

        try {
            users = dao.listAll();
        } catch (ModelException e) {
            e.printStackTrace();
        }

        req.setAttribute("users_list", users);
    }

    private void loadPosts(HttpServletRequest req) {
        PostDAO dao = DAOFactory.createDAO(PostDAO.class);

        List<Post> posts = List.of();

        try {
            posts = dao.listAll();
        } catch (ModelException e) {
            e.printStackTrace();
        }

        req.setAttribute("posts_list", posts);
    }
}
