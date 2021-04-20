package br.com.prcompany.msscbeerservice.listeners;

import br.com.prcompany.beerevents.events.ValidateBeerOrderRequest;
import br.com.prcompany.beerevents.events.ValidateBeerOrderResult;
import br.com.prcompany.beerevents.utils.EventsConstants;
import br.com.prcompany.msscbeerservice.services.order.BeerOrderValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidateOrderListener {

    private final BeerOrderValidator beerOrderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = EventsConstants.VALIDATE_ORDER_QUEUE)
    public void listenValidateOrder(@Payload ValidateBeerOrderRequest beerOrderRequest) {
        Boolean isValid = this.beerOrderValidator.validateOrder(beerOrderRequest.getBeerOrderDTO());
        this.jmsTemplate.convertAndSend(EventsConstants.VALIDATE_ORDER_RESULT_QUEUE, ValidateBeerOrderResult.builder()
                .isValid(isValid)
                .orderId(beerOrderRequest.getBeerOrderDTO().getId())
                .build());
    }
}
