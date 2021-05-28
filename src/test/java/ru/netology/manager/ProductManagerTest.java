package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    Product first = new Book(1, "Idiot", 500, "Dostoevsky");
    Product second = new Book(2, "Fahrenheit 451", 600, "Ray Bradbury");
    Product third = new Book(3, "War and Peace", 700, "Tolstoy");
    Product fourth = new Smartphone(4, "iPhone XR", 700, "Apple");
    Product fifth = new Smartphone(5, "iPhone 11", 900, "Apple");

    public void setUpProductList() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
    }


    @Test
    void searchItemByName() {
        setUpProductList();

        Product[] expected = new Product[]{third};
        Product[] actual = manager.searchBy("War and Peace");
        assertArrayEquals(expected, actual);

    }

    @Test
    void searchByManufacturer() {
        setUpProductList();
        Product[] expected = new Product[]{fourth};
        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthor() {
        setUpProductList();
        Product[] expected = new Product[]{third};
        Product[] actual = manager.searchBy("Tolstoy");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByBookName() {
        setUpProductList();
        Product[] expected = new Product[]{first};
        Product[] actual = manager.searchBy("Idiot");
        assertArrayEquals(expected, actual);

    }

    @Test
    void searchBySmartphoneName() {
        setUpProductList();
        Product[] expected = new Product[]{fourth};
        Product[] actual = manager.searchBy("iPhone XR");
        assertArrayEquals(expected, actual);

    }

    @Test
    void addProduct() {
        manager.add(first);
        assertArrayEquals(new Product[]{first}, repository.getAll());

    }

    @Test
    void searchAllItemsFromOneManufacturer() {
        setUpProductList();
        Product[] expected = manager.searchBy("Apple");
        Product[] actual = new Product[]{fourth, fifth};

    }

    @Test
    void searchNonExistentProduct() {
        setUpProductList();
        Product[] expected = manager.searchBy("Shantaram");
        Product[] actual = new Product[]{};
        assertArrayEquals(expected, actual);
    }


}
