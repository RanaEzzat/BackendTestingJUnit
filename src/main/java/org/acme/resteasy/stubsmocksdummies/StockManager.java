package org.acme.resteasy.stubsmocksdummies;

public class StockManager {

	private ExternalISBNDataService databaseService;
	private ExternalISBNDataService webService;

	
	public void setDatabaseService(ExternalISBNDataService service) {
		this.databaseService = service;
	}
	public void setWebService(ExternalISBNDataService service) {
		this.webService = service;
	}
	
	public String getLocatorCode(String isbn) {
		Book book=databaseService.lookup(isbn);
		if(book==null) 
			book=webService.lookup(isbn);
		StringBuilder locator = new StringBuilder();
		locator.append(isbn.substring(isbn.length()-4));
		locator.append(book.getAuthor().substring(0,1));
		locator.append(book.getTitle().split(" ").length);
		return locator.toString();
	}
	
	

}
