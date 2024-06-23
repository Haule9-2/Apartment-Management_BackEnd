package com.dmp.repositories.impl;

import com.dmp.pojo.FamilyMember;
import com.dmp.pojo.Resident;
import com.dmp.pojo.User;
import com.dmp.repositories.FamilyMemberRepository;
import com.dmp.repositories.ResidentRepository;
import com.dmp.repositories.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class FamilyMemberRepositoryImpl implements FamilyMemberRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public List<FamilyMember> getFamilyMembers(Map<String, String> params, String username) {
        User u = this.userRepository.getUserByUsername(username);

        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();

        CriteriaQuery<FamilyMember> criteria = builder.createQuery(FamilyMember.class);
        Root<FamilyMember> root = criteria.from(FamilyMember.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(root.get("residentId").get("id"), u.getId()));
        // Thêm điều kiện để lọc FamilyMember có active = 1
        predicates.add(builder.equal(root.get("active"), (short) 1));

        criteria.select(root).where(predicates.toArray(new Predicate[0]));

        Query query = s.createQuery(criteria);

        return query.getResultList();
    }

    @Override
    public FamilyMember  addFamilyMember(FamilyMember familyMember) {
        // Lấy tên người dùng từ SecurityContext
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Lấy User từ repository
        User u = this.userRepository.getUserByUsername(username);
        if (u == null) {
            throw new RuntimeException("User not found");
        }

        // Lấy session hiện tại
        Session s = this.sessionFactory.getObject().getCurrentSession();

        // Lấy Resident dựa trên ID của User
        Resident r = this.residentRepository.getResidentById(u.getId());
        if (r == null) {
            throw new RuntimeException("Resident not found");
        }

        // Thiết lập các thông tin cần thiết cho FamilyMember
        familyMember.setResidentId(r);
        familyMember.setFullName(familyMember.getFullName());
        familyMember.setActive((short) 1); // Giả sử bạn dùng Short cho active
        s.save(familyMember);
        return familyMember;
    }
}