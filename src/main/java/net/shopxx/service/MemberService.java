/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.Country;
import net.shopxx.entity.DepositLog;
import net.shopxx.entity.Language;
import net.shopxx.entity.Member;
import net.shopxx.entity.MemberRank;
import net.shopxx.entity.MemberRank.Type;
import net.shopxx.entity.PointLog;
import net.shopxx.security.AuthenticationProvider;

/**
 * Service - 会员
 * 
 * @author SHOP++ Team
 * @version 5.0.3
 */
@Repository
public interface MemberService extends BaseService<Member, Long>, AuthenticationProvider {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 根据用户名查找会员
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 会员，若不存在则返回null
	 */
	Member findByUsername(String username);
	/**
	 * 判断E-mail是否存在
	 * 
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return E-mail是否存在
	 */
	boolean emailExists(String email);
	/**
	 * 登录验证
	 * @param 用户的编码
	 * 			usercode(大写)
	 * return Member
	 */
	boolean verifyLogin(String usercode,String password);
	/**
	 * 获取会员信息接口
	 * @return
	 */
	List<Member> getListMember(String userCodes);
	/**
	 * 判断E-mail是否唯一
	 * 
	 * @param id
	 *            ID
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return E-mail是否唯一
	 */
	boolean emailUnique(Long id, String email);

	/**
	 * 根据E-mail查找会员
	 * 
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return 会员，若不存在则返回null
	 */
	Member findByEmail(String email);

	/**
	 * 判断手机是否存在
	 * 
	 * @param mobile
	 *            手机(忽略大小写)
	 * @return 手机是否存在
	 */
	boolean mobileExists(String mobile);

	/**
	 * 判断手机是否唯一
	 * 
	 * @param id
	 *            ID
	 * @param mobile
	 *            手机(忽略大小写)
	 * @return 手机是否唯一
	 */
	boolean mobileUnique(Long id, String mobile);

	/**
	 * 根据手机查找会员
	 * 
	 * @param mobile
	 *            手机(忽略大小写)
	 * @return 会员，若不存在则返回null
	 */
	Member findByMobile(String mobile);
	
	/**
	 * 根据usercode查找会员
	 * 
	 * @param usercode
	 *            用户编号
	 * @return 会员，若不存在则返回null
	 */
	Member findByUsercode(String usercode);

	/**
	 * 查找会员分页
	 * 
	 * @param rankingType
	 *            排名类型
	 * @param pageable
	 *            分页信息
	 * @return 会员分页
	 */
	Page<Member> findPage(Member.RankingType rankingType, Pageable pageable);

	/**
	 * 增加余额
	 * 
	 * @param member
	 *            会员
	 * @param amount
	 *            值
	 * @param type
	 *            类型
	 * @param memo
	 *            备注
	 */
	void addBalance(Member member, BigDecimal amount, DepositLog.Type type, String memo);

	/**
	 * 增加积分
	 * 
	 * @param member
	 *            会员
	 * @param amount
	 *            值
	 * @param type
	 *            类型
	 * @param memo
	 *            备注
	 */
	void addPoint(Member member, long amount, PointLog.Type type, String memo);

	/**
	 * 增加消费金额
	 * 
	 * @param member
	 *            会员
	 * @param amount
	 *            值
	 * @param amount
	 *            购物券
	 */
	void addAmount(Member member, BigDecimal amount, BigDecimal couponAmount);
	
	/**
	 * 根据编号和名称查找会员
	 * @param keyword
	 * @param country
	 * @param count
	 * @return
	 */
	List<Member> search(String keyword, Country country, Integer count);
	/**
	 * 创建一个会员
	 * @param companyCode
	 * 			国家码
	 * @param userCode
	 * 			会员编码
	 * @param signature
	 * 			约定码：MD5加密，格式=时间戳+约定值
	 * @param timestamp
	 * 			时间戳
	 * @param language 
	 * @return
	 * @throws Exception 
	 */
	boolean create(Member member,String companyCode, String userCode, String signature,String timestamp,HttpServletRequest request, RedirectAttributes redirectAttributes, Language language) throws Exception;
	/**
	 * 根据会员类型查找
	 * @param register
	 * @return
	 */
	List<Member> getRegisterMember(MemberRank memberRank,int count);

}
