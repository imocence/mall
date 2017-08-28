package net.shopxx.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.shopxx.Filter;
import net.shopxx.Order;
import net.shopxx.dao.FiBankbookBalanceDao;
import net.shopxx.entity.FiBankbookBalance;
import net.shopxx.entity.Member;

import org.springframework.stereotype.Repository;

/**
 * Dao - 存折
 * 
 * @author gaoxiang
 * @version 5.0.3
 */
@Repository
public class FiBankbookBalanceDaoImpl extends BaseDaoImpl<FiBankbookBalance, Long> implements FiBankbookBalanceDao {
	/**
	 * 根据会员编号查询存折
	 */
	public List<FiBankbookBalance> findList(Member member, Integer count,List<Filter> filters, List<Order> orders){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<FiBankbookBalance> criteriaQuery = criteriaBuilder.createQuery(FiBankbookBalance.class);
		Root<FiBankbookBalance> root = criteriaQuery.from(FiBankbookBalance.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if(member != null){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.join("member"), member));
		}
		criteriaQuery.where(restrictions);
		return super.findList(criteriaQuery, null, count, filters, orders);
	}
}