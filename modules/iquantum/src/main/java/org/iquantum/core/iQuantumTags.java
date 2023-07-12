/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */
package org.iquantum.core;

import org.iquantum.datacenters.CDatacenter;

public class iQuantumTags {
    /** ORIGINAL iQuantum TAGS **/
    /** Starting constant value for cloud-related tags. **/
    private static final int BASE = 0;

    /** Starting constant value for network-related tags. **/
    private static final int NETBASE = 100;

    /** Denotes boolean <tt>true</tt> in <tt>int</tt> value. */
    public static final int TRUE = 1;

    /** Denotes boolean <tt>false</tt> in <tt>int</tt> value. */
    public static final int FALSE = 0;

    /** Denotes the default baud rate for iQuantum entities. */
    public static final int DEFAULT_BAUD_RATE = 9600;

    /** Schedules an entity without any delay. */
    public static final double SCHEDULE_NOW = 0.0;

    /** Denotes the end of simulation. */
    public static final int END_OF_SIMULATION = -1;

    /**
     * Denotes an abrupt end of simulation. That is, one event of this type is enough for
     * {@link iQuantumShutdown} to trigger the end of the simulation
     */
    public static final int ABRUPT_END_OF_SIMULATION = -2;

    /**
     * Denotes insignificant simulation entity or time. This tag will not be used for identification
     * purposes.
     */
    public static final int INSIGNIFICANT = BASE + 0;

    /** Sends an Experiment object between UserEntity and Broker entity */
    public static final int EXPERIMENT = BASE + 1;

    /**
     * Denotes a cloud resource to be registered. This tag is normally used between
     * {@link org.iquantum.core.CloudInformationService} and CloudResouce entities.
     */
    public static final int REGISTER_RESOURCE = BASE + 2;

    /**
     * Denotes a cloud resource to be registered, that can support advance reservation. This tag is
     * normally used between {@link org.iquantum.core.CloudInformationService} and CloudResouce entity.
     */
    public static final int REGISTER_RESOURCE_AR = BASE + 3;

    /**
     * Denotes a list of all hostList's, including the ones that can support advance reservation. This
     * tag is normally used between {@link org.iquantum.core.CloudInformationService} and iQuantum entity.
     */
    public static final int RESOURCE_LIST = BASE + 4;

    /**
     * Denotes a list of hostList's that only support advance reservation. This tag is normally used
     * between {@link org.iquantum.core.CloudInformationService} and iQuantum entity.
     */
    public static final int RESOURCE_AR_LIST = BASE + 5;

    /**
     * Denotes cloud resource characteristics information. This tag is normally used between iQuantum
     * and CloudResource entity.
     */
    public static final int RESOURCE_CHARACTERISTICS = BASE + 6;

    /**
     * Denotes cloud resource allocation policy. This tag is normally used between iQuantum and
     * CloudResource entity.
     */
    public static final int RESOURCE_DYNAMICS = BASE + 7;

    /**
     * Denotes a request to get the total number of Processing Elements (PEs) of a resource. This
     * tag is normally used between iQuantum and CloudResource entity.
     */
    public static final int RESOURCE_NUM_PE = BASE + 8;

    /**
     * Denotes a request to get the total number of free Processing Elements (PEs) of a resource.
     * This tag is normally used between iQuantum and CloudResource entity.
     */
    public static final int RESOURCE_NUM_FREE_PE = BASE + 9;

    /**
     * Denotes a request to record events for statistical purposes. This tag is normally used
     * between iQuantum and CloudStatistics entity.
     */
    public static final int RECORD_STATISTICS = BASE + 10;

    /** Denotes a request to get a statistical list. */
    public static final int RETURN_STAT_LIST = BASE + 11;

    /**
     * Denotes a request to send an Accumulator object based on category into an event scheduler.
     * This tag is normally used between ReportWriter and CloudStatistics entity.
     */
    public static final int RETURN_ACC_STATISTICS_BY_CATEGORY = BASE + 12;

