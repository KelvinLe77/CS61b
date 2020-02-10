/**
 * Scheme-like pairs that can be used to form a list of integers.
 *
 * @author P. N. Hilfinger; updated by Vivant Sakore (1/29/2020)
 */
public class IntDList {

    /**
     * First and last nodes of list.
     */
    protected DNode _front, _back;

    /**
     * An empty list.
     */
    public IntDList() {
        _front = _back = null;
    }

    /**
     * @param values the ints to be placed in the IntDList.
     */
    public IntDList(Integer... values) {
        _front = _back = null;
        for (int val : values) {
            insertBack(val);
        }
    }

    /**
     * @return The first value in this list.
     * Throws a NullPointerException if the list is empty.
     */
    public int getFront() {
        return _front._val;
    }

    /**
     * @return The last value in this list.
     * Throws a NullPointerException if the list is empty.
     */
    public int getBack() {
        return _back._val;
    }

    /**
     * @return The number of elements in this list.
     */
    public int size() {
        // FIXME: Implement this method and return correct value
        if (_front == null) {
            return 0;
        }
        else {
            DNode beginning = _front;
            int numElements = 1;
            for (int i = 1; beginning != null; i += 1) {
                beginning = beginning._next;
                numElements = i;
            }
            return numElements;
        }

    }

    /**
     * @param i index of element to return,
     *          where i = 0 returns the first element,
     *          i = 1 returns the second element,
     *          i = -1 returns the last element,
     *          i = -2 returns the second to last element, and so on.
     *          You can assume i will always be a valid index, i.e 0 <= i < size for positive indices
     *          and -size <= i <= -1 for negative indices.
     * @return The integer value at index i
     */
    public int get(int i) {
        if (i > 0) {
            DNode beginning = _front;
            while (i > 0) {
                beginning = beginning._next;
                i -= 1;
            }
            return beginning._val;
        }
        else if (i < 0) {
            i += 1;
            DNode end = _back;
            while (i < 0) {
                end = end._prev;
                i += 1;
            }
            return end._val;
        }
        else {
            return _front._val;
        }
    }

    /**
     * @param d value to be inserted in the front
     */
    public void insertFront(int d) {
        // FIXME: Implement this method
        if (_front == null) {
            _front = _back = new DNode(d);
        }
        else {
            DNode newFront = new DNode(d);
            newFront._next = _front;
            _front._prev = newFront;
            _front = newFront;
        }
    }

    /**
     * @param d value to be inserted in the back
     */
    public void insertBack(int d) {
        // FIXME: Implement this method
        if (_back == null) {
            _front = _back = new DNode(d);
        }
        else {
            DNode newBack = new DNode(d);
            newBack._prev = _back;
            _back._next = newBack;
            _back = newBack;
        }
    }

    /**
     * @param d     value to be inserted
     * @param index index at which the value should be inserted
     *              where index = 0 inserts at the front,
     *              index = 1 inserts at the second position,
     *              index = -1 inserts at the back,
     *              index = -2 inserts at the second to last position, and so on.
     *              You can assume index will always be a valid index,
     *              i.e 0 <= index <= size for positive indices (including insertions at front and back)
     *              and -(size+1) <= index <= -1 for negative indices (including insertions at front and back).
     */
    public void insertAtIndex(int d, int index) {
        // FIXME: Implement this method
    }

    /**
     * Removes the first item in the IntDList and returns it.
     *
     * @return the item that was deleted
     */
    public int deleteFront() {
        // FIXME: Implement this method and return correct value
        int frontToReturn;
        if (_front._next == null) {
            frontToReturn = _front._val;
            _front = _back = null;
        }
        else {
            frontToReturn = _front._val;
            _front = _front._next;
            _front._prev = null;
        }
        return frontToReturn;
    }

