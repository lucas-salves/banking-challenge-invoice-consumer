package br.stark.invoiceconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvoiceConsumerApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(InvoiceConsumerApplication.class, args);
                
                InvoiceWorker.main(args);
	}

}
