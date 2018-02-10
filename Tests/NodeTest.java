import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    // Tests if the link between nodes was made by the weight
    @Test
    void nodeWeightLink01() {
        Node oldNode = new Node();
        Node newNode = new Node();
        Weight weight = new Weight(oldNode);

        boolean weightAppended = newNode.appendWeight(weight);

        assertTrue(weightAppended);
        assertEquals(weight.startNode, newNode.weights.get(0).startNode);
    }

    @Test
    void nodeWeightLink02() {
        Node oldNode = new Node();
        Node newNode = new Node();
        Weight weight = new Weight(oldNode, 0.5);

        boolean weightAppended = newNode.appendWeight(weight);

        assertTrue(weightAppended);
        assertEquals(weight.startNode, newNode.weights.get(0).startNode);
    }

    // Tests if the weight actually got appended
    @Test
    void nodeWeightAppend01() {
        Node oldNode = new Node();
        Node newNode = new Node();
        Weight weight = new Weight(oldNode);

        boolean weightAppended = newNode.appendWeight(weight);

        assertTrue(weightAppended);
        assertEquals(weight, newNode.weights.get(0));
    }

    @Test
    void nodeWeightAppend02() {
        Node oldNode = new Node();
        Node newNode = new Node();
        Weight weight = new Weight(oldNode, 0.5);

        boolean weightAppended = newNode.appendWeight(weight);

        assertTrue(weightAppended);
        assertEquals(weight, newNode.weights.get(0));
    }

    // Tests if the weight had the expected weight value
    @Test
    void nodeWeightValue01() {
        Node oldNode = new Node();
        Node newNode = new Node();
        Weight weight = new Weight(oldNode);

        boolean weightAppended = newNode.appendWeight(weight);

        assertTrue(weightAppended);
        assertEquals(weight.weight, newNode.weights.get(0).weight);
    }

    @Test
    void nodeWeightValue02() {
        Node oldNode = new Node();
        Node newNode = new Node();
        Weight weight = new Weight(oldNode, 0.5);

        boolean weightAppended = newNode.appendWeight(weight);

        assertTrue(weightAppended);
        assertEquals(0.5, newNode.weights.get(0).weight);
    }

    // Tests if a node can update itself
    private double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }

    @Test
    void updateNode01() {
        Node oldNode = new Node();
        oldNode.value = 1;

        Node newNode = new Node();
        newNode.value = 0;

        Weight weight = new Weight(oldNode, 0.5);

        boolean weightAppended = newNode.appendWeight(weight);
        boolean nodeUpdated = newNode.updateNode();

        double x = 1 * 0.5;
        double expected = sigmoid(x);

        assertTrue(weightAppended && nodeUpdated);
        assertEquals(expected, newNode.value);
    }

    @Test
    void updateNode02() {
        Node oldNode = new Node();
        oldNode.value = 1;

        Node newNode = new Node();
        newNode.value = 0;

        Weight weight = new Weight(oldNode, -0.5);

        boolean weightAppended = newNode.appendWeight(weight);
        boolean nodeUpdated = newNode.updateNode();

        double x = 1 * (-0.5);
        double expected = sigmoid(x);

        assertTrue(weightAppended && nodeUpdated);
        assertEquals(expected, newNode.value);
    }

    @Test
    void updateNode03() {
        Node oldNode = new Node();
        oldNode.value = 1;

        Node newNode = new Node();
        newNode.value = 0;

        Weight weight1 = new Weight(oldNode, 0.5);
        Weight weight2 = new Weight(oldNode, -0.5);

        boolean weightsAppended = newNode.appendWeight(weight1);
        weightsAppended = weightsAppended && newNode.appendWeight(weight2);
        boolean nodeUpdated = newNode.updateNode();

        double x = 1 * (0.5) + 1 * (-0.5);
        double expected = sigmoid(x);

        assertTrue(weightsAppended && nodeUpdated);
        assertEquals(expected, newNode.value);
    }
}