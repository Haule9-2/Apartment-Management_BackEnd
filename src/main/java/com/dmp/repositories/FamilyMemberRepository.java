package com.dmp.repositories;

import com.dmp.pojo.FamilyMember;

import java.util.List;
import java.util.Map;

public interface FamilyMemberRepository {
    List<FamilyMember> getFamilyMembers(Map<String, String> params);
    void addOrUpdate(FamilyMember familyMember);

}
