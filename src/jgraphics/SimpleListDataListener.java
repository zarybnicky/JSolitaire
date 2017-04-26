package jgraphics;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * A simple ListDataListener proxy that runs its target function on every update.
 *
 * @author Jakub Zarybnický (xzaryb00)
 * @author Jiří Záleský (xzales12).
 */
public class SimpleListDataListener implements ListDataListener {
    private final Runnable fn;

    /**
     * Constructs a list data listener.
     * 
     * @param fn The function to run on every List update.
     */
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
