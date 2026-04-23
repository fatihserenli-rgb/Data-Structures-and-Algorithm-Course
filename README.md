# Data-Structures-and-Algorithm-Course

# CSE222 - Data Structures and Algorithms

This repository contains my solutions for the assignments of the **CSE222 - Data Structures and Algorithms** course. All projects are developed using **Java**, focusing on implementing robust software architectures through advanced Object-Oriented Programming (OOP) techniques to manage system complexity effectively.
---

## HW1: Embedded System Communication Interface

### Description
[cite_start]The primary goal of this assignment is to design and implement a framework for controlling communication ports in an embedded system[cite: 4, 7]. [cite_start]The system supports multiple devices connecting via various communication protocols, ensuring that each port is managed efficiently and independently[cite: 8].

### Requirements & Features
* [cite_start]**Device Management:** Ability to add and remove devices (sensors, displays, motors, etc.) while adhering to specific type limits defined by the system[cite: 9, 11, 18].
* [cite_start]**Power Control:** Standardized operations to turn devices ON and OFF through the main system interface[cite: 16, 19].
* **Device-Specific Operations:**
    * [cite_start]**Sensors:** Reading and processing data from models like DHT11, BME280, and MPU6050 [cite: 21, 66-69, 118].
    * [cite_start]**Displays:** Printing data to LCD and OLED screens[cite: 23, 72, 124].
    * [cite_start]**Motor Drivers:** Controlling motor speeds via PCA9685 and SparkFunMD drivers[cite: 24, 74, 128].
    * [cite_start]**Wireless IO:** Simulating data transmission and reception for Bluetooth and Wifi modules[cite: 25, 73, 126].
* [cite_start]**System Monitoring:** Comprehensive listing of all ports (occupied or empty) and detailed lists of connected devices categorized by their specific types[cite: 12, 26, 27].
* [cite_start]**Supported Protocols:** Full implementation of communication interfaces for **I2C, SPI, UART,** and **OneWire** protocols[cite: 33, 34, 36, 37, 57].
* [cite_start]**Error Handling:** A robust mechanism to detect and report illegal operations—such as incompatible port assignments, device limit violations, or invalid references—without terminating the program execution[cite: 13, 172].
* [cite_start]**System Configuration:** Initialization of port configurations and device constraints via an external configuration file[cite: 144, 145].

### Constraints & Implementation Details
* **Language:** Java.
* [cite_start]**Strict OOP Rules:** Use of abstraction and interfaces for protocol and device hierarchies; direct field access is prohibited, and downcasting is strictly avoided[cite: 186, 187].
* [cite_start]**Allowed Libraries:** Only `java.io.File`, `java.util.ArrayList`, and `java.util.Scanner` are used[cite: 181, 182, 183].
* [cite_start]**Randomization:** The `Math` library is utilized for simulating realistic sensor data generation[cite: 185].
