# Fully Connected Neural Network

A Java implementation of a fully connected neural network. 

Current features:
* A fully connected artificial neural network. (ANN)
* Scalable size of the network.
* Feed forward.

Future planned implementations:
* Backpropagation.
* Import / export of already trained ANN.

## Getting Started

A short introduction to the network.

### How to initialize a Neural Network

#### Fast method: Homogeneous network.

Constructor:
```Java
ANN(int inputs, int outputs, int hiddenLayers, int hiddenNeurons)
```

Implementation:
```Java
ANN neuralNetwork = New ANN(inputs, outputs, hiddenLayers, neuronsPrHiddenLayer)
```

#### Manual method: Inhomogeneous network.

Constructor:
```Java
ANN()
```

Methods:
```Java
boolean addLayer(int nodes) // Returns true if the layer was added to the network
```

Implementation:
```Java
ANN neuralNetwork = New ANN();
neuralNetwork.addLayer(NeuronsForThisLayer);
neuralNetwork.addLayer(Neurons..);  // A minimum of 2 layers is required.
neuralNetwork.addLayer(Neurons..);
```

### How to train a neural network

Methods:
```Java
void train(TrainingData[] data, double learningRate, int iterations)
```

Implementation:
```Java
// Initialize network and data
ANN neuralNetwork = new ANN(inputs, outputs, hiddenLayers, neuronsPrHiddenLayer)
double[] dataInputs = {*Input data*};
double[] dataOutputs = {*Input data*};
TrainingData[] data = {new TrainingData(dataInputs, dataOutputs)};

// Train network
Trainer trainer = new Trainer(neuralNetwork);
trainer.train(data, 0.5, 100);
```

### How to make a prediction based on input

Methods:
```Java
// Returns an array of doubles containing the outputs / predictions
double[] evaluateInputs(double[] inputs); 
```

Implementation:
```Java
ANN neuralNetwork = New ANN(inputs, outputs, hiddenLayers, neuronsPrHiddenLayer)
double[] inputs = {*Input data*};

double[] outputs = neuralNetwork.evaluateInputs(inputs);
```

## Authors

* **Niki Ewald Zakariassen** - *Initial work*

See also the list of [contributors](https://github.com/niki9796dk/FullyConnected-Neural-Network/graphs/contributors) who participated in this project.

## License
--To be made--

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* This code currently haven't been optimized in any way.

