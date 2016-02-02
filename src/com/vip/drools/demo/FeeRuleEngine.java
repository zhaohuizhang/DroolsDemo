package com.vip.drools.demo;

public interface FeeRuleEngine {

	public void initEngine();

	public void refreshEngineRule();

	public void excuteRuleEngine(final MerchantEntity merchant);
}
