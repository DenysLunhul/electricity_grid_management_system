# Electricity System Management

A comprehensive Java-based electricity billing and power grid management system that handles multiple consumer types, meter types, and tariff structures.

## 📋 Overview

This system provides a complete solution for managing electricity consumers, meters, readings, and billing. It supports three types of consumers (Residential, Commercial, Industrial) and three types of meters (Single, Dual, and Triple tariff), with automated billing calculations based on consumption patterns.

## 🏗️ Project Structure

```
Electricity_System/
├── src/
│   ├── Main.java                          # Application entry point
│   ├── billing/
│   │   ├── Bill.java                      # Bill entity with display formatting
│   │   ├── Reading.java                   # Abstract reading base class
│   │   ├── SingleTariffReading.java       # Single tariff meter reading
│   │   ├── DualTariffReading.java         # Day/Night tariff reading
│   │   └── TripleTariffReading.java       # Peak/Day/Night tariff reading
│   ├── consumers/
│   │   ├── Consumer.java                  # Abstract consumer base class
│   │   ├── ResidentialConsumer.java       # Residential consumer implementation
│   │   ├── CommercialConsumer.java        # Commercial consumer implementation
│   │   └── IndustrialConsumer.java        # Industrial consumer implementation
│   ├── meters/
│   │   ├── Meter.java                     # Abstract meter base class
│   │   ├── SingleTariffMeter.java         # Single rate meter
│   │   ├── DualTariffMeter.java           # Day/Night rate meter
│   │   └── TripleTariffMeter.java         # Peak/Day/Night rate meter
│   ├── services/
│   │   └── BillingService.java            # Bill generation and calculation service
│   ├── system/
│   │   └── PowerGridSystem.java           # Core system management
│   ├── tariff/
│   │   └── ConsumerType.java              # Consumer types and pricing enum
│   └── ui/
│       └── ConsoleUI.java                 # Interactive console interface
└── README.md
```

## ✨ Features

### Consumer Management
- **Add/Remove Consumers**: Register and manage electricity consumers in the system
- **Multiple Consumer Types**:
  - **Residential**: 4 UAH/kWh
  - **Commercial**: 10 UAH/kWh
  - **Industrial**: 8 UAH/kWh
- **Consumer Information**: Track ID, name, address, and associated meters

### Meter Management
- **Multiple Meter Types**:
  - **Single Tariff**: Standard single-rate meter
  - **Dual Tariff**: Day and night rate meter (night consumption weighted at 0.5x)
  - **Triple Tariff**: Peak, day, and night rate meter
- **Add/Remove Meters**: Associate multiple meters with consumers
- **Reading History**: Maintain historical readings for each meter
- **Consumption Calculation**: Automatic calculation based on reading differences

### Billing System
- **Automatic Bill Generation**: Calculate total consumption across all consumer meters
- **Tariff-Based Pricing**: Apply consumer-specific rates (Residential/Commercial/Industrial)
- **Formatted Bill Display**: Professional bill formatting with consumer details
- **Unique Bill IDs**: Auto-generated bill identification (BILL00001, BILL00002, etc.)

### User Interface
- **Interactive Console Menu**: Easy-to-use command-line interface
- **Consumer Operations**: Add, remove, and display consumers
- **Meter Operations**: Add, remove, and display meters
- **Reading Entry**: Input meter readings with date tracking
- **Bill Generation**: Generate and display bills for consumers

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Java IDE (IntelliJ IDEA, Eclipse, etc.) or command-line compiler

### Installation

1. **Clone or download the project**:
   ```bash
   cd /path/to/Electricity_System
   ```

2. **Compile the project**:
   ```bash
   javac -d out src/**/*.java src/*.java
   ```

3. **Run the application**:
   ```bash
   java -cp out Main
   ```

### Alternative (Using IDE)
1. Open the project in IntelliJ IDEA or your preferred IDE
2. Wait for the project to index
3. Run `Main.java`

