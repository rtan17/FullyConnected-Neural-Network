public class Trainer {
    ANN network;

    public Trainer(ANN network) {
        this.network = network;
    }

    // Method to train a neural network with backpropagation
    void train(TrainingData[] data, double learningRate) {
        boolean inputSizeFits, outputSizeFits;
        Node currentNode, nextNode;

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

            int layers = this.network.layers.size();
            // For every layer we backpropagate.
            for (int layerID = layers - 1; layerID > 0; layerID--){

                // For every node in the layer
                int nodes = this.network.layers.get(layerID).nodes.size();
                for (int nodeID = 0; nodeID < nodes; nodeID++){

                    // Calculate every nodes effect on the TE.
                    currentNode = this.network.layers.get(layerID).nodes.get(nodeID);
                    if (layerID == layers - 1){
                        currentNode.effect = (currentNode.value - data[dataID].outputs[nodeID]);
                    } else {
                        currentNode.effect = 0;
                        int nodesInNextLayer = this.network.layers.get(layerID+1).nodes.size();
                        for (int nextNodeID = 0; nextNodeID < nodesInNextLayer; nextNodeID++){
                            nextNode = this.network.layers.get(layerID + 1).nodes.get(nextNodeID);

                            // Find the connecting node between currentNode and NextNode
                            int i = -1;
                            while (nextNode.weights.get(++i).startNode != currentNode); // Find a better solution, maybe change the way nodes and weights work

                            currentNode.effect += nextNode.weights.get(i).weight * sigmoidPrime(nextNode.value) * nextNode.effect;
                        }
                    }

                    // For every weight connected to every node in the layer, calculate the new weight*
                    int weights = this.network.layers.get(layerID).nodes.get(nodeID).weights.size();
                    for (int weightID = 0; weightID < weights; weightID++){
                        Weight currentWeight = this.network.layers.get(layerID).nodes.get(nodeID).weights.get(weightID);
                        double d = currentNode.effect * sigmoidPrime(currentNode.value) * currentWeight.startNode.value;
                        currentWeight.updateValue = currentWeight.weight - (learningRate * d);
                    }
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
