package desearlization;

import java.util.List;

public class courses {

	private List<webautomation> webAutomation;
	private List<api> api;
	private List<mobile> mobile;

	public List<desearlization.webautomation> getWebAutomation() {
		return webAutomation;
	}

	public void setWebAutomation(List<webautomation> webAutomation) {
		this.webAutomation = webAutomation;
	}

	public List<desearlization.api> getApi() {
		return api;
	}

	public void setApi(List<desearlization.api> api) {
		this.api = api;
	}

	public List<desearlization.mobile> getMobile() {
		return mobile;
	}

	public void setMobile(List<desearlization.mobile> mobile) {
		this.mobile = mobile;
	}

}
