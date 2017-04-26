/*
 * @authors: Jakub Zarybnický (xzaryb00)
 *           Jiří Záleský (xzales12)
 * VUTBR BIT 2, 2016/17
 *
 * Description: Implementation of specialized listener.
 */

package jgraphics;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class SimpleListDataListener implements ListDataListener {
    private Runnable fn;

    public SimpleListDataListener(Runnable fn) {
        this.fn = fn;
    }

    @Override
    public void intervalAdded(ListDataEvent e) {
        fn.run();
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
        fn.run();
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
        fn.run();
    }
}
