/*
 * License
 */
package jsolitaire;

import java.util.Arrays;
import java.util.Stack;
import javax.swing.ListModel;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

class StackModel<T> extends Stack<T> implements ListModel<T> {

    private static final long serialVersionUID = 1L;

    private final EventListenerList listenerList = new EventListenerList();

    @Override
    public int getSize() {
        return size();
    }

    @Override
    public T getElementAt(int index) {
        return super.get(index);
    }

    @Override
    public synchronized Object clone() {
        return super.clone();
    }

    @Override
    public synchronized T pop() {
        int i = size() - 1;
        T x = super.pop();
        fireIntervalRemoved(this, i, i);
        return x;
    }

    @Override
    public T push(T item) {
        int i = size();
        T x = super.push(item);
        fireIntervalAdded(this, i, i);
        return x;
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listenerList.add(ListDataListener.class, l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listenerList.remove(ListDataListener.class, l);
    }

    protected void fireContentsChanged(Object source, int index0, int index1) {
        ListDataEvent e = new ListDataEvent(source, ListDataEvent.CONTENTS_CHANGED, index0, index1);
        Arrays.asList(listenerList.getListeners(ListDataListener.class))
                .forEach(y -> y.contentsChanged(e));
    }

    protected void fireIntervalAdded(Object source, int index0, int index1) {
        ListDataEvent e = new ListDataEvent(source, ListDataEvent.INTERVAL_ADDED, index0, index1);
        Arrays.asList(listenerList.getListeners(ListDataListener.class))
                .forEach(y -> y.intervalAdded(e));
    }

    protected void fireIntervalRemoved(Object source, int index0, int index1) {
        ListDataEvent e = new ListDataEvent(source, ListDataEvent.INTERVAL_REMOVED, index0, index1);
        Arrays.asList(listenerList.getListeners(ListDataListener.class))
                .forEach(y -> y.intervalRemoved(e));
    }
}