    /**
     * Denotes a request to register a CloudResource entity to a regional
     * {@link CloudInformationService} (CIS) entity.
     */
    public static final int REGISTER_REGIONAL_GIS = BASE + 13;

    /**
     * Denotes a request to get a list of other regional CIS entities from the system CIS entity.
     */
    public static final int REQUEST_REGIONAL_GIS = BASE + 14;

    /**
     * Denotes request for cloud resource characteristics information. This tag is normally used
     * between iQuantum and CloudResource entity.
     */
    public static final int RESOURCE_CHARACTERISTICS_REQUEST = BASE + 15;

    /** This tag is used by an entity to send ping requests. */
    public static final int INFOPKT_SUBMIT = NETBASE + 5;

    /** This tag is used to return the ping request back to sender. */
    public static final int INFOPKT_RETURN = NETBASE + 6;

    /**
     * Denotes the return of a Cloudlet back to sender.
     * This tag is normally used by CloudResource entity.
     */
    public static final int CLOUDLET_RETURN = BASE + 20;

    /**
     * Denotes the submission of a Cloudlet.
     * This tag is normally used between iQuantum User and CloudResource entity.
     */
    public static final int CLOUDLET_SUBMIT = BASE + 21;

    /**
     * Denotes the submission of a Cloudlet with an acknowledgement. This tag is normally used
     * between iQuantum User and CloudResource entity.
     */
    public static final int CLOUDLET_SUBMIT_ACK = BASE + 22;

    /** Cancels a Cloudlet submitted in the CloudResource entity. */
    public static final int CLOUDLET_CANCEL = BASE + 23;

    /** Denotes the status of a Cloudlet. */
    public static final int CLOUDLET_STATUS = BASE + 24;

    /** Pauses a Cloudlet submitted in the CloudResource entity. */
    public static final int CLOUDLET_PAUSE = BASE + 25;

    /**
     * Pauses a Cloudlet submitted in the CloudResource entity with an acknowledgement.
     */
    public static final int CLOUDLET_PAUSE_ACK = BASE + 26;

    /** Resumes a Cloudlet submitted in the CloudResource entity. */
    public static final int CLOUDLET_RESUME = BASE + 27;

    /**
     * Resumes a Cloudlet submitted in the CloudResource entity with an acknowledgement.
     */
    public static final int CLOUDLET_RESUME_ACK = BASE + 28;

    /** Moves a Cloudlet to another CloudResource entity. */
    public static final int CLOUDLET_MOVE = BASE + 29;

    /**
     * Moves a Cloudlet to another CloudResource entity with an acknowledgement.
     */
    public static final int CLOUDLET_MOVE_ACK = BASE + 30;

    /**
     * Denotes a request to create a new VM in a {@link CDatacenter} with acknowledgement
     * information sent by the Datacenter.
     */
    public static final int VM_CREATE = BASE + 31;

    /**
     * Denotes a request to create a new VM in a {@link CDatacenter}
     * with acknowledgement information sent by the Datacenter.
     */
    public static final int VM_CREATE_ACK = BASE + 32;

    /**
     * Denotes a request to destroy a new VM in a {@link CDatacenter}.
     */
    public static final int VM_DESTROY = BASE + 33;

    /**
     * Denotes a request to destroy a new VM in a {@link CDatacenter}
     * with acknowledgement information sent by the Datacener.
     */
    public static final int VM_DESTROY_ACK = BASE + 34;

    /**
     * Denotes a request to migrate a new VM in a {@link CDatacenter}.
     */
    public static final int VM_MIGRATE = BASE + 35;

    /**
     * Denotes a request to migrate a new VM in a {@link CDatacenter}
     * with acknowledgement information sent by the Datacener.
     */
    public static final int VM_MIGRATE_ACK = BASE + 36;

    /**
     * Denotes an event to send a file from a user to a {@link CDatacenter}.
     */
    public static final int VM_DATA_ADD = BASE + 37;

    /**
     * Denotes an event to send a file from a user to a {@link CDatacenter}
     * with acknowledgement information sent by the Datacener.
     */
    public static final int VM_DATA_ADD_ACK = BASE + 38;

