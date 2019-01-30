package ru.alex.vic.vk;

import com.vk.api.sdk.objects.base.Country;
import com.vk.api.sdk.objects.database.City;
import com.vk.api.sdk.objects.database.University;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.alex.vic.hhtool.HhtoolApplication;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = HhtoolApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:test-application.properties")
public class VkClientTest {
    @Autowired
    private VkClient vkClient;

    @Test
    public void testCountries() {
        final Optional<Country> country = vkClient.getCountryByCode("RU");
        Assert.assertEquals(1, country.get().getId().intValue());



    }

    @Test
    public void testCities() {
        final List<City> cities = vkClient.getCity(1, "Долгопрудный");
        Assert.assertEquals("Долгопрудный", cities.get(0).getTitle());
    }

    @Test
    public void testUniversities() {
        final List<City> cities = vkClient.getCity(1, "Долгопрудный");
        final Integer cityId = cities.get(0).getId();
        final List<University> universities = vkClient.geUniversities(1, cityId, "");
        System.out.println("universities = " + universities);

    }

   /* @Configuration
    @EnableConfigurationProperties(VkConfig.class)
    @TestPropertySource("classpath:application.properties")
    @ComponentScan("ru.alex.vic.vk")
    public static class SpringConfig {
        public SpringConfig() {
        }
    }*/
}