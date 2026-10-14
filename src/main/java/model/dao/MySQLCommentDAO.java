package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.Comment;
import model.ModelException;
import model.User;
import model.Post;

public class MySQLCommentDAO implements CommentDAO {

    @Override
    public boolean save(Comment comment) throws ModelException {
        DBHandler db = new DBHandler();

        String sql = "INSERT INTO comments "
                + "(content, `date`, user_id, post_id) "
                + "VALUES (?, NOW(), ?, ?)";

        db.prepareStatement(sql);
        db.setString(1, comment.getContent());
        db.setInt(2, comment.getUser().getId());
        db.setInt(3, comment.getPost().getId());

        return db.executeUpdate() > 0;
    }

    @Override
    public boolean update(Comment comment) throws ModelException {
        DBHandler db = new DBHandler();

        String sql = "UPDATE comments "
                + "SET content = ?, user_id = ?, post_id = ? "
                + "WHERE id = ?";

        db.prepareStatement(sql);
        db.setString(1, comment.getContent());
        db.setInt(2, comment.getUser().getId());
        db.setInt(3, comment.getPost().getId());
        db.setInt(4, comment.getId());

        return db.executeUpdate() > 0;
    }

    @Override
    public boolean delete(Comment comment) throws ModelException {
        DBHandler db = new DBHandler();

        String sql = "DELETE FROM comments WHERE id = ?";

        db.prepareStatement(sql);
        db.setInt(1, comment.getId());

        return db.executeUpdate() > 0;
    }

    @Override
    public List<Comment> listAll() throws ModelException {
        DBHandler db = new DBHandler();
        List<Comment> comments = new ArrayList<>();

        String sql = "SELECT * FROM comments ORDER BY id ASC";

        db.createStatement();
        db.executeQuery(sql);

        while (db.next()) {
            comments.add(createComment(db));
        }

        return comments;
    }

    @Override
    public Comment findById(int id) throws ModelException {
        DBHandler db = new DBHandler();

        String sql = "SELECT * FROM comments WHERE id = ?";

        db.prepareStatement(sql);
        db.setInt(1, id);
        db.executeQuery();

        if (db.next()) {
            return createComment(db);
        }

        return null;
    }

    private Comment createComment(DBHandler db) throws ModelException {
        Comment comment = new Comment();

        comment.setId(db.getInt("id"));
        comment.setContent(db.getString("content"));
        comment.setDate(db.getDateTime("date"));

        int userId = db.getInt("user_id");
        int postId = db.getInt("post_id");

        UserDAO userDAO = DAOFactory.createDAO(UserDAO.class);
        PostDAO postDAO = DAOFactory.createDAO(PostDAO.class);

        User user = userDAO.findById(userId);
        Post post = postDAO.findById(postId);

        comment.setUser(user);
        comment.setPost(post);

        return comment;
    }
}