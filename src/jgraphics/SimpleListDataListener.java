/*
 * License
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