    /**
     * Denotes an event to remove a file from a {@link CDatacenter} .
     */
    public static final int VM_DATA_DEL = BASE + 39;

    /**
     * Denotes an event to remove a file from a {@link CDatacenter}
     * with acknowledgement information sent by the Datacener.
     */
    public static final int VM_DATA_DEL_ACK = BASE + 40;

    /**
     * Denotes an internal event generated in a {@link CDatacenter}.
     */
    public static final int VM_DATACENTER_EVENT = BASE + 41;

    /**
     * Denotes an internal event generated in a Broker.
     */
    public static final int VM_BROKER_EVENT = BASE + 42;

    public static final int Network_Event_UP = BASE + 43;

    public static final int Network_Event_send = BASE + 44;

    public static final int RESOURCE_Register = BASE + 45;

    public static final int Network_Event_DOWN = BASE + 46;

    public static final int Network_Event_Host = BASE + 47;

    public static final int NextCycle = BASE + 48;
//    /** Starting constant value for cloud-related tags. **/
//    private static final int BASE = 0;
//
//    /** ORIGINAL iQuantum TAGS **/
//    /** Denotes the end of simulation. */
//    public static final int END_OF_SIMULATION = -1;
//    /**
//     * Denotes a cloud resource to be registered. This tag is normally used between
//     * {@link CloudInformationService} and CloudResouce entities.
//     */
//    public static final int REGISTER_RESOURCE = BASE + 2;
//    /**
//     * Denotes request for cloud resource characteristics information. This tag is normally used
//     * between iQuantum and CloudResource entity.
//     */
//    public static final int RESOURCE_CHARACTERISTICS_REQUEST = BASE + 15;
//



    /**
     * iQuantumTags (start from 1000 to separate from iQuantumTags)
     * @author Hoa Nguyen
     * @since iQuantum 0.1
     */
    /**
     * Denotes a QTask is ready to be submitted to a QDatacenter.
     */

    private static final int QBASE = 1000;

    public static final int QTASK_SUBMIT_READY = QBASE + 1;

/**
     * Denotes a QTask is submitting to a QDatacenter.
     */
    public static final int QTASK_SUBMIT = QBASE + 2;

    /**
     * Denotes a QTask is returned from a QDatacenter.
     */
    public static final int QTASK_RETURN = QBASE + 3;

    /**
     * Denotes a QTask is being processed in a QDatacenter.
     */
    public static final int UPDATE_QTASK_PROCESSING = QBASE + 4;

    /**
     * Denotes a QTask is cancelled.
     */
    public static final int QTASK_CANCEL = QBASE + 5;

    /**
     * Denotes a QTask is failed as a result of a qubit insufficiency.
     */
    public static final int QTASK_FAILED_QUBIT = QBASE + 6;

    /**
     * Denotes a QTask is failed as a result of a gate insufficiency.
     */
    public static final int QTASK_FAILED_GATES = QBASE + 7;

    /**
     * Denotes a QTask is failed as a result of a time insufficiency.
     */
    public static final int QTASK_FAILED_QUBIT_MAP = QBASE + 8;

    /**
     * Denotes cloud resource characteristics information. This tag is normally used between iQuantum
     * and CloudResource entity.
     */
    public static final int QRESOURCE_CHARACTERISTICS = QBASE + 9;

    /**
     * Denotes request for cloud resource characteristics information. This tag is normally used
     * between iQuantum and CloudResource entity.
     */
    public static final int QRESOURCE_CHARACTERISTICS_REQUEST = QBASE + 10;

    public static final int QREGISTER_RESOURCE = QBASE + 11;

    public static final int QRESOURCE_LIST = QBASE + 12;

    public static final int CLOUD_GATEWAY_DISPATCH_TASK = QBASE + 13;
    /** Private Constructor. */
    private iQuantumTags() {
        throw new UnsupportedOperationException("iQuantumTags cannot be instantiated");
    }
}
