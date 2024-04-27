package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //실무에서는 멑티스레드 환경이므로 hashmap을 사용하지는 않음
    private static long sequence = 0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    //tip: 파라미터로 넘어오는 updateParam은 사실 새로운 DTO를 만드는 것이 맞음
    //(updateParam은 id는 필요 없고 item에서 수정할 data만 담을 객체 -> 명확성을 위해 새로운 domain으로 작성하는 것이 적합)
    //실무에서는 이런식으로 중복성 vs. 명확성 문제가 발생하면 명확성을 챙기는 방식으로 개발하는 것이 적합
    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore(){
        store.clear();
    }
}
