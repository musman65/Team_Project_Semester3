# Skydiving Simulator

## Overview

This project simulates and analyzes the physics, mechanics, and experience of skydiving. It includes cuztomizable paramaters (mass, starting height, drag factor, etc) and data visualization. The goal is to provide an educational and interactive tool for understanding how skydiving works from both a physical and technical perspective.

## Features

* **Free-Fall Simulation:** Models motion under gravity and air resistance.
* **Parachute Deployment System:** Demonstrates how drag changes once the parachute opens.
* **Data Visualization:** Graphs for velocity, acceleration, altitude over time, and terminal velocity.

## Project Structure

```
skydiver-simulator/
├── src/
│   ├── models/              # Motion equations, drag models, environment
│   ├── views/               # Interface and controls
│   ├── controllers/         # Event-Handling and handles communication between model and view classes
│   └── MainApp/          # Main file that runs the application
└── README.md           # This file
```

## Important Link(s):

 * Our Trello board: https://trello.com/b/K1BGEXcK/team-proposal
 * How to work with branches: https://gist.github.com/Alinaprotsyuk/3d58f8cd52eb03a11283d64beb0e083e

## Requirements

* Language: *Java*
* Dependencies:

  * *JavaFX*

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/musman65/Team_Project_Semester3.git
   ```
2. Navigate into the project folder:

   ```bash
   cd skydiving-project
   ```
   

## Usage

* Modify initial conditions such as:

  * Jump altitude
  * Body orientation
* Run the simulation to observe the resulting fall profile.

## Simulation Outputs

* Altitude vs. time
* Velocity vs. time
* Acceleration vs. time
