package com.york;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.UUID;

import com.connectifier.xeroclient.XeroClient;
import com.connectifier.xeroclient.models.Contact;
import com.connectifier.xeroclient.models.Invoice;
import com.connectifier.xeroclient.models.InvoiceType;
import com.connectifier.xeroclient.models.LineItem;
import com.google.common.collect.ImmutableList;

/**
 * Generate a unique number
 *
 */
public class App 
{

    public static void main( String[] args )
    {
    	String consumerKey="GHV02AGMPCWXHNHIWDQEQQIYSEMHSF";
    	String consumerSecret="PVPONOKYIGSLF5IP2EABMMNIAB8R1R";
    	String xero_privatekey_pem="C:\\OpenSSL\\bin\\xero_privatekey.pem";
    	
        Reader pemReader=null;
		try {
			pemReader = new FileReader(new File(xero_privatekey_pem));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		XeroClient client = new XeroClient(pemReader, consumerKey, consumerSecret);

		
		
		List<Invoice> invoices=client.getInvoices();
		List<Contact> contacts=client.getContacts();
		
		System.out.println("invoices.length=" + invoices.size() );
		System.out.println("contacts.length=" + contacts.size() );
		System.out.println("branch 1001 invoices.length=" + invoices.size() );
	    
        App obj = new App();

        System.out.println("Unique ID : " + obj.generateUniqueKey());
    }
   
    
    public static void createContact(XeroClient client ){
    	Contact contact = new Contact();

    }
    
    
    public static void createInvoice(XeroClient client ){
    	Invoice invoice = new Invoice();
    	invoice.setType(InvoiceType.ACCREC);
    	invoice.setContact(client.getContacts().get(0));
    	LineItem item = new LineItem();
    	item.setDescription("Test Invoice");
    	invoice.setLineItems(ImmutableList.of(item));
    	client.createInvoice(invoice);
    }
    
    public String generateUniqueKey(){
    	
    	String id = UUID.randomUUID().toString();
    	return id;
    	
    }
}
