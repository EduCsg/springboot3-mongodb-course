package com.du.mongoDbCourse.repository;

import com.du.mongoDbCourse.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchByTitle(String title);

    List<Post> findByTitleContainingIgnoreCase(String title);

    @Query("{ $and: [ " +
                   " { date: { $gte: ?1 } }, " +
                   " { date: { $lte: ?2 } }, " +
                   " { $or: " +
                   "     [ " +
                   "         { 'title': { $regex: ?0, $options: 'i' } }, " +
                   "         { 'body': { $regex: ?0, $options: 'i' } }, " +
                   "         { 'comments.text': { $regex: ?0, $options: 'i' } } " +
                   "     ] } " +
                   " ] }")
    List<Post> searchByTextBetweenDates(String text, Date startDate, Date endDate);

}
