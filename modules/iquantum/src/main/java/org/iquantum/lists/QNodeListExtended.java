/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */
package org.iquantum.lists;

import org.iquantum.backends.quantum.QNode;
import org.iquantum.backends.quantum.QNodeExtended;

import java.util.List;

public class QNodeListExtended {


    private QNodeListExtended() {

    }

    public static <T extends QNodeExtended> T getById(List<T> qNodeList, int id) {
        for (T qNode : qNodeList) {
            if (qNode.getId() == id) {
                return qNode;
            }
        }
        return null;
    }
}
