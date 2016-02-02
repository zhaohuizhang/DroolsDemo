package com.vip.drools.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.drools.RuleBase;
import org.drools.StatefulSession;
import org.drools.compiler.PackageBuilder;
import org.drools.rule.Package;
import org.drools.spi.Activation;
import org.drools.spi.AgendaFilter;

public class FeeRuleEngineImpl implements FeeRuleEngine {

	private RuleBase ruleBase;

	public void initEngine() {
		// TODO Auto-generated method stub
		System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
		try {
			synchronized (this) {
				ruleBase = FeeRuleBaseFactory.getRuleBase();
				// 从数据库中查找规则装载packageBuidler
				PackageBuilder packageBuilder = buildPackageBuilder(builderReaderFromDB());
				// 从规则文件找获取规则装载 packageBuilder
				// PackageBuilder packageBuilder = buildPackageBuilder(builderReaderFromDrlFile());
				packageBuilder = null == packageBuilder ? buildPackageBuilder(builderReaderFromDrlFile())
						: packageBuilder;
				ruleBase.addPackages(packageBuilder.getPackages());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void refreshEngineRule() {
		// TODO Auto-generated method stub
		ruleBase = FeeRuleBaseFactory.getRuleBase();
		synchronized (this) {
			// 删除已经添加的Package
			Package[] packages = ruleBase.getPackages();
			for (Package pg : packages) {
				ruleBase.removePackage(pg.getName());
			}

			initEngine();
		}

	}

	public void excuteRuleEngine(final MerchantEntity merchant) {
		// TODO Auto-generated method stub
		if (null == ruleBase.getPackages() || 0 == ruleBase.getPackages().length) {
			return;
		}

		StatefulSession statefulSession = ruleBase.newStatefulSession();
		statefulSession.insert(merchant);
		System.out.println("-------------hello-------------");
		// fire
		statefulSession.fireAllRules(new AgendaFilter() {

			public boolean accept(Activation activation) {
				// TODO Auto-generated method stub
				return !activation.getRule().getName().contains("_test");
			}

		});
		System.out.println("-------------world-------------");

		statefulSession.dispose();
	}

	/**
	 * 
	 * 装载PackageBuilder <br>
	 * 〈功能详细描述〉
	 * 
	 * @param readers
	 * @return
	 * @throws Exception
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	private PackageBuilder buildPackageBuilder(List<Reader> readers) throws Exception {
		if (null == readers || 0 == readers.size()) {
			return null;
		}

		PackageBuilder packageBuilder = new PackageBuilder();
		for (Reader reader : readers) {
			packageBuilder.addPackageFromDrl(reader);
		}

		if (packageBuilder.hasErrors()) {
			throw new Exception(packageBuilder.getErrors().toString());
		}

		return packageBuilder;
	}

	/**
	 * 
	 * 从数据库中装载规则到Reader中 <br>
	 * 〈功能详细描述〉
	 * 
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	private List<Reader> builderReaderFromDB() {
		List<Reader> readers = new ArrayList<Reader>();

		List<FeeRuleEntity> feeRuleEntities = getRuleFromDB();
		if (null == feeRuleEntities) {
			return readers;
		}

		for (FeeRuleEntity feeRuleEntity : feeRuleEntities) {
			String ruleContext = feeRuleEntity.getRuleContext();
			Reader reader = new StringReader(ruleContext);

			readers.add(reader);
		}

		return readers;
	}

	/**
	 * 
	 * 装载规则文件到Reader中 <br>
	 * 〈功能详细描述〉
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	private List<Reader> builderReaderFromDrlFile() throws FileNotFoundException {
		return readRuleFromDrlFile(getRuleDrlFile());
	}

	/**
	 * 
	 * 从规则文件中读取规则 <br>
	 * 〈功能详细描述〉
	 * 
	 * @param drlFilePath
	 * @return
	 * @throws FileNotFoundException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	private List<Reader> readRuleFromDrlFile(List<String> drlFilePath) throws FileNotFoundException {
		if (null == drlFilePath || 0 == drlFilePath.size()) {
			return null;
		}
		List<Reader> readers = new ArrayList<Reader>();

		for (String ruleFilePath : drlFilePath) {
			readers.add(new FileReader(new File(ruleFilePath)));
		}

		return readers;

	}

	/**
	 * 
	 * 从文件系统获取规则文件 <br>
	 * 
	 * 
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	private List<String> getRuleDrlFile() {
		List<String> drlFilePath = new ArrayList<String>();
		drlFilePath.add("E:/chromedownload/DroolsDemo/DroolsDemo/src/com/vip/drools/demo/addfee.drl");
		drlFilePath.add("E:/chromedownload/DroolsDemo/DroolsDemo/src/com/vip/drools/demo/subfee.drl");
		return drlFilePath;
	}

	/**
	 * 
	 * 从数据库中读取规则 <br>
	 * 〈功能详细描述〉
	 * 
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	private List<FeeRuleEntity> getRuleFromDB() {
		List<FeeRuleEntity> feeRuleEntities = new ArrayList<FeeRuleEntity>();
		FeeRuleEntity feeRuleEntity = new FeeRuleEntity();
		feeRuleEntity.setId(1L);
		feeRuleEntity.setRuleContext("package com.vip.drools.demo" + "\n"
				+ "import com.vip.drools.demo.MerchantEntity;" + "\n" + "rule activityDayFee" + "\n" + "salience 100"
				+ "\n" + "lock-on-active true" + "\n" + "when" + "\n"
				+ "$merchantEntity : MerchantEntity(activityDay == true)" + "\n" + "then" + "\n"
				+ "$merchantEntity.setFee($merchantEntity.getFee()-1);" + "\n"
				+ "$merchantEntity.recordLog($merchantEntity.getMerchantName(),$merchantEntity.getMerchantName());"
				+ "\n" + "end");
		feeRuleEntity.setRuleName("actitiyDayRule");
		feeRuleEntity.setVersion(1);
		feeRuleEntities.add(feeRuleEntity);
		return feeRuleEntities;
	}
}
