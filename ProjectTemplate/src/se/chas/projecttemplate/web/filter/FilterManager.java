package se.chas.projecttemplate.web.filter;

import java.io.Serializable;

public class FilterManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7804698102456945272L;

	private Filter filter;
	
	public FilterManager() {
		setFilter(new Filter());
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	
}
