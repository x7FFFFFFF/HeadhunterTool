package ru.alex.vic.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.base.Country;
import com.vk.api.sdk.objects.database.City;
import com.vk.api.sdk.objects.database.University;
import com.vk.api.sdk.objects.database.responses.GetCitiesResponse;
import com.vk.api.sdk.objects.database.responses.GetCountriesResponse;
import com.vk.api.sdk.objects.database.responses.GetUniversitiesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.alex.vic.hhtool.VkConfig;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Component
@EnableConfigurationProperties(VkConfig.class)
public class VkClient {


    @Autowired
    private VkConfig vkConfig;
    private UserActor actor;
    private VkApiClient vkClient;

    @PostConstruct
    private void init() {
        TransportClient transportClient = HttpTransportClient.getInstance();
        vkClient = new VkApiClient(transportClient);
        actor = new UserActor(vkConfig.getUserId(), vkConfig.getAccessToken());
    }


    public Optional<Country> getCountryByCode(String code) {
        final GetCountriesResponse response;
        try {
            response = vkClient.database().getCountries(actor).code(code).execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (response.getCount() == 0) {
            return Optional.empty();
        }
        return Optional.of(response.getItems().get(0));
    }

    public List<City> getCity(int countryid, String query) {
        try {
            final GetCitiesResponse citiesResponse = vkClient.database().getCities(actor, countryid).q(query).execute();
            return citiesResponse.getItems();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<University> geUniversities(int countryid, int cityId, String query) {
        try {
            final GetUniversitiesResponse universitiesResponse = vkClient.database()
                    .getUniversities(actor).cityId(cityId).countryId(countryid).q(query).execute();
            return universitiesResponse.getItems();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
