import concur.api.ConCurApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest(classes = ConCurApiApplication.class)
public class KafkaProducerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Test
    void test() {
        sendMessage("hihi");
    }

    private void sendMessage(String msg) {
        kafkaTemplate.send("c", msg);
    }
}
