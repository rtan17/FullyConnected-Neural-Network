import java.util.ArrayList;

public class ANN {
    ArrayList<Layer> layers = new ArrayList<>();

    // Constructor for a fully size customizable network
    ANN() {
        // TO-DO: Find a better solution for an emptyy constructor.

        // Idea: Maybe change this, so it still requires inputs & output neurons to be defined
        // Would probably require changes in the addLayer function and more.
    }

    // Constructor for a homogeneous network
    ANN(int inputs, int outputs, int hiddenLayers, int hiddenNeurons) {
        Layer currentLayer, nextLayer;

        // Add layers
        this.addLayer(inputs);

        for (int i = 0; i < hiddenLayers; i++) {
            this.addLayer(hiddenNeurons);
        }

        this.addLayer(outputs);
    }

    // Method to add a layer to an already existing network
    boolean addLayer(int nodes) {
        boolean addSuccess, connectSucces = false;
        int totalLayers;
        Layer currentLayer, pastLayer;

        // Add layer
        addSuccess = this.layers.add(new Layer(nodes));

        // Connect layers
        totalLayers = this.layers.size();

        if (totalLayers > 1) {   // We need atleast 2 layers to connect them.
            currentLayer = this.layers.get(totalLayers - 1);
            pastLayer = this.layers.get(totalLayers - 2);

            connectSucces = pastLayer.connectTo(currentLayer);

        } else {
            connectSucces = true;
        }

        // Returns true if the layer was connected
        return addSuccess && connectSucces;
    }

    // Method to evaluate inputs and return the output / predictions
    double[] evaluateInputs(double[] inputs) {
        int totalLayers = layers.size();
        double[] output;

        // In case the input size does not fit the input layer size.
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
