import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ANNTest {

    // Tests if a network can add layers to itself
    @Test
    void ANNConstructor01() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new ANN(0,1,1,1);
        });
    }

    @Test
    void ANNConstructor02() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new ANN(1,0,1,1);
        });
    }

    @Test
    void ANNConstructor03() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new ANN(1,0,1,0);
        });
    }

    // Tests if a network can add layers to itself
    @Test
    void addLayer01() {
        ANN network = new ANN();

        boolean layerAdded = network.addLayer(1);

        assertTrue(layerAdded);
    }

    @Test
    void addLayer02() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ANN network = new ANN();
            network.addLayer(0);
        });
    }

    // Tests if a network can evaluate inputs to outputs correctly
    @Test
    void evaluateInputs01() {
        ANN network = new ANN(1,1,0,0);
        network.layers.get(1).nodes.get(0).weights.get(0).weight = 1;
        double[] inputs = {0};
        double[] expectedOutput = {sigmoid(1)};

        assertArrayEquals(expectedOutput, network.evaluateInputs(inputs));
    }

    @Test
    void evaluateInputs02() {
        ANN network = new ANN(1,1,1,1);
        network.layers.get(1).nodes.get(0).weights.get(0).weight = 1;
        network.layers.get(2).nodes.get(0).weights.get(0).weight = 2;
        double[] inputs = {0};
        double x = sigmoid(1) * network.layers.get(2).nodes.get(0).weights.get(1).weight;
        double[] expectedOutput = {sigmoid(x+2)};

        assertArrayEquals(expectedOutput, network.evaluateInputs(inputs));
    }

    @Test
    void evaluateInputs03() {
        ANN network = new ANN(2,1,1,2);
        network.layers.get(1).nodes.get(0).weights.get(0).weight = 1;
        network.layers.get(1).nodes.get(1).weights.get(0).weight = 2;
        network.layers.get(2).nodes.get(0).weights.get(0).weight = 3;
        double[] inputs = {0,0};
        double x = sigmoid(1) * network.layers.get(2).nodes.get(0).weights.get(1).weight + sigmoid(2) * network.layers.get(2).nodes.get(0).weights.get(2).weight;
        double[] expectedOutput = {sigmoid(x + 3)};

        assertArrayEquals(expectedOutput, network.evaluateInputs(inputs));
    }

    @Test
    void evaluateInputs04() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ANN network = new ANN(2,1,1,2);
            double[] inputs = {0};

            network.evaluateInputs(inputs);
        });
    }

    @Test
    void bias01(){

    }

    private double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }
}