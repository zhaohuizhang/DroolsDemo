package com.drools.demo.point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		PointRuleEngine pointRuleEngine = new PointRuleEngineImpl();
		while (true) {
			InputStream is = System.in;
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String input = br.readLine();

			if (null != input && "start".equals(input)) {
				System.out.println("--------------初始化规则引擎--------------");
				pointRuleEngine.initEngine();
				System.out.println("--------------初始化结束--------------");
			} else if ("excute".equals(input)) {
				final PointDomain pointDomain = new PointDomain();
				pointDomain.setUserName("qiqixue");
				pointDomain.setBackMondy(100d);
				pointDomain.setBuyMoney(500d);
				pointDomain.setBackNums(1);
				pointDomain.setBuyNums(5);
				pointDomain.setBillThisMonth(5);
				pointDomain.setBirthDay(true);
				pointDomain.setPoint(0l);
				System.out.println("--------------开始执行规则--------------");
				System.out.println("执行开始BillThisMonth：" + pointDomain.getBillThisMonth());
				System.out.println("执行开始BuyMoney：" + pointDomain.getBuyMoney());
				System.out.println("执行开始BuyNums：" + pointDomain.getBuyNums());

				pointRuleEngine.executeRuleEngine(pointDomain);
				System.out.println("--------------执行规则完毕--------------");
				System.out.println("执行完毕BillThisMonth：" + pointDomain.getBillThisMonth());
				System.out.println("执行完毕BuyMoney：" + pointDomain.getBuyMoney());
				System.out.println("执行完毕BuyNums：" + pointDomain.getBuyNums());

				System.out.println("执行完毕规则引擎决定发送积分：" + pointDomain.getPoint());
			} else if ("refresh".equals(input)) {
				System.out.println("--------------刷新规则文件--------------");
				pointRuleEngine.refreshEnginRule();
				System.out.println("--------------刷新规则文件结束--------------");
			} else if ("exit".equals(input)) {
				System.exit(0);
			}
		}
	}

}
