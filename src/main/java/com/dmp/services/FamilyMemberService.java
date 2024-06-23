package com.dmp.services;

import com.dmp.pojo.FamilyMember;

import java.util.List;
import java.util.Map;

public interface FamilyMemberService {
    List<FamilyMember> getFamilyMembers(Map<String, String> params, String username);
    FamilyMember  addFamilyMember(FamilyMember familyMember);
}
