package com.dmp.repositories.impl;

import com.dmp.pojo.Cabinet;
import com.dmp.pojo.Item;
import com.dmp.pojo.RentalContract;
import com.dmp.repositories.CabinetRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Repository
@Transactional
public class CabinetRepositoryImpl implements CabinetRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public void createCabinet(Cabinet cabinet) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        if (!ischeckCabinetbyContract(cabinet.getContractId().getId()))
            session.save(cabinet);

    }

    @Override
    public List<Object[]> getAllCabinet(Map<String, String> params) {
        Session session = Objects.requireNonNull(this.factoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Cabinet> root = criteriaQuery.from(Cabinet.class);

        List<Predicate> predicates = new ArrayList<>();


        if (params.containsKey("status")) {
            String status = params.get("status");
            predicates.add(criteriaBuilder.equal(root.get("status"), status));
        }


        if (params.containsKey("isActive")) {
            Boolean isActive = Boolean.parseBoolean(params.get("isActive"));
            predicates.add(criteriaBuilder.equal(root.get("isActive"), isActive ? (short) 1 : (short) 0));
        }

        criteriaQuery.select(criteriaBuilder.array(
                root.get("id"),
                root.get("status"),
                root.get("isActive"),
                root.get("contractId")
        )).where(predicates.toArray(new Predicate[0]));

        Query query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<Item> getItemsByCabinetId(int cabinetId, Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();

        CriteriaQuery<Item> cq = builder.createQuery(Item.class);
        Root item = cq.from(Item.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(item.get("cabinet"), cabinetId));

        String status = params.get("status");
        if(status != null && !status.isEmpty()) {
            if(status.equalsIgnoreCase("chưa nhận")) {
                predicates.add(builder.isNull(item.get("received_date")));
            } else if(status.equalsIgnoreCase("đã nhận")) {
                predicates.add(builder.isNotNull(item.get("received_date")));
            }
        }

        cq.where(predicates.toArray(Predicate[]::new));

        Order orderByReceivedDateDesc = builder.desc(item.get("received_date"));
        cq.orderBy(orderByReceivedDateDesc);

        Query query = s.createQuery(cq);
        return (List<Item>) query.getResultList();
    }

    @Override
    public Boolean checkStatusCabinet(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();

        Query q = s.createQuery("SELECT A.status FROM Cabinet A WHERE A.id=:cabinetId");
        q.setParameter("cabinetId", id);

        return (Boolean) q.getSingleResult();
    }

    @Override
    public void closeCabinets() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();

        CriteriaQuery<Cabinet> cq = builder.createQuery(Cabinet.class);
        Root cabinet = cq.from(Cabinet.class);
        Root contractRoot = cq.from(RentalContract.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(contractRoot.get("id"), cabinet.get("contractId")));
        predicates.add(builder.lessThanOrEqualTo(contractRoot.get("end_date"), LocalDate.now()));

        cq.select(cabinet).where(predicates.toArray(Predicate[]::new));

        List<Cabinet> cabinets = s.createQuery(cq).getResultList();
        for (Cabinet c : cabinets) {
            c.setIsActive((short) 0);
            s.update(cabinet);
        }

    }

    @Override
    public Boolean ischeckCabinetbyContract(int contractId) {
        Session session = factoryBean.getObject().getCurrentSession();


        Query query = session.createQuery("SELECT COUNT(c) FROM Cabinet c WHERE c.contractId.id = :id", Long.class);
        query.setParameter("id", contractId);


        Long count = (Long) query.getSingleResult();
        return count > 0;

    }
}
