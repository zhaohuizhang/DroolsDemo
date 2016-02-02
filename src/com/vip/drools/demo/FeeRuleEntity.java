package com.vip.drools.demo;

public class FeeRuleEntity {
	private long id;
	private String ruleName;
	private String ruleContext;
	private int version;
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleContext() {
		return ruleContext;
	}

	public void setRuleContext(String ruleContext) {
		this.ruleContext = ruleContext;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
