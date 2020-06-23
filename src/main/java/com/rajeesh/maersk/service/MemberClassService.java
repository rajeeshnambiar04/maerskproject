package com.rajeesh.maersk.service;

import com.rajeesh.maersk.domain.Member;
import com.rajeesh.maersk.domain.MemberClass;
import com.rajeesh.maersk.repository.ClassRepository;
import com.rajeesh.maersk.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MemberClassService {
    private ClassRepository classRepository;
    private MemberRepository memberRepository;

    public MemberClassService(ClassRepository classRepository, MemberRepository memberRepository) {
        this.classRepository = classRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
    public void classStats() {
        List<MemberClass> memberClasses = classRepository.findAll();

        //Map<String, Integer> memberClassesMap = memberClasses
          //      .stream()
                //.collect(Collectors.toMap(MemberClass::getName, c -> 0));

        List<Member> members = memberRepository.findAll();

        for (Member member : members) {
            if (CollectionUtils.isNotEmpty(member.getMemberClasses())) {
                for (Object memberClass : member.getMemberClasses()) {
                    Object memberClassesMap;
					// Another way to do this ...
                    // memberClassesMap.computeIfPresent(memberClass.getName(), (k, v) -> v + 1);
                   // memberClassesMap.merge(((Object) memberClass).getName(), 1, Integer::sum);
                }
            }
        }

        /*
		log.info("Class Statics:");
        log.info("=============");
        memberClassesMap.forEach((k,v) -> log.info("{}: {}", k, v));
        log.info("==========================");
        */
    }
}
