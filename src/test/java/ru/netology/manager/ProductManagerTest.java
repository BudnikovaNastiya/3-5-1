package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;


class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book aliceInWonderland = new Book(1, "Alice In Wonderland", 500, "Lewis Carrol");
    private Book theLittlePrince = new Book(2, "The little prince", 400, "Antoine de Saint-Exupery");
    private Smartphone iPhoneXR = new Smartphone(3, "iPhone XP", 60000, "Apple");
    private Smartphone redmiNote9 = new Smartphone(4, "Redmi  Note 9", 50000, "Xiaomi");

    @BeforeEach
    public void setUp() {
        manager.add(aliceInWonderland);
        manager.add(redmiNote9);
        manager.add(iPhoneXR);
    }


    @Test
    public void shouldSaveOneItem() {
        manager.add(theLittlePrince);

        Product[] expected = new Product[]{aliceInWonderland,redmiNote9,iPhoneXR,theLittlePrince};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOneBook() {
        manager.searchBy("Alice In Wonderland");

        Product[] expected = new Product[] {aliceInWonderland};
        Product[] actual = manager.searchBy("Alice In Wonderland");
        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldNotFindOneSmartphone() {
        manager.searchBy("iPhone XR");

        Product[] expected = new Product[] {};
        Product[] actual = manager.searchBy("iPhone XR");
        assertArrayEquals(expected, actual);

    }


}