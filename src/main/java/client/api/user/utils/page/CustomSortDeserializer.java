package client.api.user.utils.page;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.data.domain.Sort;

import java.io.IOException;

/**
 * Created by kimiyu on 15/9/11.
 */
public class CustomSortDeserializer extends JsonDeserializer<Sort> {
    @Override
    public Sort deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
//        ArrayNode node = jp.getCodec().readTree(jp);
//        Sort.Order[] orders = new Sort.Order[node.size()];
//        int i = 0;
//        for (JsonNode obj : node) {
//            orders[i] = new Sort.Order(Sort.Direction.valueOf(obj.get("direction").asText()), obj.get("property")
//                    .asText());
//            i++;
//        }
//        Sort sort = new Sort(orders);
//        return sort;
        ObjectNode node = jp.getCodec().readTree(jp);
        Sort.Order[] orders = new Sort.Order[node.size()];
        int i = 0;
        for (JsonNode obj : node) {
            if (obj.get("direction") != null) {
                orders[i] = new Sort.Order(Sort.Direction.valueOf(obj.get("direction").asText()), obj.get("property")
                        .asText());
            }
            i++;
        }
        Sort sort = new Sort(orders);
        return sort;

    }
}
