package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("아이템을 저장한다")
    void save () {
        //given
        Item item = new Item("itemA", 10000, 190);
        //when
        Item savedItem = itemRepository.save(item);
        //then
        Item findItemId = itemRepository.findById(savedItem.getId());
        assertThat(findItemId).isEqualTo(savedItem);
    }

    @Test
    @DisplayName("아이템을 찾는다")
    void findAll () {
        //given
        Item item1 = new Item("itemA", 10000, 190);
        Item item2 = new Item("itemB", 20000, 200);
        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);

    }

    @Test
    @DisplayName("아이템을 수정한다")
    void updateItem () {
        //given
        Item item = new Item("itemA", 10000, 190);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        //then
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());

    }

}