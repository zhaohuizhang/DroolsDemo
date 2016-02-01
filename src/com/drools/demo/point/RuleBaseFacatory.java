package com.drools.demo.point;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;

/**
 * 
 * RuleBaseFactory 单实例RuleBase生成工具<br>
 * 〈功能详细描述〉
 * 
 * @author napu.zhang
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RuleBaseFacatory {
	private static RuleBase ruleBase;

	public static RuleBase getRuleBase() {
		return null != ruleBase ? ruleBase : RuleBaseFactory.newRuleBase();
	}
}