    /**
     * Removes the last item in the IntDList and returns it.
     *
     * @return the item that was deleted
     */
    public int deleteBack() {
        // FIXME: Implement this method and return correct value
        int backToReturn;
        if (_back._prev == null) {
            backToReturn = _back._val;
            _front = _back = null;
        }
        else {
            backToReturn = _back._val;
            _back = _back._prev;
            _back._next = null;
        }
        return backToReturn;
    }

    /**
     * @param index index of element to be deleted,
     *          where index = 0 returns the first element,
     *          index = 1 will delete the second element,
     *          index = -1 will delete the last element,
     *          index = -2 will delete the second to last element, and so on.
     *          You can assume index will always be a valid index,
     *              i.e 0 <= index < size for positive indices (including deletions at front and back)
     *              and -size <= index <= -1 for negative indices (including deletions at front and back).
     * @return the item that was deleted
     */
    public int deleteAtIndex(int index) {
        // FIXME: Implement this method and return correct value
        /**int deleteVal = 0;
        if (index > 0 && size() > 0) {
            DNode beg = _front;
            for (int i = 0; i < index; i += 1) {
                beg = beg._next;
            }
            deleteVal = beg._val;
            if (beg._prev != null && beg._next != null) {
                beg._prev._next = beg._next;
                beg._next._prev = beg._prev;
            }
            else {
                beg._next._prev = null;
                _front = beg._next;
            }
        }
        else if (index < 0 && size() > 0) {
            index += 1;
            DNode end = _back;
            for (int i = 0; i > index; i -= 1) {
                end = end._prev;
            }
            deleteVal = end._val;
            if (end._prev != null && end._next != null) {
                end._prev._next = end._next;
                end._next._prev = end._prev;
            }
            else {
                end._prev._next = null;
                _back = end._prev;
            }
        }
        else {
            deleteVal = _front._val;
            _front = _back = null;
        }
        return deleteVal;*/

        DNode pointer = _front;
        int value;
        if (index > 0) {
            pointer = _front;
            while (index > 0) {
                pointer = pointer._next;
                index -= 1;
            }
        }
        else if (index < 0) {
            index += 1;
            pointer = _back;
            while (index < 0) {
                pointer = pointer._prev;
                index += 1;
            }
        }

        if (pointer._prev != null && pointer._next != null) {
            value = pointer._val;
            pointer._prev._next = pointer._next;
            pointer._next._prev = pointer._prev;
        }
        else if (pointer._prev == null && pointer._next != null) {
            value = pointer._val;
            pointer._next._prev = null;
            _front = pointer._next;
        }
        else if (pointer._prev != null && pointer._next == null) {
            value = pointer._val;
            pointer._prev._next = null;
            _back = pointer._prev;
        }
        else {
            value = _front._val;
            _front = _back = null;
            return value;
        }

        return value;
    }

    /**
     * @return a string representation of the IntDList in the form
     * [] (empty list) or [1, 2], etc.
     * Hint:
     * String a = "a";
     * a += "b";
     * System.out.println(a); //prints ab
     */
    public String toString() {
        // FIXME: Implement this method to return correct value
        if (size() == 0) {
            return "[]";
        }
        String str = "[";
        DNode curr = _front;
        for (; curr._next != null; curr = curr._next) {
            str += curr._val + ", ";
        }
        str += curr._val +"]";
        return str;
    }

    /**
     * DNode is a "static nested class", because we're only using it inside
     * IntDList, so there's no need to put it outside (and "pollute the
     * namespace" with it. This is also referred to as encapsulation.
     * Look it up for more information!
     */
    static class DNode {
        /** Previous DNode. */
        protected DNode _prev;
        /** Next DNode. */
        protected DNode _next;
        /** Value contained in DNode. */
        protected int _val;

        /**
         * @param val the int to be placed in DNode.
         */
        protected DNode(int val) {
            this(null, val, null);
        }

        /**
         * @param prev previous DNode.
         * @param val  value to be stored in DNode.
         * @param next next DNode.
         */
        protected DNode(DNode prev, int val, DNode next) {
            _prev = prev;
            _val = val;
            _next = next;
        }
    }

}
