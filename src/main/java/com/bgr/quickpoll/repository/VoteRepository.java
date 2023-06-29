package com.bgr.quickpoll.repository;

import com.bgr.quickpoll.domain.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends CrudRepository<Vote, Long> {

    @Query(value = "select v.* from Option o, Vote v where o.POLL_ID = :pollId and v.OPTION_ID = o.OPTION_ID", nativeQuery = true)
    public Iterable<Vote> findByPoll(@Param("pollId") Long pollId);
}
