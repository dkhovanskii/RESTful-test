package com.silverrailtech.repository;

import com.silverrailtech.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by d on 6/01/17.
 */
@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    public List<Session> findAllByBrowserId(String browserid);
}
