
# iQuantum ![Project Status](https://img.shields.io/badge/Project-Beta%20Release-yellow) [![iQuantum Maven Build](https://github.com/Cloudslab/iQuantum/actions/workflows/maven.yml/badge.svg)](https://github.com/Cloudslab/iQuantum/actions/workflows/maven.yml)

##  A Toolkit for Modeling and Simulation of Quantum Computing Environments

As quantum computers become increasingly accessible through
cloud services and potentially edge networks, the demand for efficient resource
management strategies and service models has grown.
iQuantum is a pioneering simulation toolkit (based on CloudSim) designed to model hybrid quantum-classical
computing environments, enabling researchers to prototype and evaluate system design and
resource management algorithms. iQuantum addresses the challenges posed by the limited
availability and accessibility of quantum resources, catalyzing research in
quantum software and systems.

## Main Features
The iQuantum project aims to facilitate research in quantum software and systems,
particularly focusing on resource management, job scheduling, and hybrid
task orchestration in quantum computing environments that
integrate cloud and edge computing resources.
- Modeling quantum computing entities: Quantum Datacenters (QDatacenter),
  Quantum Computers (QNode), QPU, Quantum Tasks (QTask), and Quantum Broker (QBroker).
- Modeling classical computing entities: Classical Datacenters (CDatacenter),
  Classical Computers (CNode), CPU, Classical Tasks (CTask), and Classical Broker (CBroker).
- Modeling Gateways and offloading policies from edge to cloud layer.
- Modeling QTask scheduling, qubit mapping, and quantum node selection policies for quantum computing environments.
- Modeling Hybrid Quantum-Classical Task Orchestration in the Cloud-Edge Continuum.
- Importing quantum nodes and workload dataset (from calibration data and QASM files) and exporting simulation results.

## Setup and Using
Please refer to the [Release page](https://github.com/Cloudslab/iQuantum/releases) to access the latest release of iQuantum (1.0.0-beta).
The downloaded package contains the main jar file (`iquantum-1.0.0-beta.jar`) and the entire source code of iQuantum.
### Prerequisites
- Java SDK 17+ ([corretto-17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html) is recommended).
- [Apache Maven](https://maven.apache.org/download.cgi) (All library management and build automation in iQuantum are handled by Maven; version 3.9+ is recommended)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (latest version, 2023+) is highly recommended for customizing the source code (e.g., resource management policies or other entities)

### Building from Source
To build iQuantum from source, please follow these steps:
1. Clone the iQuantum repository to your local machine.
2. Open the project in IntelliJ IDEA.
3. Build the project using Maven.
4. The built jar file (`iquantum-1.0.0-beta.jar`) will be located in the `/modules/iquantum/target` folder.
5. Add the built jar file to your project's classpath.
6. You are ready to use iQuantum in your project.

### Examples of Operation
Several examples that demonstrate the use of iQuantum can be found in the `org.iquantum.examples` package, located in the `/modules/iquantum-examples` folder.

![iQuantum Example](documentation/figures/examples.jpg "iQuantum Example")
- `quantum`: Examples of modeling quantum computing environments.
- `cloudsim`: Examples of modeling classical cloud computing environments (derived from CloudSim).
- `hybrid`: Examples of modeling hybrid quantum-classical computing environments in cloud and edge layers.
- `experimental`: Experimental examples of extending iQuantum with new features.

## Contributing
Pull requests are welcome.
For major changes, please open an issue first to discuss what you would like to change.

## References
[1] Hoa T. Nguyen, Muhammad Usman, and Rajkumar Buyya, iQuantum: A Case for Modeling and Simulation of Quantum Computing Environments, Proceedings of the 2023 IEEE International Conference on Quantum Software (QSW 2023, IEEE CS Press, USA), Chicago, USA, July 2-8, 2023.
## License
[GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.en.html)
