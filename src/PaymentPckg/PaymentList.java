package PaymentPckg;

import java.util.*;

public class PaymentList extends ModelObject {
	private List<Payment> payments = new ArrayList<Payment>();
	
	public List<Payment> getPayments() {
	    return payments;
	    }
	public void addPayment(Payment payment) {
		this.payments.add(payment);
        firePropertyChange("payments", null, null);
    }
    public void removePayment(Payment payment) {
    	this.payments.remove(payment);
    firePropertyChange("payments", null, null);
    }
    public void removeMarked(){
    	Iterator<Payment> itr = payments.iterator();
    	while (itr.hasNext()){
    		Payment item = itr.next();
    		if (item.isChecked()){
    			itr.remove();
    			firePropertyChange("payments", null, null);
    		}
    	}
    }
}
