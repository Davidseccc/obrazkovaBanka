package cz.uhk.obrazkovaBanka.entity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.obrazkovaBanka.entity.Tag;
import cz.uhk.obrazkovaBanka.entity.dao.TagDao;

@Service
@Transactional
public class TagService {

	@Autowired
	private TagDao tagDao;
	
	public long countAllTags() {
        return tagDao.countTags();
    }

	public void deleteTag(Tag t) {
		tagDao.remove(t);
    }

	public Tag findTag(Integer id) {
        return tagDao.findTag(id);
    }
	
	public Tag findTagEagerly(Long id) {
        return tagDao.findTagsEagerly(id);
    }

	public List<Tag> getAllTags() {
        return tagDao.findAllTags();
    }

	public List<Tag> findTagEntries(int firstResult, int maxResults) {
        return tagDao.findTagsEntries(firstResult, maxResults);
    }
	
	public List<Tag> findTagsByImageId(int imageId) {
        return tagDao.findTagsByImageId(imageId);
    }

	public void saveTag(Tag t) {
		tagDao.persist(t);
    }

	public Tag updateTag(Tag t) {
        return tagDao.merge(t);
    }
	
	public void findOrCreate(Tag t) {
		tagDao.findOrCreate(t);
	}
	
}
