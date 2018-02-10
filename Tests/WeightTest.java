import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightTest {

    @Test
    public void updateValue01(){
        Node node = new Node();
        Weight W = new Weight(node);

        assertEquals(0, W.updateValue);
    }

    @Test
    public void updateValue02(){
        Node node = new Node();
        Weight W = new Weight(node);
        W.updateValue = 1;

        W.updateWeight();

        assertEquals(1, W.weight);
    }
}