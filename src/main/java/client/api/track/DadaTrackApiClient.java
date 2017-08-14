package client.api.track;


import client.api.track.model.ResponseMessage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by kimiyu on 16/4/18.
 */
@FeignClient("sjes-api-track")
@RequestMapping(value = "/dadaTracks", consumes = MediaType.APPLICATION_JSON_VALUE)
public interface DadaTrackApiClient {

    @RequestMapping(value = "/submitOrderOfNewDada", method = RequestMethod.POST)
    String submitOrderOfNewDada(@RequestParam("orderId") Long orderId);

    @RequestMapping(value = "/reAddOrderOfNewDada/{orderId}", method = RequestMethod.GET)
    String reAddOrderOfNewDada(@PathVariable("orderId") Long orderId);


    /**
     * 老接口
     *
     * @param orderId
     */
    @RequestMapping(value = "/customer/{orderId}", method = RequestMethod.GET)
    ResponseMessage submitOrderForCustomer(@PathVariable("orderId") Long orderId);

    @RequestMapping(value = "/reAddOrder/{orderId}", method = RequestMethod.GET)
    ResponseMessage reAddOrder(@PathVariable("orderId") Long orderId);

}
