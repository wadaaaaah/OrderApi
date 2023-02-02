package com.accenture.order.service;

import com.accenture.order.entity.MagicWand;
import com.accenture.order.entity.Order;
import com.accenture.order.entity.WizardInfo;
import com.accenture.order.exception.BadRequestException;
import com.accenture.order.exception.OrderNotFoundException;
import com.accenture.order.repository.OrderRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    OrderRepository repo;
    @Autowired
    private WizardInfoService wizard;
    @Autowired
    private MagicWandService magic;

    RestTemplate restTemplate = new RestTemplate();

    public Order getOrderById(Long id){
        return repo.findById(id).orElseThrow(() ->
                new OrderNotFoundException(OrderNotFoundException.INVALID_ID + id));
    }

    public boolean checkOrderExist(Order order)
    {
        List<Order> existingOrder = repo.findOrdersByWizardId(order.getWizardId());

        for(Order checkOrder: existingOrder)
        {
            if (checkOrder.getWizardId().equals(order.getWizardId())
                    && checkOrder.getMagicName().equals(order.getMagicName()))
            {
                try {
                    throw new BadRequestException(BadRequestException.BAD_REQUEST);
                } catch (BadRequestException e) {
                    throw new RuntimeException(e);
                }
            }
            else
                break;
        }

        return true;
    }

    @SneakyThrows
    public boolean validateOrderInfo(Order order, WizardInfo wizard, MagicWand magic)
    {
        if(!wizard.isActive())
            throw new BadRequestException("Cannot Add Order: Wizard is inactive");
        else if(magic.getStock() == 0)
            throw new BadRequestException("Cannot Add Order: Magic Wand is out of stock");
        else if(wizard.getAge() < magic.getAgeLimit())
            throw new BadRequestException("Cannot Add Order: Wizard's age is below the age requirement");
        else if(order.getStock() > magic.getStock())
            throw new BadRequestException("Cannot Add Order: Stock quantity requested is more than available magic wand stock");
        else
            return true;
    }

    public String addOrder(Order order){
        boolean isOrderExist = checkOrderExist(order);

        if(isOrderExist == true)
        {
            WizardInfo wizardInfo = wizard.getWizardInfoById(order.getWizardId()).getBody();
            MagicWand magicWand = magic.getMagicWandById(order.getMagicId()).getBody();

            if (wizardInfo != null && magicWand != null)
            {
                boolean isOrderValid = validateOrderInfo(order, wizardInfo, magicWand);
                if (isOrderValid == true)
                {
                    magicWand.setStock(magicWand.getStock() - order.getStock());
                    magic.updateMagicWand(magicWand);
                    repo.save(order);

                }
            }
        }
        return "Order create successfully";
    }

    public void updateOrder(Long id, Order order){
        Order existingOrder = getOrderById(id);

        if(existingOrder != null)
        {
            WizardInfo wizardInfo = wizard.getWizardInfoById(order.getWizardId()).getBody();
            MagicWand magicWand = magic.getMagicWandById(order.getMagicId()).getBody();

            if(wizardInfo != null && magicWand != null)
            {
                boolean isOrderValid = validateOrderInfo(order, wizardInfo, magicWand);
                if (isOrderValid == true) {
                    Order updateOrder = new Order();
                    updateOrder.setOrderId(id);
                    updateOrder.setWizardId(order.getWizardId());
                    updateOrder.setMagicId(order.getMagicId());
                    updateOrder.setStock(order.getStock());

                    if(existingOrder.getStock() < updateOrder.getStock())
                    {
                        magicWand.setStock(magicWand.getStock() - (updateOrder.getStock() -
                                existingOrder.getStock()));
                        magic.updateMagicWand(magicWand);
                    }

                    else if(existingOrder.getStock() > updateOrder.getStock())
                    {
                        magicWand.setStock(magicWand.getStock() + (existingOrder.getStock() -
                                updateOrder.getStock()));
                        magic.updateMagicWand(magicWand);
                    }

                    repo.save(updateOrder);
                }
            }
        }

    }

    public void deleteOrder(Long id){
        Order order = getOrderById(id);
        MagicWand magicWand = magic.getMagicWandById(order.getMagicId()).getBody();

        if(magicWand != null){
            magicWand.setStock(magicWand.getStock() + order.getStock());
            magic.updateMagicWand(magicWand);
        }

        repo.deleteById(id);
    }

}
