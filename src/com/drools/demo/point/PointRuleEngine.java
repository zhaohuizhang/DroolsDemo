package com.drools.demo.point;

/**
 * 
 * 规则接口<br>
 * 
 * @author napu.zhang
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface PointRuleEngine {

	/**
	 * 初始化规则引擎
	 */
	public void initEngine();

	/**
	 * 刷新规则引擎中的规则
	 */
	public void refreshEnginRule();

	/**
	 * 
	 * 执行规则引擎<br>
	 * 
	 * @param pointDomain
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public void executeRuleEngine(final PointDomain pointDomain);
}
