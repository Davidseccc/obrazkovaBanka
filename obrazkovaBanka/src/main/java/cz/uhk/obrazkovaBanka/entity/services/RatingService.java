package cz.uhk.obrazkovaBanka.entity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.obrazkovaBanka.entity.Rating;
import cz.uhk.obrazkovaBanka.entity.dao.RatingDao;

@Service
@Transactional
public class RatingService {

	@Autowired
	private RatingDao ratingDao;
	
	public long countAllRatings() {
        return ratingDao.countRatings();
    }

	public void deleteRating(Rating r) {
		ratingDao.remove(r);
    }

	public Rating findRating(Integer id) {
        return ratingDao.findRating(id);
    }
	
	public double findAvgRatingbyImageId(Integer Imageid) {
        return ratingDao.findAvgRatingbyImageId(Imageid);
    }
	
	public Rating findRatingsEagerly(Long id) {
        return ratingDao.findRatingsEagerly(id);
    }

	public List<Rating> getAllRatings() {
        return ratingDao.findAllRatings();
    }

	public List<Rating> findRatingEntries(int firstResult, int maxResults) {
        return ratingDao.findRatingEntries(firstResult, maxResults);
    }

	public void saveRating(Rating r) {
		ratingDao.persist(r);
    }

	public Rating updateRating(Rating r) {
        return ratingDao.merge(r);
    }
}
