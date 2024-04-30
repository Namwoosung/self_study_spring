package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    //파라미터로 넘어온 aClass를 ItemValidator에서 검증 가능한 것인지 여부 반환
    @Override
    public boolean supports(Class<?> aClass) {
        return Item.class.isAssignableFrom(aClass);
        // item == aclass
        // item == subItem
        //=> aclass가 item혹은 item의 자식일 경우만 true
    }

    @Override
    public void validate(Object o, Errors errors) {
        Item item = (Item) o;

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){ //상품명이 비어있는 경우
            errors.rejectValue("itemName", "required");
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);

        }
        if(item.getQuantity() == null || item.getQuantity() > 9999){
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }
}
