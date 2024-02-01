
# Menu Evaluation Project

This project is centered around evaluating the effectiveness of hierarchical menus in applications. Written in Java, it consists of two main programs: one for testing menu navigation and another for analyzing test results.

## Project Overview

- **Objective:** To assess how users navigate through complex menus to find specific actions, using the Opera GX browser's 'Settings' tab as a model.
- **Test Protocols:** Protocols are identified by unique alphanumeric codes, covering various tasks such as activating mouse shortcuts, modifying IPFS/IPNS settings, and customizing wallpapers.
- **Development:** Developed in Java without external borrowing, except for the official API.

## Features

### Test Program
- **User Interaction:** Testers navigate the menu, attempting to complete assigned tasks.
- **Data Recording:** Records the paths taken and actions selected by testers, storing them in a MariaDB database.

### Analysis Program
- **Statistical Analysis:** Presents data in two pie charts - one showing the distribution of chosen actions (with correct actions in green) and another displaying the number of sub-menus accessed during each test.

## Commands

- `make run-tests`: Compiles and launches the testing program.
- `make run-stats`: Compiles and launches the statistics program.
- `make build`: Compiles the project.
- `make clean`: Removes the compiled classes in the build folder.
- `make jar-tests`: Creates a .jar archive of the testing program.
- `make jar-stats`: Creates a .jar archive of the statistics program.
