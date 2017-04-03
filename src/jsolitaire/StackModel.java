/*
 * License
 */
package jsolitaire;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;
import javax.swing.ListModel;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class StackModel<T extends Observable> extends Stack<T> implements ListModel<T>, Observer {

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
        x.deleteObserver(this);
        fireIntervalRemoved(this, i, i);
        return x;
    }

    @Override
    public T push(T item) {
        int i = size();
        T x = super.push(item);
        x.addObserver(this);
        fireIntervalAdded(this, i, i);
        return x;
    }

    @Override
    public synchronized T peek() {
        return empty() ? null : super.peek();
    }

    @Override
    public void clear() {
        int size = size();
        for (int i = 0; i < size; i++) {
            pop();
        }
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

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("updating!");
        fireContentsChanged(this, 0, size() - 1);
    }
}
