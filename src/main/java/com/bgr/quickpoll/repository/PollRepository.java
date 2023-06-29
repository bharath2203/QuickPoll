package com.bgr.quickpoll.repository;

import com.bgr.quickpoll.domain.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository<Poll, Long> {}
