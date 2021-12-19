package ajun.webapp.vebnding.result;

import ajun.webapp.vebnding.object.ChangeInfo;

public class BuyProductResult {
	
	public BuyProductResult() {
		this.result = true;
		this.resultMessage = null;
		this.returnChange = new ChangeInfo();
	}
	
	private boolean result;
	
	private String resultMessage;
	
	private ChangeInfo returnChange;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public ChangeInfo getReturnChange() {
		return returnChange;
	}

	public void setReturnChange(ChangeInfo returnChange) {
		this.returnChange = returnChange;
	}

}
