package store.data.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.models.Buyer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuyerRepositoryImplTest {
    private Buyer buyer;
    private Buyer secondBuyer;

    private static final BuyerRepository buyerRepository = new BuyerRepositoryImpl();

    @BeforeEach
    void setUp() {
        buyer = new Buyer();
        buyer.setFirstname("Godman");
        buyer.setLastname("Buhari");
        buyer.setEmail("emilokan2022@gmail.com");
        buyer.setPassword("IloveNaija2022");

        secondBuyer = new Buyer();
        secondBuyer.setFirstname("Jennifer");
        secondBuyer.setLastname("Emilokan");
        secondBuyer.setEmail("jenny123@gmail.com");
        secondBuyer.setPassword("jenny1234@");
    }

    @AfterEach
    void tearDown() {
        buyerRepository.deleteAll();
    }

    @Test
    void saveTest() {
//        before save
        assertEquals(0, buyer.getId());
        assertEquals(0, buyerRepository.findAll().size());
//        save buyer
        Buyer savedBuyer = buyerRepository.save(buyer);
//        buyer has id
        assertEquals(1, savedBuyer.getId());
//        there is one in db
        List<Buyer> buyersList = buyerRepository.findAll();
        assertEquals(1, buyersList.size());

//        save second buyer
        Buyer savedSecondBuyer = buyerRepository.save(secondBuyer);
//        second buyer's id is 2
        assertEquals(2, savedSecondBuyer.getId());
//        there are two buyers in the db
        buyersList = buyerRepository.findAll();
        assertEquals(2, buyersList.size());
    }

    @Test
    void findByIdTest() {
        Buyer firstSavedBuyer = buyerRepository.save(buyer);
        Buyer secondeSavedBuyer = buyerRepository.save(secondBuyer);

        Buyer foundBuyer = buyerRepository.findById(secondeSavedBuyer.getId());
        assertEquals(foundBuyer, secondeSavedBuyer);

    }

    @Test
    void findAllTest() {
        Buyer savedBuyer = buyerRepository.save(buyer);
        var listOfAllBuyersInDb = buyerRepository.findAll();
        assertEquals(1, listOfAllBuyersInDb.size());
    }

    @Test
    void deleteTest() {
    }

    @Test
    void deleteAllTest(){
        buyerRepository.save(buyer);
        assertEquals(1, buyerRepository.findAll().size());
        buyerRepository.deleteAll();
        assertEquals(0, buyerRepository.findAll().size());
    }
}