package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor //final이 붙은 변수를 받는 생성자를 만들어줌(생성자가 하나이므로 자동으로 의존관계 주입)
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(
            @RequestParam String itemName,
            @RequestParam int price,
            @RequestParam Integer quantity,
            Model model
    ){

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemv2(
            @ModelAttribute("item") Item item
    ){
        itemRepository.save(item);

        //model.addAttribute("item", item); 생략가능 <- @ModelAttribute("item")과 같이 선언하면 자동으로 모델에 추가해줌
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemv3(
            @ModelAttribute Item item
    ){
        itemRepository.save(item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemv4(Item item){
        itemRepository.save(item);
        return "basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    //@PostMapping("/add")
    public String addItemv5(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }


    @PostMapping("/add")
    public String addItemv6(Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }




    //test를 위해 서버 시작히 임의의 데이터 2개를 생성해서 넣어 놓음 -> test를 위해 일일이 계속 새 상품을 등록하는 것은 번거로우므로
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("ItemA", 1000, 3));
        itemRepository.save(new Item("ItemB", 30000, 20));
    }
}
