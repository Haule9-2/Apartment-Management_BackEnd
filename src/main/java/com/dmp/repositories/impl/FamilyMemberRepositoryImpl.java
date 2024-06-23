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
    public FamilyMember addFamilyMember(FamilyMember familyMember) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = this.userRepository.getUserByUsername(username);

        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();

        List<Predicate> predicates = new ArrayList<>();

        if (u == null) {
            predicates.add(builder.isNull(builder.literal(u)));
        } else {
            Resident r = this.residentRepository.getResidentById(u.getId());
            if (r == null) {
                predicates.add(builder.isNull(builder.literal(r)));
            } else {
                familyMember.setResidentId(r);
                familyMember.setFullName(familyMember.getFullName());
                familyMember.setActive((short) 1);
                s.save(familyMember);
            }
        }

        if (!predicates.isEmpty()) {
            throw new RuntimeException("User or Resident not found");
        }

        return familyMember;
    }

    @Override
    public void deleteFamilyMember(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<FamilyMember> query = builder.createQuery(FamilyMember.class);
        Root<FamilyMember> root = query.from(FamilyMember.class);

        query.select(root).where(root.get("id").in(id));

        List<FamilyMember> items = session.createQuery(query).getResultList();

        items.forEach(session::delete);
    }
}
