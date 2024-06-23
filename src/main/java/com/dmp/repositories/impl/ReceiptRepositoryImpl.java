package com.dmp.repositories.impl;

import com.dmp.pojo.*;
import com.dmp.repositories.ReceiptRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.*;
import javax.persistence.criteria.Predicate;

@Repository
@Transactional
public class ReceiptRepositoryImpl implements ReceiptRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;



    @Override
    public List<Receipt> getAllReceipt(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Query q = s.createQuery("from User r where r.username = :username");
        q.setParameter("username", username);
        User u = (User) q.getSingleResult();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Receipt> cq = cb.createQuery(Receipt.class);
        Root<Receipt> r = cq.from(Receipt.class);
        cq.select(r);
        List<Predicate> predicates = new ArrayList<>();

        if (u.getRole().equals("resident")) {
            // Nếu là cư dân, chỉ lấy biên lai liên quan đến cư dân này
            Root<RentalContract> rt = cq.from(RentalContract.class);
            predicates.add(cb.equal(rt.get("resident"), u));
            predicates.add(cb.equal(rt.get("id"), r.get("rentalContract")));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(r.get("id")));

        Query rs = s.createQuery(cq);

        List<Receipt> receipts = rs.getResultList();
        return receipts;
    }

    @Override
    public List<Receipt> getAllReceiptsByResidentID(int residentID) {
        try {
            Session session = this.factoryBean.getObject().getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Receipt> cq = cb.createQuery(Receipt.class);
            Root<Receipt> receiptRoot = cq.from(Receipt.class);
            Root<RentalContract> contractRoot = cq.from(RentalContract.class);

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(contractRoot.get("resident").get("id"), residentID));
            predicates.add(cb.equal(contractRoot.get("id"), receiptRoot.get("contractId").get("id")));

            predicates.add(cb.like(receiptRoot.get("status"), String.format("%%%s%%", "Chưa thu")));

            cq.select(receiptRoot).where(predicates.toArray(new Predicate[0]));

            Query query = session.createQuery(cq);
            return query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Receipt getReceiptById(int id) {
        try {
            Session s = this.factoryBean.getObject().getCurrentSession();
            Query query = s.createQuery("from Receipt r where r.id = :id");
            query.setParameter("id", id);
            return (Receipt) query.getSingleResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteReceiptById(int id) {
        try {
            Session session = this.factoryBean.getObject().getCurrentSession();
            Receipt receipt = session.get(Receipt.class, id);
            if (receipt != null) {
                session.delete(receipt);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addReceipt(Map<String, Services> s) {
        Session session = factoryBean.getObject().getCurrentSession();
        try {
            Receipt receipt = new Receipt();
            receipt.setCreatedDate(new Date());


            RentalContract contract = session.get(RentalContract.class, 1); // Example contract ID
            receipt.setContractId(contract);

            Set<ReceiptDetail> receiptDetailSet = new HashSet<>();
            long total = 0;

            for (Map.Entry<String, Services> entry : s.entrySet()) {
                Services service = entry.getValue();
                ReceiptDetail receiptDetail = new ReceiptDetail();
                receiptDetail.setServicesId(service);
                receiptDetail.setReceiptId(receipt);
                receiptDetail.setAmount(service.getPrice().intValue());

                total += service.getPrice().intValue();

                receiptDetailSet.add(receiptDetail);
            }

            receipt.setTotal(total);
            receipt.setReceiptDetailSet(receiptDetailSet);

            session.save(receipt);

            for (ReceiptDetail detail : receiptDetailSet) {
                session.save(detail);
            }

        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateReceipt(Receipt receipt) {
        Session session = this.factoryBean.getObject().getCurrentSession();

        Receipt rp = session.get(Receipt.class, receipt.getId());
        if (rp != null) {

            rp.setTotal(receipt.getTotal());
            rp.setStatus(receipt.getStatus());

            session.update(rp);
        } else {
            throw new RuntimeException("Receipt not found");
        }
    }
}

