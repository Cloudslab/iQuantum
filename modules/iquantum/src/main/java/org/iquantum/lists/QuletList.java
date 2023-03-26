package org.iquantum.lists;

import org.iquantum.Qulet;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QuletList {
    /**
     * Gets a {@link Qulet} with a given id.
     *
     * @param quletList the list of existing Qulets
     * @param id the Qulet id
     * @return a Qulet with the given ID or $null if not found
     */
    public static <T extends Qulet> T getById(List<T> quletList, int id) {
        for (T qulet : quletList) {
            if (qulet.getQuletId() == id) {
                return qulet;
            }
        }
        return null;
    }

    /**
     * Gets the position of a Qulet with a given id.
     *
     * @param quletList the list of existing Qulets
     * @param id the Qulet id
     * @return the position of the Qulet with the given id or -1 if not found
     */
    public static <T extends Qulet> int getPositionById(List<T> quletList, int id) {
        int i = 0 ;
        for (T qulet : quletList) {
            if (qulet.getQuletId() == id) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Sorts the Qulets in a list based on their number of layers.
     *
     * @param QuletList the Qulet list
     * @pre $none
     * @post $none
     */
    public static <T extends Qulet> void sort(List<T> QuletList) {
        Collections.sort(QuletList, new Comparator<T>() {

            /**
             * Compares two Qulets.
             *
             * @param a the first Qulet to be compared
             * @param b the second Qulet to be compared
             * @return the value 0 if both Qulets are numerically equal; a value less than 0 if the
             *         first Object is numerically less than the second Qulet; and a value greater
             *         than 0 if the first Qulet is numerically greater than the second Qulet.
             * @throws ClassCastException <tt>a</tt> and <tt>b</tt> are expected to be of type
             *             <tt>Qulet</tt>
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
