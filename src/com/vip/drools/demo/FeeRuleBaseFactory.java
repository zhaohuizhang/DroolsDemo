package com.vip.drools.demo;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;

public class FeeRuleBaseFactory {
	private static RuleBase ruleBase;

	public static RuleBase getRuleBase() {
		return null != ruleBase ? ruleBase : RuleBaseFactory.newRuleBase();
	}
}
