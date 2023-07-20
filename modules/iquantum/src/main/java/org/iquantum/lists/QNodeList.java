/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */
package org.iquantum.lists;

import org.iquantum.backends.quantum.QNode;

import java.util.ArrayList;
import java.util.List;

public class QNodeList {

    protected static List<QNode> qNodeList;

    public QNodeList() {
        qNodeList = new ArrayList<QNode>();

    }

    public static QNode getById(int id) {
        for (QNode qNode : qNodeList) {
            if (qNode.getId() == id) {
                return qNode;
            }
        }
        return null;
    }
}
