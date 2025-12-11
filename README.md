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
  * Body mass
  * Time step
  * Drag factor
* Run the simulation to observe the resulting fall profile.

## Simulation Outputs

* Altitude vs. time
* Velocity vs. time
* Acceleration vs. time

## Teamwork Summary
There are three members that were part of this project: Aswinth Sinnathamby, Muhammad Usman and Sahel Assadi.
Each member have contributed equally in the project. Here are some that each member did: 
* Usman:
* Sahel: Sahel has done all the views, such as the two scenes, the stage, scenebuilding, css coloring and organizing textfields, buttons and tableview. Also, he has done the StartScreenController class and done parts of the simulation controller class, such as popping out the error message, setting conditions for the data inputs and the return buton.
* Aswinth: Aswinth did the Skydiver class which holds the current state of the skydiver including position, velocity, acceleration, and net force. It also stores simulation parameters like mass, drag factor, and time step. He also did a lot of controllers classes. He also did the SimulationResults class holds the data for a single time step of the skydiving simulation. Each instance represents the state of the skydiver at a specific point in time. He did the ResultsController for the results screen shown after the simulation finishes. He did a lot of the SimulationController class.

