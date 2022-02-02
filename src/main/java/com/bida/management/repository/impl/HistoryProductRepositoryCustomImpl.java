package com.bida.management.repository.impl;

import static com.bida.management.util.DaoUtil.like;

import com.bida.management.config.Constants;
import com.bida.management.domain.HistoryProduct;
import com.bida.management.domain.Provider;
import com.bida.management.domain.User;
import com.bida.management.repository.HistoryProductRepositoryCustom;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class HistoryProductRepositoryCustomImpl implements HistoryProductRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<HistoryProduct> search(Pageable pageable, HistoryProduct historyProduct) {
        CriteriaQuery<HistoryProduct> criteriaQuery = buildSearchQuery(HistoryProduct.class, historyProduct, false);
        TypedQuery<HistoryProduct> query = em.createQuery(criteriaQuery);
        query.setFirstResult((int) (pageable.getOffset()));
        query.setMaxResults(pageable.getPageSize());
        List<HistoryProduct> items = query.getResultList();
        CriteriaQuery<Long> count = buildSearchQuery(Long.class, historyProduct, true);
        int total = Math.toIntExact(em.createQuery(count).getSingleResult());
        return new PageImpl<>(items, pageable, total);
    }

    private <T> CriteriaQuery<T> buildSearchQuery(Class<T> clazz, HistoryProduct historyProduct, boolean isCount) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(clazz);
        Root<HistoryProduct> root = criteriaQuery.from(HistoryProduct.class);
        Join<HistoryProduct, User> join1 = root.join("user", JoinType.LEFT);
        Join<HistoryProduct, Provider> join2 = root.join("provider", JoinType.LEFT);
        if (isCount) {
            criteriaQuery.multiselect(cb.countDistinct(root));
        }
        Predicate filterPredicate = buildPredicate(historyProduct, cb, root, join1, join2);
        criteriaQuery.distinct(true).where(filterPredicate);
        return criteriaQuery;
    }

    private Predicate buildPredicate(
        HistoryProduct historyProduct,
        CriteriaBuilder cb,
        Root<HistoryProduct> root,
        Join<HistoryProduct, User> join1,
        Join<HistoryProduct, Provider> join2
    ) {
        Predicate pFinal = cb.notEqual(root.get("status"), Constants.STATUS.DELETED);
        if (StringUtils.isNotBlank(historyProduct.getProductName())) {
            Predicate predicateName = cb.like(root.get("productName"), like(historyProduct.getProductName()));
            pFinal = cb.and(pFinal, predicateName);
        }
        if (Objects.nonNull(historyProduct.getEmployeeId())) {
            Predicate predicateEmployee = cb.equal(join1.get("id"), historyProduct.getEmployeeId());
            pFinal = cb.and(pFinal, predicateEmployee);
        }
        if (Objects.nonNull(historyProduct.getProviderId())) {
            Predicate predicateProvider = cb.equal(join2.get("id"), historyProduct.getProviderId());
            pFinal = cb.and(pFinal, predicateProvider);
        }
        return pFinal;
    }
}
