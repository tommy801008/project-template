package se.chas.projecttemplate.paginator;

import java.io.Serializable;

import se.chas.projecttemplate.utils.Utilities;


public class Paginator implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2280311564075117240L;
	
	private int offset;
	private int numberToGet;
	private int recordsTotal;
	private int pageIndex;
	private int pages;
	
	// Constructor
	public Paginator() {
		
	}
	
	// Constructor 2
	public Paginator(int offset, int numbersToGet, int totalRecords) {
		setOffset(offset);
		setNumberToGet(numbersToGet);
		setRecordsTotal(totalRecords);
		updatePages();
		setPageIndex(1);
	}
	
	// Constructor 3
	public Paginator(int offset, int numbersToGet) {
		setOffset(offset);
		setNumberToGet(numbersToGet);
		setPageIndex(1);
	}

	public void next() {
		int recordsTemp = offset + numberToGet;
		if (recordsTemp <= recordsTotal) {
			offset = recordsTemp;
		}else {
			numberToGet = (Utilities.safeLongToInt(recordsTotal) - offset);
		}
		
		if(getPageIndex() < getPages()) {
            this.pageIndex++;
        }else {
			setPageIndex(this.pages);
		}
	}
	
	public void prev() {
		int recordsTemp = offset - numberToGet;
		if (recordsTemp > 0) {
			offset = recordsTemp;
		}else {
			setOffset(0);
		}
		
		if (getPageIndex() > getPages()) {
			setPageIndex(this.pages);
		}else if(this.pageIndex > 1) {
            this.pageIndex--;
        }
	}
	
	public void first() {
		setOffset(0);
		setPageIndex(1);
	}
	
	public void last() {
		setPageIndex(getPages());
		setOffset((Utilities.safeLongToInt(recordsTotal) - numberToGet));
	}
	
	public void updatePages() {
		
		if ( (recordsTotal % numberToGet) == 0) {
			setPages(recordsTotal/numberToGet);
		} else {
			setPages( (recordsTotal/numberToGet) + 1);
		}
	}
	
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int firstRecord) {
		this.offset = firstRecord;
	}

	public int getNumberToGet() {
		return numberToGet;
	}

	public void setNumberToGet(int records) {
		this.numberToGet = records;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "Paginator [offset=" + offset + ", numberToGet=" + numberToGet
				+ ", recordsTotal=" + recordsTotal + ", pageIndex=" + pageIndex
				+ ", pages=" + pages + "]";
	}

	
}
