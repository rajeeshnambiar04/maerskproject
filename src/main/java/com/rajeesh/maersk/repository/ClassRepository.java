package com.rajeesh.maersk.repository;

import com.rajeesh.maersk.domain.MemberClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ClassRepository extends JpaRepository<MemberClass, Long>, JpaSpecificationExecutor {
    List<MemberClass> findAllByNameContainsIgnoreCase(String searchString);
}
