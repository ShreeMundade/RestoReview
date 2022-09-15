package com.rcs.restoreview.model;

import javax.persistence.Embeddable;

@Embeddable
public class AdminReviewAction {
    private Boolean accept;

	@Override
	public String toString() {
		return "AdminReviewAction [accept=" + accept + "]";
	}

	public AdminReviewAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminReviewAction(Boolean accept) {
		super();
		this.accept = accept;
	}

	public Boolean getAccept() {
		return accept;
	}

	public void setAccept(Boolean accept) {
		this.accept = accept;
	}
    
    
    
}


