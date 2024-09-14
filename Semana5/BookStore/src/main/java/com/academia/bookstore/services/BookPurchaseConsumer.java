package com.academia.bookstore.services;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.academia.bookstore.dto.BookPurchaseMessage;
import com.academia.bookstore.dto.Utils;
import com.academia.bookstore.models.Purchase;
import com.academia.bookstore.services.PurchaseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BookPurchaseConsumer implements MessageListener {

    @Autowired
    private PurchaseService purchaseService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onMessage(Message message) {
        try {
            BookPurchaseMessage bookPurchaseMessage = objectMapper.readValue(message.getBody(), BookPurchaseMessage.class);
            System.out.println("Received message: " + bookPurchaseMessage);
            savePurchaseDetails(bookPurchaseMessage);
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
        }
    }

    private void savePurchaseDetails(BookPurchaseMessage message) {
        Purchase purchase = new Purchase();
        String bookIdsString = Utils.convertLongListToCommaSeparatedString(message.getBookIds());
        purchase.setBookIds(bookIdsString);
        purchase.setTotalPrice(message.getTotalPrice());
        purchase.setTotalPriceWithVAT(message.getTotalPriceWithVAT());
        purchaseService.savePurchase(purchase);
        System.out.println("Purchase details saved to database: " + purchase);
    }
}