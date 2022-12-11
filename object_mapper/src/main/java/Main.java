import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("Hello world!");


        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("kwah");
        user.setAge(30);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("SEDAN");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가 2222");
        car2.setType("SUV");

        List<Car> carList = Arrays.asList(car1, car2);
        user.setCars(carList);

        System.out.println(user);

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);


        // JsonNode 를 통해서, 값을 가져 온다 (parsing)
        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();

        System.out.println("name : "+_name);
        System.out.println("age : "+_age);

        // 배열은 어떻게 ?
        // ArrayNode 와 convertValue() 메서드를 통해서 한다.
        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode) cars;

        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println(_cars);

        // (JsonNode의 set() 메서드가 없기 때문에) ObjectNode 를 통해서 JSON 값을 바꿀 수도 있다.
        // K, V 값을 바꾸는 형태이다.
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "hoon");
        objectNode.put("age", 20);
        System.out.println(objectNode.toPrettyString());
    }
}