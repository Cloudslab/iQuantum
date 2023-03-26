package org.iquantum.lists;

import org.iquantum.QNode;

import java.util.List;

public class QNodeList {

    public static <T extends QNode> T getById(List<T> qNodeList, int id) {
        for (T qNode : qNodeList) {
            if (qNode.getId() == id) {
                return qNode;
            }
        }
        return null;
    }
}
