package com.vip.drools.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestRuleEngine {

	public static void main(String[] args) throws IOException {
		FeeRuleEngine feeRuleEngine = new FeeRuleEngineImpl();
		while (true) {
			InputStream is = System.in;
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String input = br.readLine();

			if (null != input && "s".equals(input)) {
				System.out.println("--------------初始化规则引擎--------------");
				feeRuleEngine.initEngine();
				System.out.println("--------------初始化结束--------------");
			} else if ("e".equals(input)) {
				final MerchantEntity merchantEntity = new MerchantEntity();
				merchantEntity.setMerchantName("qiqixue");
				merchantEntity.setActivityDay(true);
				merchantEntity.setBackMoney(3000);
				merchantEntity.setBackNums(48);
				merchantEntity.setFee(5000);
				merchantEntity.setSellMoney(10836);
				merchantEntity.setSellNums(134);
				System.out.println("--------------开始执行规则--------------");
				System.out.println("执行开始 FEE：" + merchantEntity.getFee());

				feeRuleEngine.excuteRuleEngine(merchantEntity);
				System.out.println("--------------执行规则完毕--------------");
				System.out.println("执行完毕规则引擎决定 FEE：" + merchantEntity.getFee());

			} else if ("r".equals(input)) {
				System.out.println("--------------刷新规则文件--------------");
				feeRuleEngine.refreshEngineRule();
				System.out.println("--------------刷新规则文件结束--------------");
			} else if ("ee".equals(input)) {
				System.exit(0);
			}
		}
	}
}
