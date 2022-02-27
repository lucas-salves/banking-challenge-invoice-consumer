/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.stark.invoiceconsumer.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class InvoiceConsumer extends DefaultConsumer{
    
    private final Channel channel;

    public InvoiceConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }
    
    @Override
    public void handleDelivery(String csTag, Envelope env, AMQP.BasicProperties props, byte[] body) {
        try {

            var message = new String(body, "UTF-8");

            this.processDelivery(message);

            //tag, ack all messages
            channel.basicAck(env.getDeliveryTag(), false);

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(0);

        } catch (Exception ex) {
            Logger.getLogger(InvoiceConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void processDelivery(String message) throws Exception {

        System.out.println(message);
        
        
    }
    
}
