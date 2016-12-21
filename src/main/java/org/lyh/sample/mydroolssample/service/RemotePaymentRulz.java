package org.lyh.sample.mydroolssample.service;

import java.io.InputStream;

import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.lyh.sample.mydroolssample.dao.PaymentInfo;

public class RemotePaymentRulz {

	public static void main(String[] args) throws Exception {
		/* step 1, 配置规则库发布地址 */
		String url = "http://localhost:8080/kie-wb/maven2/org/lyh/sample/MyDroolsSample/1.0/MyDroolsSample-1.0.jar";
		// ReleaseIdImpl releaseId = new ReleaseIdImpl("org.lyh.sample", "MyDroolsSample", "LATEST");

		/* step 2, 建立连接 */
		KieServices ks = KieServices.Factory.get();
		KieRepository kr = ks.getRepository();
		UrlResource urlResource = (UrlResource) ks.getResources().newUrlResource(url);
		urlResource.setUsername("dadmin");
		urlResource.setPassword("dadmin");
		urlResource.setBasicAuthentication("enabled");
		InputStream is = urlResource.getInputStream();
		KieModule kModule = kr.addKieModule(ks.getResources().newInputStreamResource(is));
		KieContainer kContainer = ks.newKieContainer(kModule.getReleaseId());
		StatelessKieSession kSession = kContainer.newStatelessKieSession("defaultStatelessKieSession");

		/* step 3, 调用规则 */
		PaymentInfo m = new PaymentInfo();
		m.setMoneyAmount(5001);
		kSession.execute(m);

		/* step 4, 校验结果 */
		System.out.println(m.getDecisionPath());
		if (m.getDecisionPath().equalsIgnoreCase("m")) {
			System.out.println("数额<=5000需要经理审批");
		} else {
			System.out.println("数额>5000需要总经理审批");
		}
	}
}