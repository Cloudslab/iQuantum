/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */
package org.iquantum.core;
import org.cloudbus.cloudsim.core.CloudInformationService;

public class iQuantumTags {
    /** Starting constant value for cloud-related tags. **/
    private static final int BASE = 0;

    /** ORIGINAL CLOUDSIM TAGS **/
    /** Denotes the end of simulation. */
    public static final int END_OF_SIMULATION = -1;
    /**
     * Denotes a cloud resource to be registered. This tag is normally used between
     * {@link CloudInformationService} and CloudResouce entities.
     */
    public static final int REGISTER_RESOURCE = BASE + 2;
    /**
     * Denotes request for cloud resource characteristics information. This tag is normally used
     * between CloudSim and CloudResource entity.
     */
    public static final int RESOURCE_CHARACTERISTICS_REQUEST = BASE + 15;

    /**
     * Denotes cloud resource characteristics information. This tag is normally used between CloudSim
     * and CloudResource entity.
     */
    public static final int RESOURCE_CHARACTERISTICS = BASE + 6;


    /**
     * iQuantumTags (start from 1000 to separate from CloudSimTags)
     * @author Hoa Nguyen
     * @since iQuantum 0.1
     */
    /**
     * Denotes a Qulet is ready to be submitted to a QDatacenter.
     */
    public static final int QULET_SUBMIT_READY = BASE + 1000;

/**
     * Denotes a Qulet is submitting to a QDatacenter.
     */
    public static final int QULET_SUBMIT = BASE + 1001;

    /**
     * Denotes a Qulet is returned from a QDatacenter.
     */
    public static final int QULET_RETURN = BASE + 1002;

    /**
     * Denotes a Qulet is being processed in a QDatacenter.
     */
    public static final int UPDATE_QULET_PROCESSING = BASE + 1003;

    /**
     * Denotes a Qulet is cancelled.
     */
    public static final int QULET_CANCEL = BASE + 1004;

    /**
     * Denotes a Qulet is failed as a result of a qubit insufficiency.
     */
    public static final int QULET_FAILED_QUBIT = BASE + 1005;

    /**
     * Denotes a Qulet is failed as a result of a gate insufficiency.
     */
    public static final int QULET_FAILED_GATES = BASE + 1006;
}
