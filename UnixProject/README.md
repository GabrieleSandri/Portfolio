# Unix Operating Systems: Chain Reaction Simulation 
### Participants:
- Sandri Gabriele  
- Sandri Mattia  

### Task:
The goal is to simulate a chain reaction. For this purpose, the following processes are present:  
1. A **master process** that manages the simulation and maintains statistics.  
2. **Atom processes** that split into additional atom processes, generating energy.  
3. An **activator process** that sends split commands to atom processes.  
4. An **inhibitor process** (can be deactivated) that absorbs part of the energy and turns some atom processes into waste.  
5. A **feeder process** that adds new atoms every `STEP_FEEDER` nanoseconds.  

### Project Structure:
- `/src` - Project source code  
- `/include` - Project header files  
- `/bin` - Files generated during compilation  
- `/logs` - Log file containing all operations performed during the simulation  
- `/config` - Configuration files  
- `/library` - External libraries used in the project  
- `MakeFile`  

### Implementation:
#### Communication between atom-activator and atom-inhibitor processes:
Atom processes communicate their PID to the activator or inhibitor through message queues.  
The activator and inhibitor, after retrieving the PID of an atom process from the message queue, send a signal to the atom process:  
- **SIGUSR1** for activation  
- **SIGUSR2** for inhibition  

#### Splitting:
When receiving an activation signal, an atom splits, releasing energy.  
In case of an inhibition signal, two scenarios occur:  
1. The atom splits, transferring part of the energy to the inhibitor.  
2. The atom becomes waste.  

#### Shared Statistics:
The **master process** displays simulation statistics on the screen every second. These values are constantly updated by the processes after each operation. The shared memory structure includes the following variables:  
1. **Master PID**  
2. **Inhibitor status**  
3. **Number of atoms added (relative and absolute)**  
4. **Number of activations (relative and absolute)**  
5. **Number of splits (relative and absolute)**  
6. **Energy produced (relative and absolute)**  
7. **Energy consumed (relative and absolute)**  
8. **Number of waste atoms (relative and absolute)**  
9. **Number of currently active atom processes**  

#### Synchronization:
To synchronize processes and avoid inconsistent results, a set of semaphores is used (only for variables related to the last second):  
1. **START_SEM** to start the simulation.  
2. **ATOMS_SEM** to modify the number of inserted atoms.  
3. **SPLITTING_SEM** to modify the number of splits.  
4. **WASTE_SEM** to modify the number of waste atoms.  
5. **ACTIVATION_SEM** to modify the number of activations.  
6. **ABSORBING_SEM** to modify the energy absorbed by the inhibitor.  
7. **ENERGY_SEM** to modify the energy produced.  
8. **MSGQ_SEM** to read from the message queue.  
9. **LOG_SEM** to write to the log file.  

### Commands:
| Combination | Description |  
|:------------|-------------|  
| CTRL + C    | Ends the simulation, as requested by the user. |  
| CTRL + Z    | Toggles the activation/deactivation of the inhibitor process. |  

### Configuration:
The configuration parameters are read at runtime through a `config.ini` file. The structure of the configuration file is as follows:  
| Name | Description |  
|:-----|------------:|  
| **n_atoms_init**            | Number of atoms created by the master at the start of the simulation. |  
| **energy_demand**           | Energy consumed by the master every second. |  
| **energy_explode_threshold**| Maximum accumulable energy threshold. |  
| **simulation_duration**     | Duration of the simulation in seconds. |  
| **n_atom_max**              | Maximum atomic number that can be generated. |  
| **n_atom_min**              | Minimum atomic number below which an atom becomes waste. |  
| **feeder_step**             | Feeder process speed. |  
| **n_new_atoms**             | Number of atoms added by the feeder every step (in nanoseconds). |  
| **activator_step**          | Activator process speed. |  
| **inhibitor_step**          | Inhibitor process speed. |  
| **in_mode**                 | Initial state of the inhibitor process. |  
| **inhibitor_percent_absorbing** | Proportion of energy absorbed by the inhibitor. |  
| **inhibitor_probability_waste** | Probability that the inhibitor turns an atom process into waste. |  

### Libraries:
The following libraries were used in the project:  
1. `stdio.h`  
2. `stdlib.h`  
3. `sys/ipc.h`, `sys/shm.h`, `sys/sem.h`, `sys/msg.h` for creating and performing operations on IPC structures.  
4. `signal.h` for modifying or performing operations on signal handlers.  
5. `time.h` for obtaining and managing time and date functions (e.g., writing timestamps to logs).  
6. `errno.h` for accessing error constants.  
7. `unistd.h` for accessing various system calls.  
8. `stdarg.h` for using argument lists in functions.  
9. `ini.h` - a third-party library for retrieving configuration information from the `config.ini` file (available at [https://github.com/benhoyt/inih.git](https://github.com/benhoyt/inih.git)).  