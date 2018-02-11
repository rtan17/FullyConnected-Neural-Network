public class Trainer {
    ANN network;

    public Trainer(ANN network) {
        this.network = network;
    }

    // Method to train a neural network with backpropagation
    void train(TrainingData[] data, double learningRate) {
        boolean inputSizeFits, outputSizeFits;

        // Check for error in Data.
        int dataPoints = data.length;
        for (int dataID = 0; dataID < dataPoints; dataID++) {
            inputSizeFits = data[dataID].inputs.length == this.network.layers.get(0).nodes.size();
            outputSizeFits = data[dataID].outputs.length == this.network.layers.get(this.network.layers.size() - 1).nodes.size();

            if ( !(inputSizeFits && outputSizeFits) ) {
                throw new IllegalArgumentException("Some data does not have the correct size to fit the network");
            }
        }

        // Backpropagation
        for (int dataID = 0; dataID < dataPoints; dataID++){

            // Feed forward
            this.network.evaluateInputs(data[dataID].inputs);

            // Gradient decent
            int layers = this.network.layers.size();
            // For every layer we backpropagate.
            for (int layerID = layers - 1; layerID > 0; layerID--){

                // If last layer
                if (layerID == layers - 1){
                    this.backpropagateLastLayer(data, dataID, learningRate);

                // Else
                } else {
                    /* Insert general function here */
                }
            }

            // Update learned weights
            this.updateLearnedWeights();
        }



    }

    private void backpropagateLastLayer(TrainingData[] data, int dataID, double learningRate){
        double d;
        Weight thisWeight;

        int layerID = this.network.layers.size()-1; // ID of the last layer

        // For every node in the last layer
        int nodes = this.network.layers.get(layerID).nodes.size();
        for (int nodeID = 0; nodeID < nodes; nodeID++){

            // For every weight connected to every node
            int weights = this.network.layers.get(layerID).nodes.get(nodeID).weights.size();
            for (int weightID = 0; weightID < weights; weightID++){
                d = 1;
                d *= this.network.layers.get(layerID).nodes.get(nodeID).value - data[dataID].outputs[nodeID];
                d *= sigmoidPrime(this.network.layers.get(layerID).nodes.get(nodeID).value);
                d *= this.network.layers.get(layerID).nodes.get(nodeID).weights.get(weightID).startNode.value;

                thisWeight = this.network.layers.get(layerID).nodes.get(nodeID).weights.get(weightID);
                thisWeight.updateValue = thisWeight.weight - learningRate * d;
            }
        }
    }

    private void updateLearnedWeights(){
        int layers = this.network.layers.size();
        for (int layerID = layers - 1; layerID > 0; layerID--){

            int nodes = this.network.layers.get(layerID).nodes.size();
            for (int nodeID = 0; nodeID < nodes; nodeID++){

                int weights = this.network.layers.get(layerID).nodes.get(nodeID).weights.size();
                for (int weightID = 0; weightID < weights; weightID++){
                    this.network.layers.get(layerID).nodes.get(nodeID).weights.get(weightID).updateWeight();
                }
            }
        }
    }

    private double sigmoidPrime(double x){
        return x * (1 - x);
    }


}
