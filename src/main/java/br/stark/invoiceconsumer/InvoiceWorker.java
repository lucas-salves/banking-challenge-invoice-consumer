/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.stark.invoiceconsumer;

import br.stark.invoiceconsumer.config.AMQPConfig;
import br.stark.invoiceconsumer.consumer.InvoiceConsumer;

/**
 *
 * @author lucas
 */
public class InvoiceWorker {

    private static final String QUEUE_NAME = "Shopee_Estoque";

    public static void main(String[] args) throws Exception {

        var connection = AMQPConfig.getConnection();

        var channel = connection.createChannel();

        channel.basicQos(0);

//        queue, durable, exclusive, autoDelete, properties
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        //requeue
        channel.basicRecover(true);

        var callback = new InvoiceConsumer(channel);

        //queue, autoAck, callback
        channel.basicConsume(QUEUE_NAME, false, callback);

        System.out.println("waiting message");
    }
}
