package cz.uhk.obrazkovaBanka.entity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.obrazkovaBanka.entity.Comment;
import cz.uhk.obrazkovaBanka.entity.dao.CommentDao;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentDao commentDao;
	
	public long countAllComments() {
        return commentDao.countComments();
    }

	public void deleteComment(Comment c) {
		commentDao.remove(c);
    }

	public Comment findComment(Integer id) {
        return commentDao.findComment(id);
    }
	
	public Comment findCommentEagerly(Long id) {
        return commentDao.findCommentEagerly(id);
    }

	public List<Comment> getAllCategories() {
        return commentDao.findAllComments();
    }

	public List<Comment> findCommentEntries(int firstResult, int maxResults) {
        return commentDao.findCommentEntries(firstResult, maxResults);
    }

	public void saveComment(Comment c) {
		commentDao.persist(c);
    }

	public Comment updateCategory(Comment c) {
        return commentDao.merge(c);
    }
}
