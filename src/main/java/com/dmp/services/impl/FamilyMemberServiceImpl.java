package com.dmp.services.impl;

import com.dmp.pojo.FamilyMember;
import com.dmp.repositories.FamilyMemberRepository;
import com.dmp.services.FamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FamilyMemberServiceImpl implements FamilyMemberService {
    @Autowired
    private FamilyMemberRepository repo;
    @Override
    public List<FamilyMember> getFamilyMembers(Map<String, String> params, String username) {
        return this.repo.getFamilyMembers(params, username);
    }

    @Override
    public FamilyMember addFamilyMember(FamilyMember familyMember) {
        return this.repo.addFamilyMember(familyMember);
    }
}
