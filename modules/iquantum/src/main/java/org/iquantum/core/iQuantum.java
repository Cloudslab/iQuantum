/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */
package org.iquantum.core;

import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.core.CloudInformationService;
import org.cloudbus.cloudsim.core.CloudSim;

import java.util.Calendar;

/**
 * The iQuantum class is the main class of the iQuantum Toolkit. It is responsible for
 * initializing the simulation and starting the simulation.
 * This class is inherited from CloudSim class, extending for future improvements
 *
 * @author Hoa Nguyen
 * @since iQuantum 1.0
 */
public class iQuantum extends CloudSim {

    private static final String IQUANTUM_VERSION_STRING = "0.3";

    private static boolean traceFlag;

    public static void init(int numUser, Calendar cal, boolean traceFlag) {
        CloudSim.init(numUser, cal, traceFlag);
        setTraceFlag(traceFlag);
    }

    public static double startSimulation() throws NullPointerException {
        Log.printConcatLine("Starting iQuantum version ", IQUANTUM_VERSION_STRING);
        return CloudSim.startSimulation();
    }


    public static boolean getTraceFlag() {
        return traceFlag;
    }

    private static void setTraceFlag(boolean _traceFlag) {
        traceFlag = _traceFlag;
    }

}