## 📖 Usage Guide

### Main Menu Options

```
═══════════════════════════════════════
    POWER GRID MANAGEMENT SYSTEM
═══════════════════════════════════════
1. Add Consumer
2. Remove Consumer
3. Display All Consumers
4. Add Meter to Consumer
5. Remove Meter from Consumer
6. Display Consumer Meters
7. Enter Meter Reading
8. Generate Bill
0. Exit
═══════════════════════════════════════
```

### Basic Workflow

1. **Add a Consumer**
   - Select option 1
   - Enter consumer name and address
   - Choose consumer type (Residential/Commercial/Industrial)
   - System generates unique consumer ID

2. **Add a Meter to Consumer**
   - Select option 4
   - Enter the consumer ID
   - Choose meter type (Single/Dual/Triple tariff)
   - Enter installation date
   - System generates unique meter ID

3. **Enter Meter Readings**
   - Select option 7
   - Enter consumer ID and meter ID
   - Input reading values based on meter type:
     - Single: 1 value (total kWh)
     - Dual: 2 values (day kWh, night kWh)
     - Triple: 3 values (peak kWh, day kWh, night kWh)

4. **Generate Bill**
   - Select option 8
   - Enter consumer ID
   - System calculates total consumption from all meters
   - Displays formatted bill with total amount due

### Example Usage Scenario

```
1. Add Residential Consumer "John Doe" at "123 Main St"
   → Consumer ID: C00001

2. Add Single Tariff Meter to C00001
   → Meter ID: M00001

3. Enter first reading for M00001: 1000 kWh
4. Enter second reading for M00001: 1250 kWh

5. Generate Bill for C00001
   → Consumption: 250 kWh
   → Rate: 4 UAH/kWh (Residential)
   → Total: 1000 UAH
```

## 🔧 Technical Details

### Object-Oriented Design
- **Inheritance**: Consumer and Meter classes use abstract base classes
- **Polymorphism**: Different consumer and meter types with specialized behavior
- **Encapsulation**: Private fields with getter methods
- **Composition**: Consumers contain multiple meters; meters contain reading history

### Key Classes

#### Consumer (Abstract)
- Base class for all consumer types
- Manages consumer information and meter collection
- Abstract method `getType()` implemented by subclasses

#### Meter (Abstract)
- Base class for all meter types
- Maintains reading history
- Calculates consumption based on reading differences
- Abstract method `updateReadings()` implemented by subclasses

#### PowerGridSystem
- Central system management
- Consumer and meter registration
- ID generation for consumers and meters
- CRUD operations for consumers and meters

#### BillingService
- Static utility for bill generation
- Consumption calculation across multiple meters
- Cost calculation based on consumer type tariff

### Tariff Calculation

**Single Tariff**:
```
Consumption = Current Reading - Previous Reading
Cost = Consumption × Consumer Rate
```

**Dual Tariff**:
```
Effective Reading = Day Reading + (Night Reading × 0.5)
Consumption = Current Effective - Previous Effective
Cost = Consumption × Consumer Rate
```

**Triple Tariff**:
```
Effective Reading = Peak + Day + (Night × 0.5)
Consumption = Current Effective - Previous Effective
Cost = Consumption × Consumer Rate
```

## 🎯 Future Enhancements

Potential improvements for the system:
- Database integration for persistent storage
- GUI interface (JavaFX or Swing)
- Payment tracking and history
- Monthly/yearly consumption reports
- Email notifications for bills
- Export bills to PDF
- Multi-currency support
- Advanced analytics and statistics
- RESTful API for integration
- User authentication and authorization

## 📝 License

This project is created for educational purposes.

## 👥 Contributing

This is an educational project. Feel free to fork and modify for your own learning purposes.

## 📧 Contact

For questions or suggestions about this project, please refer to the project repository.

---

**Note**: All prices are displayed in UAH (Ukrainian Hryvnia). The system uses current date for billing and readings automatically via `LocalDate.now()`.