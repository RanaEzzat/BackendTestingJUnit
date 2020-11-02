package org.acme.resteasy.stubsmocksdummiestestexample;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.acme.resteasy.stubsmocksdummies.Book;
import org.acme.resteasy.stubsmocksdummies.ExternalISBNDataService;
import org.acme.resteasy.stubsmocksdummies.StockManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Stubs, Mocks and Dummies

class StockManagementTests {
	
	ExternalISBNDataService testWebService;
	ExternalISBNDataService testDatabaseService;
	StockManager stockManager;

	
	
	@BeforeEach
	public void setup()
	{
		stockManager = new StockManager();
		testWebService = mock(ExternalISBNDataService.class);
		testDatabaseService = mock(ExternalISBNDataService.class);
		stockManager.setWebService(testWebService);
		stockManager.setDatabaseService(testDatabaseService);
		System.out.println("setup running");
	}

	@Test
	public void testCanGetACorrectLocatorCode() {
		
				
		/*
		 * ExternalISBNDataService testWebService = new ExternalISBNDataService() {
		 * 
		 * @Override public Book lookup(String isbn) { return new
		 * Book(isbn,"Of Mice and Men","J. Steinbeck"); } };
		 * 
		 * ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
		 * 
		 * @Override public Book lookup(String isbn) { return null; } };
		 */
				
				
		
		when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396","Of Mice and Men","J. Steinback"));
		when(testDatabaseService.lookup(anyString())).thenReturn(new Book("0140177396","Of Mice and Men","J. Steinback"));
		
		String isbn="0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}
	
	@Test
	public void databaseIsUsedIfDataIsPresent()
	{
		
		when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
		
		stockManager.setWebService(testWebService);
		stockManager.setDatabaseService(testDatabaseService);
		
		String isbn="0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);

		verify(testDatabaseService,times(1)).lookup("0140177396");
		verify(testWebService,never()).lookup(anyString());

	}
	
	@Test
	public void webserviceIsUsedIfDataIsNotPresentInDatabase()
	{
		when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
		when(testDatabaseService.lookup("0140177396")).thenReturn(null);

		stockManager.setWebService(testWebService);
		stockManager.setDatabaseService(testDatabaseService);
		
		String isbn="0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);

		//verify(databaseService,times(1)).lookup("0140177396");
		//verify(webService,times(1)).lookup("0140177396");
		
		verify(testDatabaseService).lookup("0140177396");
		verify(testWebService).lookup("0140177396");
	}

}
