/*
 * Title:        iQuantum Toolkit
 * Description:  Simulation Toolkit for Modeling and Simulation of Quantum Computing Environments
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2023, CLOUDS Lab, The University of Melbourne, Australia
 */
package org.iquantum.lists;

import org.iquantum.tasks.QTask;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QTaskList {
    /**
     * Gets a {@link QTask} with a given id.
     *
     * @param qtaskList the list of existing QTasks
     * @param id the QTask id
     * @return a QTask with the given ID or $null if not found
     */
    public static <T extends QTask> T getById(List<T> qtaskList, int id) {
        for (T qtask : qtaskList) {
            if (qtask.getQTaskId() == id) {
                return qtask;
            }
        }
        return null;
    }

    /**
     * Gets the position of a QTask with a given id.
     *
     * @param qtaskList the list of existing QTasks
     * @param id the QTask id
     * @return the position of the QTask with the given id or -1 if not found
     */
    public static <T extends QTask> int getPositionById(List<T> qtaskList, int id) {
        int i = 0 ;
        for (T qtask : qtaskList) {
            if (qtask.getQTaskId() == id) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Sorts the QTasks in a list based on their number of layers.
     *
     * @param QTaskList the QTask list
     * @pre $none
     * @post $none
     */
    public static <T extends QTask> void sort(List<T> QTaskList) {
        Collections.sort(QTaskList, new Comparator<T>() {

            /**
             * Compares two QTasks.
             *
             * @param a the first QTask to be compared
             * @param b the second QTask to be compared
             * @return the value 0 if both QTasks are numerically equal; a value less than 0 if the
             *         first Object is numerically less than the second QTask; and a value greater
             *         than 0 if the first QTask is numerically greater than the second QTask.
             * @throws ClassCastException <tt>a</tt> and <tt>b</tt> are expected to be of type
             *             <tt>QTask</tt>
             * @pre a != null
             * @pre b != null
             * @post $none
             */
            @Override
            public int compare(T a, T b) throws ClassCastException {
                Double cla = Double.valueOf(a.getNumLayers());
                Double clb = Double.valueOf(b.getNumLayers());
                return cla.compareTo(clb);
            }
        });
    }
}
