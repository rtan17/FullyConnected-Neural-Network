import java.util.ArrayList;

public class ANN {
    ArrayList<Layer> layers = new ArrayList<>();

    ANN() {
        // TO-DO: Find en bedre metode til at have en helt tom construtor.
    }

    ANN(int inputs, int outputs, int hiddenLayers, int hiddenNeurons) {
        Layer currentLayer, nextLayer;

        // Add layers
        this.addLayer(inputs);

        for (int i = 0; i < hiddenLayers; i++) {
            this.addLayer(hiddenNeurons);
        }

        this.addLayer(outputs);

    }

    boolean addLayer(int nodes) {
        boolean addSucces, connectSucces = false;
        int totalLayers;
        Layer currentLayer, pastLayer;

        // Add layer
        addSucces = this.layers.add(new Layer(nodes));

        // Connect layers
        totalLayers = this.layers.size();

        if (totalLayers > 1) {   // We need atleast 2 layers to connect them.
            currentLayer = this.layers.get(totalLayers - 1);
            pastLayer = this.layers.get(totalLayers - 2);

            connectSucces = pastLayer.connectTo(currentLayer);

        } else {
            connectSucces = true;
        }

        return addSucces && connectSucces;
    }

    double[] evaluateInputs(double[] inputs) {
        int totalLayers = layers.size();
        double[] output;

        if (inputs.length != layers.get(0).nodes.size()) {
            throw new IllegalArgumentException("The input size, is not equal to the input layer size: " + inputs.length + " vs. " + layers.get(0).nodes.size());
        }

        // Set inputs
        for (int i = 0; i < layers.get(0).nodes.size(); i++) {
            layers.get(0).nodes.get(i).value = inputs[i];
        }

        // Update layers
        for (int layerID = 1; layerID < totalLayers; layerID++) {
            layers.get(layerID).updateLayer();
        }

        // Output
        output = layers.get(totalLayers - 1).nodeValues();
        return output;
    }

}
